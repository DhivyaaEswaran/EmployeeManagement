package com.ideas2it.exception;

/**
 * Here we add custom exception to EmployeeManagement
 */
public class UserDefinedException extends Exception {

	/**
	 * We handle the exception by getting expection input from user
	 * @param cause
	 */
	public UserDefinedException(Throwable cause) {
		super(cause);
	}

	/**
	 * We handle the exception by getting expection input from user
	 * @param message
	 * @param cause
	 */
	public UserDefinedException(String message,Throwable cause) {
		super(message,cause);
	}

	/**
	 * We handle the exception by getting expection input from user
	 * @param message
	 */
	public UserDefinedException(String message) {
		super(message);
	}
}