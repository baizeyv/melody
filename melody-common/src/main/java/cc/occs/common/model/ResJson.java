package cc.occs.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResJson<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    ResJson(ResCode resCode) {
        handleResCode(resCode);
        this.data = null;
    }

    ResJson(ResCode resCode, T obj) {
        handleResCode(resCode);
        this.data = obj;
    }

    public static ResJson success() {
        return new ResJson(ResCode.SUCCESS);
    }

    public static ResJson success(Object obj) {
        return new ResJson(ResCode.SUCCESS, obj);
    }

    public static ResJson failure(ResCode resCode) {
        return new ResJson(resCode);
    }

    public static ResJson failure(ResCode resCode, Object obj) {
        return new ResJson(resCode, obj);
    }

    public void handleResCode(ResCode resCode) {
        this.code = resCode.getCode();
        this.message = resCode.getMessage();
    }

    @Override
    public String toString() {
        return "ResJson{" +
                "\"code\":" + code +
                ", \"message\": \"" + message + "\"" +
                ", \"data\":" + data +
                '}';
    }
}
