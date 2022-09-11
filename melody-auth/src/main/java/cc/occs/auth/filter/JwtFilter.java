package cc.occs.auth.filter;

import cc.occs.auth.domain.UserDetail;
import cc.occs.auth.utils.JwtUtils;
import cc.occs.auth.utils.ResponseUtils;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import cc.occs.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token Filter
 * Each Request Is Only Filtered Once
 * Main Function :
 *      Verify Token Effectiveness (Include Token Header etc.)
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;

    /**
     * Token Filter
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtFilter");
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isNoneEmpty(token) && token.startsWith(jwtUtils.getTokenHeader())) {
            token = token.substring(jwtUtils.getTokenHeader().length());
        } else {
            // Token Irregular
            token = null;
        }

        // Get Username From Token By JWT
        String username = jwtUtils.getUsernameFromToken(token);

        if(jwtUtils.containToken(username, token) && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetail = jwtUtils.getUserFromToken(token);
            if(jwtUtils.validateToken(token, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

    }
}
