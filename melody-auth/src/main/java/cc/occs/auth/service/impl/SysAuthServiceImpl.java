package cc.occs.auth.service.impl;

import cc.occs.auth.domain.UserDetail;
import cc.occs.auth.model.UserInfoResponse;
import cc.occs.auth.service.SysAuthService;
import cc.occs.auth.utils.JwtUtils;
import cc.occs.common.exception.CustomException;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtils jwtUtils;

    /**
     * Login Service Implements
     * @param username Username
     * @param password Password
     * @return User Information Response, Include (Token And UserDetail)
     */
    @Override
    public UserInfoResponse login(String username, String password) throws CustomException {
        /* User Authentication */
        Authentication authentication = authentication(username, password);
        /* Store Certification Information */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("------");
        /* Generate Token */
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetail);
        System.out.println(token);
        // Store Token
        jwtUtils.putToken(username, token);
        return new UserInfoResponse(jwtUtils.getTokenHeader() + token, userDetail);
    }

    private Authentication authentication(String username, String password) {
        try {
            /**
             * This Method Will Call The "MelodyUserDetailsServiceImpl.loadUserByUsername()"
             * To Check Username And Password
             * If True: Save Username And Password In "context" of "security"
             */
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResJson.failure(ResCode.LOGIN_FAILED, e.getMessage()));
        }
    }
}
