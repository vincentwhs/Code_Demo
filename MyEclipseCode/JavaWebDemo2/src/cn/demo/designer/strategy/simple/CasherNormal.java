package cn.demo.designer.strategy.simple;

public class CasherNormal extends CasherSuper {

	public CasherNormal(){
	}
	
	@Override
	public double acceptCaher(double money) {

		return money ;
	}

}
