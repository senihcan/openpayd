package com.openpayd.task.exception;

public class NotFoundException extends RuntimeException{
	  private static final long serialVersionUID = -7003772011584189732L;
	  private final static String DEFAULT_MESSAGE = "Data can't be found";

	  public NotFoundException() {
	    super(DEFAULT_MESSAGE);
	  }

	  public NotFoundException(String message) {
	    super(message);
	  }
}
