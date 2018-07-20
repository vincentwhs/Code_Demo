package cn.demo.designer.strategyandsimplefactory;

public class CasherRebate extends CasherSuper {
	private double rate=1d;
	public CasherRebate(String rate){
		this.rate = Double.parseDouble(rate);
	}
	
	@Override
	public double acceptCaher(double money) {

		return money * rate;
	}
}
