package cc.occs.common.config;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ServerConfig {

    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        System.err.println(url);
        String contextPath = request.getServletContext().getContextPath();
        System.err.println(contextPath);
        return "";
    }

}
