package cn.demo.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 *  π”√ƒ¨»œlog4j2≈‰÷√
 */
public class Bar {

	static Logger logger = LogManager.getLogger(Bar.class);
	
	public boolean DoId(){
		logger.trace("entry Bar");
		logger.entry();
		logger.error("Do it again");
		return logger.exit(false);
	}
}
