package cn.demo.log4j;

import java.io.File;
import java.io.IOException;

import javax.enterprise.inject.New;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4JInitServlet
 */
@WebServlet("/Log4JInitServlet")
public class Log4JInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4JInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	
    	System.out.println("Log4JInitServlet 正在初始化 log4j日志设置信息");  
    	String log4jLoaction = config.getInitParameter("log4j-properties-location");
    	
    	ServletContext sc = config.getServletContext();
    	
    	if (log4jLoaction == null) {
    		System.err.println("*** 没有 log4j-properties-location 初始化的文件, 所以使用 BasicConfigurator初始化");  
    		
    		BasicConfigurator.configure();
		}else {
			String realPath=sc.getRealPath("/");
			String p = sc.getContextPath();
			System.out.println("realPath:" + realPath);
			System.out.println("p:"+p);
			
			String log4jProp = realPath + log4jLoaction;
			File file = new File(log4jProp);
			
			if (file.exists()) {
				System.out.println("使用: " + log4jProp+"初始化日志设置信息");  
				PropertyConfigurator.configure(log4jProp);
			}else {
				System.err.println("*** " + log4jProp + " 文件没有找到， 所以使用 BasicConfigurator初始化"); 
				BasicConfigurator.configure();
			}
		}
    	
    	
    	
    	super.init(config);
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
