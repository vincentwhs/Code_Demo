package cn.demo.designer.strategy.simple;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void test01(){
		CasherSuper cs = CasherFactory.getCasher("´òÎåÕÛ");
		double total = cs.acceptCaher(100);
		
		System.out.println(total);
	}

}
