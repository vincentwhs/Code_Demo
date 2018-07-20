package cn.demo.designer.simplefactory;

public class OperationFactory {
	
	public static OperationBase getOperation(String operation){
		switch (operation) {
		case "+":
			return new OperationAdd();
		case "-":
			return new OperationSub();
		default:
			return null;
		}
	}

}
