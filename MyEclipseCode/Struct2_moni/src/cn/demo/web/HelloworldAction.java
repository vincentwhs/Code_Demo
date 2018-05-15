package cn.demo.web;

public class HelloworldAction implements Action {

	@Override
	public String excute() {
		System.out.println("helloworldaction");
		String s = "sa";
		if (!"sa".equals(s)) {
			return "error";
		}
		return "success";
	}

}
