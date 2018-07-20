package cn.demo.designer.decorator;

public class ConcreteDecoratorB extends Decorator {

	private void addBehavior() {
		
	}
	
	@Override
	public void operation(){
		addBehavior();
		
		super.operation();
		
		System.out.println("ConcreteDecoratorB");
	}
}
