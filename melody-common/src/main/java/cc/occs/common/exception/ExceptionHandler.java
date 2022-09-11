package cc.occs.common.exception;

import cc.occs.common.model.ResCode;
import cc.occs.common.model.ResJson;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception Handler Class
 * Controller Cannot Catch The Exception, So We Must Handle It By Ourselves
 */
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * Handle All Custom Exception
     * @param ce Custom Exception
     * @return Result Json
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResJson handleCustomException(CustomException ce) {
        return ce.getResJson();
    }

    /**
     * Handle Args Error Exception
     * @param e Method Argument Not Valid Exception
     * @return Result Json
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResJson handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResJson.failure(ResCode.ARGS_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
