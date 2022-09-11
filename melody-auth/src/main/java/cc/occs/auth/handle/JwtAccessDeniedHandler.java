package cc.occs.auth.handle;

import cc.occs.auth.utils.ResponseUtils;
import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Insufficient Permissions
 *
 * The User Initiates A Processor Without The Right To Access The Request
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        System.out.println("JwtAccessDeniedHandler");
        ResponseUtils.out(response, ResJson.failure(ResCode.DENY_AUTH));
    }

}
