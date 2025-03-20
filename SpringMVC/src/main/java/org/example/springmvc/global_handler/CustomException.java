package org.example.springmvc.global_handler;

public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
