package com.waracle.cakemanager.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@Log4j2
public class CustomizesWaracleResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method is used to handle all types of exceptions.
     * @param e
     * @param req
     * @return WaracleResponseException
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<WaracleResponseException> handleExceptions(Exception e, WebRequest req) {
        WaracleResponseException waracleResponseException = new WaracleResponseException(new Date(), e.getMessage(), req.getDescription(false));
        log.error(e.getMessage());
        return new ResponseEntity<WaracleResponseException>(waracleResponseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is used to handle ConstraintViolationException and CustomCakeException.
     * @param e
     * @param req
     * @return WaracleResponseException.
     */
    @ExceptionHandler({ConstraintViolationException.class, CustomCakeException.class})
    public final ResponseEntity<WaracleResponseException> handleExceptions(RuntimeException e, WebRequest req) {
        WaracleResponseException waracleResponseException = new WaracleResponseException(new Date(), e.getMessage(), req.getDescription(false));
        log.error(e.getMessage());
        return new ResponseEntity<WaracleResponseException>(waracleResponseException, HttpStatus.BAD_REQUEST);
    }
}
