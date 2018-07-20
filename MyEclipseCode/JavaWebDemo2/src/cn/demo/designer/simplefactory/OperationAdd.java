package cn.demo.designer.simplefactory;

public class OperationAdd extends OperationBase {

	@Override
	public double getResult(){
		return getNumberA() + getNumberB();
	}
}
