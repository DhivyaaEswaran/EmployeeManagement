package com.dhivyaa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dhivyaa {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(Dhivyaa.class);
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        logger.fatal("fatal");
	}

}
