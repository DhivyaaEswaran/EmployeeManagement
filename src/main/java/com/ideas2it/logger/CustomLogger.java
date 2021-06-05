package com.ideas2it.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * In this class we perform debug, error, info, fatal, warn messages to properties
 */
public class CustomLogger {

	private Logger logger;

	/**
	 * Here we log className from other class
	 * @param className
	 */
	public CustomLogger(Class<?> className) {
		logger = LogManager.getLogger(className);
	}

	/**
	 * Here we log debug with message
	 * @param message
	 */
	public void debug(String message) {
		logger.debug(message);
	}

	/**
	 * Here we log error with message
	 * @param message
	 */
	public void error(String message) {
		logger.error(message);
	}

	/**
	 * Here we log error with message and exception
	 * @param message
	 * @param error
	 */
	public void error(String message, Exception error) {
		logger.error(message, error);
	}
	/**
	 * Here we log info with message
	 * @param message
	 */
	public void info(String message) {
		logger.info(message);
	}

	/**
	 * Here we log fatal with message
	 * @param message
	 */
	public void fatal(Exception message) {
		logger.fatal(message);
	}

	/**
	 * Here we log warn with message
	 * @param Message
	 */
	public void warn(Exception message) {
		logger.warn(message);
	}
	
	/**
	 * Here we log trace with message
	 * @param Message
	 */
	public void trace(Exception message) {
		logger.trace(message);
	}
}