package com.example.account.exception;

import com.example.account.domain.Account;
import com.example.account.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.account.type.ErrorCode.INVALID_REQUEST;
import static com.example.account.type.ErrorCode.INVALID_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ErrorResponse handelAccountException(AccountException e){
        log.error("{} is occurred. ", e.getErrorCode());

        return new ErrorResponse(e.getErrorCode(),e.getErrorMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handelMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException is occurred. ", e);

        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handelDataIntegrityViolationException(DataIntegrityViolationException e){
        log.error("DataIntegrityViolationException is occurred. ", e);

        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handelException(AccountException e){
        log.error("Exception is occurred. ", e);

        return new ErrorResponse(INVALID_SERVER_ERROR,INVALID_SERVER_ERROR.getDescription());
    }
}
