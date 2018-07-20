package cn.demo.designer.simplefactory;

public class OperationBase {
	
	private double numberA;
	private double numberB;
	public double getNumberB() {
		return numberB;
	}

	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}

	public double getNumberA() {
		return numberA;
	}

	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	
	public double getResult(){
		return 0;
	}

}
