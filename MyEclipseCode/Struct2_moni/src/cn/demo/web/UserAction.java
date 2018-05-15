package cn.demo.web;

public class UserAction implements Action {

	@Override
	public String excute() {
		System.out.println("UserAction");
		return "error";
	}

}
