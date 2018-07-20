package cn.demo.designer.proxy.demo;

public class Proxy implements IGiveGift{

	private Pursuit pursuit;
	public Proxy(SchoolGril girl){
		pursuit = new Pursuit(girl);
	}
	@Override
	public void giveFlowers() {
		pursuit.giveFlowers();
	}

}
