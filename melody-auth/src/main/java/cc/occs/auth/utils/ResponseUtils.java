package cc.occs.auth.utils;

import cc.occs.common.model.ResJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
    public static void out(HttpServletResponse response, ResJson resJson) {
        System.out.println("ResponseUtils");
        System.out.println(resJson.toString());
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setStatus(resJson.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            writer = response.getWriter();
            mapper.writeValue(writer, resJson);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
