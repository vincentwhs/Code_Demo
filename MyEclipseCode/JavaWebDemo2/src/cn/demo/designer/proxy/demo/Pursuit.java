package cn.demo.designer.proxy.demo;

public class Pursuit implements IGiveGift{
	private SchoolGril girl;
	
	public Pursuit(SchoolGril girl){
		this.girl = girl;
	}
	@Override
	public void giveFlowers() {
		System.out.println("pursuit give gift to " + this.girl.getName());
	}

}
