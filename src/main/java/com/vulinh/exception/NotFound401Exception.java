package com.vulinh.exception;

import module java.base;

public class NotFound401Exception extends RuntimeException {

  @Serial private static final long serialVersionUID = 258388982163609812L;

  public NotFound401Exception(String name) {
    super("Name %s not found".formatted(name));
  }
}
