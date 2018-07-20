package cn.demo.designer.decorator.demo;

import org.junit.Test;

public class TestDemo {

	@Test
	public void testDemo(){
		Person person= new Person("test");
		Finery fineryA = new TShirt();
		Finery fineryB = new Trouser();
		
		fineryA.setPerson(person);
		fineryB.setPerson(fineryA);
		
		fineryB.show();
	}
}
