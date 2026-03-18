package com.vulinh.exception;

import com.vulinh.data.ErrorCode;
import com.vulinh.data.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseObject<Object> handleRuntimeException(RuntimeException ex) {
    log.error("Internal server error:", ex);

    return fromErrorCode(ErrorCode.M999);
  }

  @ExceptionHandler(IdenticalException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseObject<Object> handleIdenticalException(IdenticalException ex) {
    showExceptionMessage(ex);

    return fromErrorCode(ErrorCode.M001);
  }

  @ExceptionHandler(NotFound404Exception.class)
  public ResponseEntity<Object> handleNotFound404Exception(NotFound404Exception ex) {
    showExceptionMessage(ex);

    // Another way to return HttpStatus without @ResponseStatus
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fromErrorCode(ErrorCode.M404));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseObject<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
    showExceptionMessage(ex);

    return fromErrorCode(ErrorCode.M400);
  }

  private static ResponseObject<Object> fromErrorCode(ErrorCode errorCode) {
    return ResponseObject.builder().errorCode(errorCode).message(errorCode.getMessage()).build();
  }

  private static void showExceptionMessage(Throwable ex) {
    log.info("Error: {}", ex.getMessage());
  }
}
