package com.capita.exception;

/**
 * @author SauravChopra
 *
 */
public class CaculatorCustomException extends Exception {

	/**
	 * Created Custom exception class
	 */
	private static final long serialVersionUID = 1L;

	public CaculatorCustomException(String message) {
        super(message);
    }
}
