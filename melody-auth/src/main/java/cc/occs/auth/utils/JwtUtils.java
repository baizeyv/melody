package cc.occs.auth.utils;

import cc.occs.auth.domain.SysResource;
import cc.occs.auth.domain.SysRole;
import cc.occs.auth.domain.SysUser;
import cc.occs.auth.domain.UserDetail;
import cc.occs.auth.service.SysResourceService;
import cc.occs.auth.service.SysUserService;
import cc.occs.common.exception.CustomException;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class JwtUtils {

    @Value("${security.jwt.expire}")
    private long expire;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.header}")
    private String header;

    @Value("${security.jwt.tokenHeader}")
    private String tokenHeader;

    private static final String CLAIM_KEY_ROLE = "user_role";

    private static final String CLAIM_KEY_USER_ID = "user_id";


    /**
     * TODO: It Should Be Put Into Redis; Current State Need Be Modified
     */
    private Map<String, String> tokenMap = new HashMap<>();

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * Add New Token To Token Map
     * @param username
     * @param token
     */
    public void putToken(String username, String token) {
        tokenMap.put(username, token);
    }

    /**
     * Remove Token Which Be Pointed From Token Map
     * @param username
     */
    public void removeToken(String username) {
        tokenMap.remove(username);
    }

    ////
    public boolean containToken(String username, String token) {
        if(username != null && tokenMap.containsKey(username) && tokenMap.get(username).equals(token)) {
            return true;
        }
        return false;
    }

    // Generate JWT Token By Username

    /**
     * Generate Token By Username And Claims Map And Expiration Time
     * @param username Username
     * @param claimsMap Claims Map
     * @param expiration Expiration Time
     * @return Token
     */
    public String generateToken(String username, Map<String, Object> claimsMap, long expiration) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claimsMap)
                .setSubject(username)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * Generate Token By Username And Claims Map
     * @param username Username
     * @param claimsMap Claims Map
     * @return Token
     */
    public String generateToken(String username, Map<String, Object> claimsMap) {
        return generateToken(username, claimsMap, expire);
    }

    /**
     * Generate Token By User Detail
     * @param userDetail UserDetail
     * @return Token
     * @throws CustomException Custom Exception
     */
    public String generateToken(UserDetail userDetail) throws CustomException {
        Map<String, Object> claimsMap = generateClaims(userDetail);
        if(userDetail.getAuthorities() == null || userDetail.getAuthorities().size() == 0) {
            throw new CustomException(ResJson.failure(ResCode.DENY_AUTH));
        } else {
            claimsMap.put(userDetail.getUsername(), authoritiesToArray(userDetail.getAuthorities()).get(0));
        }
        return generateToken(userDetail.getUsername(), claimsMap);
    }

    /**
     * Authorities Collection To List
     * @param authorities Authorities Collection
     * @return Authorities List
     */
    public List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for(GrantedAuthority item : authorities) {
            list.add(item.getAuthority());
        }
        return list;
    }

    // Generate JWT Token By Username And Role
    public String generateToken(String username, String role) {
        Date nowDate = new Date(); // Current Date
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire); // Expire Date

        return Jwts.builder()
                .setSubject(username)
                .claim(CLAIM_KEY_ROLE, role)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * Analyze JWT Token; Get Claims
     * Get Claims From Token
     * @param token Token
     * @return Claims
     */
    public Claims analyzeToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verify Whether The Token Is Effective
     * @param token Token
     * @param userDetail UserDetail
     * @return Boolean
     */
    public boolean validateToken(String token, UserDetail userDetail) {
        Long userId = getUserIdFromToken(token);
        String username = getUsernameFromToken(token);
        return (userId == userDetail.getId()
                && username.equals(userDetail.getUsername())
                && !isTokenExpired(analyzeToken(token)));
    }

    // Check Expire
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    /**
     * Generate Claims Map
     * @param userDetail UserDetail
     * @return Claims Map
     */
    private Map<String, Object> generateClaims(UserDetail userDetail) {
        Map<String, Object> claimsMap = new HashMap<>(16);
        claimsMap.put(CLAIM_KEY_USER_ID, userDetail.getId());
        return claimsMap;
    }

    /**
     * Get Expiration Date From Token
     * @param token Token
     * @return Expiration Date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expire;
        try {
            Claims claims = analyzeToken(token);
            expire = claims.getExpiration();
        } catch (Exception e) {
            expire = null;
        }
        return expire;
    }

    /**
     * Generate The Expiration Date
     * @param expire Expire Time
     * @return Expiration Date
     */
    private Date generateExpirationDate(long expire) {
        return new Date(System.currentTimeMillis() + expire * 1000);
    }

    /**
     * Get Username From Token
     * @param token Token
     * @return Username
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            username = analyzeToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Get User Id From Token
     * @param token Token
     * @return User Id
     */
    public Long getUserIdFromToken(String token) {
        long userId;
        try {
            userId = Long.parseLong(String.valueOf(analyzeToken(token).get(CLAIM_KEY_USER_ID)));
        } catch (Exception e) {
            userId = 0L;
        }
        return userId;
    }

    public Long getUserRoleFromToken(String token) {
        return Long.parseLong(String.valueOf(analyzeToken(token).get(CLAIM_KEY_ROLE)));
    }

    /**
     * Get User Detail From Token
     * @param token Token String
     * @return UserDetail
     */
    public UserDetail getUserFromToken(String token) {
        UserDetail userDetail;
        try {
            Long userId = getUserIdFromToken(token);
            String username = getUsernameFromToken(token);
            List<SysResource> sysResourceList = sysResourceService.getResourceListByUsername(username);
            SysUser sysUser = sysUserService.getById(userId);
            List<SysRole> sysRoleList = sysUserService.getRoleListByUsername(username);
            userDetail = new UserDetail(userId, username, sysUser.getAvailable(), "", sysResourceList, sysRoleList);
        } catch(Exception e) {
            userDetail = null;
        }
        return userDetail;
    }

}
