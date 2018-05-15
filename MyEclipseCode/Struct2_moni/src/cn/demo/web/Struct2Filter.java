package cn.demo.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Struct2Filter implements Filter {
	
	Map<String, String> map = new HashMap<String, String>();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		String path = request.getServletPath();
		if (path.equals("/test.jsp")) {
			chain.doFilter(arg0, arg1);
			return;
		}
		String resPath = "error";
		try {
			Action action = (Action) Class.forName(map.get(path)).newInstance();
			resPath = action.excute();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resPaths = "/" + resPath + ".jsp";
		request.getRequestDispatcher(resPaths).forward(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		map.put("/hellowordl/helloworldAction.action", "cn.demo.web.HelloworldAction");
		map.put("/primer/userAction.action.action", "cn.demo.web.UserAction");
	}

}
