package cn.demo.designer.strategyandsimplefactory;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void test01(){
		CasherContext cc = new CasherContext("������");
		
		System.out.println(cc.getResult(100));
	}

}
