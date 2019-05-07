package org.amura.interview.client.exception;

public class InvalidMatrixException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int errorCode = 12345;
	private String message = "The Specified Matrix is Invalid";

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
