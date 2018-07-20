package cn.demo.designer.decorator;

public class Decorator extends Component{

	private Component component;
	
	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void operation() {
		if (component != null) {
			component.operation();
		}
	}

}
