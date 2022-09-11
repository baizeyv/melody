package cc.occs.common.model;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum ResCode {

    SUCCESS(200, "Success"),

    ARGS_ERROR(400, "Args Error"),

    UNAUTHORIZED(401,"Unauthorized"),

    LOGIN_FAILED(402, "Login Failed"),

    DENY_AUTH(403, "Insufficient Permissions"),

    FATAL_ERROR(-1),

    UNKNOWN_ERROR("Unknown Error"),

    HAS_CHILD_RESOURCE(7373001, "Resource Has Child"),

    ROLE_DUPLICATION(7373002, "Role Name Is Duplication"),

    LOGIN_SUCCESS(7373003, "Login Successful"),

    LOGOUT_SUCCESS(7373004, "Logout Successful"),

    USER_EXIST(7373005, "User Exist"),

    ;

    /* 状态码 */
    private int code;

    /* 消息提示 */
    private String message;

    ResCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    ResCode(String message) {
        this.code = -999;
        this.message = message;
    }

    ResCode(int code) {
        this.code = code;
        this.message = "Fatal Error";
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
