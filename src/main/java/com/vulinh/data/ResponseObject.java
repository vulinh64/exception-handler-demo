package com.vulinh.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.With;

@Builder
@With
@JsonInclude(Include.NON_NULL)
public record ResponseObject<T>(ErrorCode errorCode, String message, T data) {

  public static <T> ResponseObject<T> of(T data) {
    return ResponseObject.<T>builder()
        .errorCode(ErrorCode.M000)
        .message(ErrorCode.M000.getMessage())
        .data(data)
        .build();
  }
}
