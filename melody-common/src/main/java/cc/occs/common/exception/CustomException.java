package cc.occs.common.exception;

import cc.occs.common.model.ResJson;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ResJson resJson;

    public CustomException(ResJson resJson) {
        this.resJson = resJson;
    }

}
