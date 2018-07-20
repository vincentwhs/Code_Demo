package cn.demo.designer.strategy;

public class Context {
	private Strategy strategy;
	
	public Context(Strategy strategy){
		this.strategy = strategy;
	}
	
	public void contextInterface(){
		strategy.algorithmInterface();
	}

}
