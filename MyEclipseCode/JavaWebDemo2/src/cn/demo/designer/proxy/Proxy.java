package cn.demo.designer.proxy;

public class Proxy implements Subject {
	
	private RealSubject subject;

	@Override
	public void request() {
		if (subject == null) {
			subject = new RealSubject();
		}
		subject.request();
	}

}
