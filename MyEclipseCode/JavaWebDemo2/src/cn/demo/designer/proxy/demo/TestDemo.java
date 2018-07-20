package cn.demo.designer.proxy.demo;

import org.junit.Test;

public class TestDemo {
	@Test
	public void testProxy(){
		SchoolGril girl = new SchoolGril();
		girl.setName("mm");
		
		Proxy proxy = new Proxy(girl);
		proxy.giveFlowers();
	}
}
