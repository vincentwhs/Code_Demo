package cn.demo.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4j2Demo01 {
	
	static Logger logger = LogManager.getLogger(TestLog4j2Demo01.class);
	@Test
	public void test_log4j2_01(){
		logger.trace("entry");//等同于logger.entry();但此方法在新版本好像已经废弃
		//logger.entry();
		logger.error("did it agagin");
		
		logger.info("this is info");
		
		logger.debug("this is debug");
		
		logger.warn("this is warn");
		
		logger.fatal("this is fatal");
		
		logger.trace("exit");
		//logger.exit();
	}
	
	@Test
	public void test_log4j2_rollrandomaccessfile(){		 
	    Logger logger = LogManager.getLogger("rollingRandomAccessFile");  
	    for(int i = 0; i < 10; i++) {  
	        logger.trace("trace level");  
	        logger.debug("debug level");  
	        logger.info("info level");  
	        logger.warn("warn level");  
	        logger.error("error level");  
	        logger.fatal("fatal level");  
	    }  
	    try {  
	        Thread.sleep(1000 * 61);  
	    } catch (InterruptedException e) {}  
	    logger.trace("61 trace level");  
	    logger.debug("61 debug level");  
	    logger.info("61 info level");  
	    logger.warn("61 warn level");  
	    logger.error("61 error level");  
	    logger.fatal("61 fatal level");  
	}

}
