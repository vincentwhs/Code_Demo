package cn.demo.designer.strategy;

import org.junit.Test;

public class TestDemo01 {
	
	@Test
	public void test01(){
		Context context = new Context(new ConcreteStrategyA());
		context.contextInterface();
		
		context = new Context(new ConcreteStrategyB());
		context.contextInterface();
		
	}

}
