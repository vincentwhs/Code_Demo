package cn.demo.designer.decorator;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void testDecorator(){
		Component component = new ConcreteComponent();
		Decorator decoratorA = new ConcreteDecoratorA();
		Decorator decoratorB = new ConcreteDecoratorB();
		decoratorA.setComponent(component);
		decoratorB.setComponent(decoratorA);
		
		decoratorB.operation();

	}

}
