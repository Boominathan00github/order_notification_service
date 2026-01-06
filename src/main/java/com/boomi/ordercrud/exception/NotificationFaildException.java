package com.boomi.ordercrud.exception;

public class NotificationFaildException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorCode;

	public NotificationFaildException(String message) {

		super(message);
		this.errorCode = "NOTIFICATION_FAILD";
		// TODO Auto-generated constructor stub
	}

	public NotificationFaildException(String message, String errorCode) {

		super(message);
		this.errorCode = errorCode;
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		return errorCode;
	}

}
