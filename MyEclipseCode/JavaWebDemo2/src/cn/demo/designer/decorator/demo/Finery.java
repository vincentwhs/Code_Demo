package cn.demo.designer.decorator.demo;

import cn.demo.designer.decorator.Decorator;

public class Finery extends Person {
	private Person person;
	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void show(){
		if (person != null) {
			person.show();
		}
	}
}
