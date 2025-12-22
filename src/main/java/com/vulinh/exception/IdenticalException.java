package com.vulinh.exception;

import module java.base;

public class IdenticalException extends RuntimeException {

  @Serial private static final long serialVersionUID = -6053279381658020144L;

  public IdenticalException(String name) {
    super("Name %s existed".formatted(name));
  }
}
