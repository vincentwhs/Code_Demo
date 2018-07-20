package cn.demo.designer.proxy;

public class RealSubject implements Subject {

	@Override
	public void request() {
		System.out.println("realsubject do...");
	}

}
