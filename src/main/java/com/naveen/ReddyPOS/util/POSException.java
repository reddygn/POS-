package com.naveen.ReddyPOS.util;

public class POSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public POSException(String message) {
		super(message);
	}

	public POSException(String message, Throwable cause) {
		super(message, cause);
	}

}
