package cn.demo.designer.simplefactory;

public class OperationSub extends OperationBase {

	@Override
	public double getResult(){
		return getNumberA()-getNumberB();
	}
}
