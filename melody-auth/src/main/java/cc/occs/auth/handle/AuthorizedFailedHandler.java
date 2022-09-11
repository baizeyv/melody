package cc.occs.auth.handle;

import cc.occs.auth.utils.ResponseUtils;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Certification Failed Handler
 */
@Component
public class AuthorizedFailedHandler implements AuthenticationEntryPoint {

    /**
     *
     * @param request User Request Information
     * @param response Will Be Response To User
     * @param authException Authentication Exception
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        System.out.println("AuthorizedFailedHandler");
        System.out.println(authException.getClass().getName());
        ResponseUtils.out(response, ResJson.failure(ResCode.DENY_AUTH, authException.getClass().getName()));
    }

}
