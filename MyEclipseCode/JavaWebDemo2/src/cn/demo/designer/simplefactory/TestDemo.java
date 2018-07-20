package cn.demo.designer.simplefactory;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void test01(){
		OperationBase oper = OperationFactory.getOperation("+");
		oper.setNumberA(1.0);
		oper.setNumberB(2.2);
		System.out.println(oper.getResult());
	}

}
