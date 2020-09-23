package com.openpayd.task.exception;

public class GeneralException extends RuntimeException{
	 private static final long serialVersionUID = -7003772011584189732L;
	  private final static String DEFAULT_MESSAGE = "Some problem occured";

	  public GeneralException() {
	    super(DEFAULT_MESSAGE);
	  }

	  public GeneralException(String message) {
	    super(message);
	  }
}
