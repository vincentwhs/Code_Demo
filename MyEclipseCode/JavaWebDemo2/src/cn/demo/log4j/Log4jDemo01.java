package cn.demo.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;

import org.apache.log4j.*;

public class Log4jDemo01 {

	@Test
	public void test1(){
		
		Logger logger = Logger.getLogger(Log4jDemo01.class);  
        
        //使用默认的配置信息，不需要写log4j.properties  
        BasicConfigurator.configure();  
        //设置日志输出级别为info，这将覆盖配置文件中设置的级别  
        logger.setLevel(Level.ERROR);  
        //下面的消息将被输出  
        logger.info("this is an info");  
        logger.warn("this is a warn");  
        logger.error("this is an error");  
        logger.fatal("this is a fatal");  
	}
	
	
	@Test
	public void test_SimpleLayout_FileAppender() throws IOException{
		Logger logger = Logger.getLogger(Log4jDemo01.class);
		//SimpleLayout layout = new SimpleLayout();  
        //HTMLLayout  layout = new HTMLLayout(); 
        TTCCLayout layout = new TTCCLayout();
		FileAppender appender = null;//new FileAppender(layout, "out.txt", true);
		
		try {
			appender = new FileAppender(layout, "out.html", false);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("info");
		}
		logger.setLevel((Level)Level.INFO);
		logger.addAppender(appender);
		
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		logger.fatal("fatal");
		
				
	}
	
	@Test
	public void test_property(){
		PropertyConfigurator.configure( " D:/Code/conf/log4j.properties" );
        Logger logger  =  Logger.getLogger(Log4jDemo01.class );
        logger.debug( " debug " );
        logger.error( " error " );
	}
}







