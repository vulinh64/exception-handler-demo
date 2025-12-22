package com.vulinh.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  M000("Success"),
  M999("Internal Server Error"),
  M001("Data existed"),
  M400("Bad data"),
  M404("Not found");

  private final String message;
}
