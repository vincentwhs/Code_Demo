package cn.demo.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 *  π”√ƒ¨»œlog4j2≈‰÷√
 */
public class MyApp {
	
	static Logger logger = LogManager.getLogger(MyApp.class);
	
	public static void main(String... args){
		logger.trace("entry application");
		Bar bar = new Bar();
		if (!bar.DoId()) {
			logger.error("Didn't do it");
		}
		logger.trace("exiting application");
	}

}
