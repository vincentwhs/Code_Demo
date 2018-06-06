package cn.demo.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import com.sun.mail.handlers.message_rfc822;

public class ResponseDemo extends HttpServlet {

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//test3OutputStream(response);
		//test1Writer(response);
		//downFile(response);
		//randomPic(response);
		//refreshHeaer(response);
		//refreshHeaer2(request, response);
		expiresTest( response);
		
		
	}
	
	/*
	 * 请求重定�?
	 */
	private void sendRedirect(HttpServletResponse response) throws IOException{
		
		//方法�?：手动模�?
		response.setStatus(302);
		response.setHeader("location", "/JavaWebDemo/index.jsp");
		
		//方法二：api
		response.sendRedirect("/JavaWebDemo/index.jsp");
	}
	
	@Override
    protected long getLastModified(HttpServletRequest req) {
        System.out.println("modifi");
        /*将资源的路径从工程的路径转换到真实的路径*/
        String path = this.getServletContext().getRealPath("/JavaWebDemo/WEB-INF/classes/cn/example/servlet/ResponseDemo.class");
        File file = new File(path);
        return file.lastModified(); 
    }
	
	/*
	 * 设置浏览器缓存时�?
	 */
	private void expiresTest(HttpServletResponse response) throws IOException{
		
		String msg = "aaaaaa";
		
		//servlet页面默认是不缓存�?
		// 本页面允许在浏览器端或缓存服务器中缓存，时限�?20秒�??
		// 20秒之内重新进入该页面的话不会进入该servlet�?
		java.util.Date date = new java.util.Date();
		//response.setDateHeader("Last-Modified", date.getTime()); // Last-Modified:页面的最后生成时�?
		response.setDateHeader("Expires", date.getTime() + 1000*1800); // Expires:过时期限�?
		response.setHeader("Cache-Control", "public"); // Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息�?
		response.setHeader("Pragma", "Pragma"); // Pragma:设置页面是否缓存，为Pragma则缓存，no-cache则不缓存

		// 不允许浏览器端或缓存服务器缓存当前页面信息�??
		/*
		 * response.setHeader( "Pragma", "no-cache" );
		 * response.setDateHeader("Expires", 0); response.addHeader(
		 * "Cache-Control", "no-cache" );//浏览器和缓存服务器都不应该缓存页面信�?
		 * response.addHeader( "Cache-Control", "no-store"
		 * );//请求和响应的信息都不应该被存储在对方的磁盘系统中�? response.addHeader( "Cache-Control",
		 * "must-revalidate" );
		 *///于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
		 
		
		
		//response.setDateHeader("expires", System.currentTimeMillis() + 1000*1800);
		
		response.getWriter().write(msg);
		
		
	}
	
	/*
	 * 网页自动刷新 头设�? refresh
	 */
	private void refreshHeaer2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//假设这是�?个注册的页面
		
		//假设程序运行到此，用户登录成功了
//		String message = new String("<meta http-equiv='refresh' content='3;url=/JavaWebDemo/index.jsp' />恭喜你注册成功，本浏览器将在3秒后天转到首页，如果没有反映，请点击<a href=''>超链�?</a>".getBytes(),"utf-8");
		String message = "<meta http-equiv='refresh' content='3;url=/JavaWebDemo/index.jsp' />恭喜你注册成功，本浏览器将在3秒后天转到首页，如果没有反映，请点击<a href=''>超链�?</a>";
		this.getServletContext().setAttribute("data", message);
		this.getServletContext().getRequestDispatcher("/tiaozhuan.jsp").forward(request, response);
		
		
	}
	
	/*
	 * 网页自动刷新 头设�? refresh
	 */
	private void refreshHeaer(HttpServletResponse response) throws IOException{
		
		//假设这是�?个注册的页面
		
		//假设程序运行到此，用户登录成功了
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		response.setHeader("refresh", "3;url='/JavaWebDemo/index.jsp'");
		
		response.getWriter().write("恭喜你注册成功，本浏览器将在3秒后天转到首页，如果没有反映，请点击<a href=''>超链�?</a>");
		
	}
	
	/*
	 * 生成随机图片
	 * */
	private void randomPic(HttpServletResponse response) throws IOException{
		BufferedImage image = GenerateRandomPic.getImage();
		
		//图片写给浏览�?
		response.setContentType("image/JPEG");
	
		//设置不嫩缓存，避免按回车时不刷新图片
		//三个都要设置，不同浏览器的缓存头字段不一�?
		response.setDateHeader("expires", -1);
		response.setHeader("cache-control", "no-cache");
		response.setHeader("pragma", "no-cache");
		
		ImageIO.write(image, "jpg", response.getOutputStream());
		
	}
	
	
	private void downFile(HttpServletResponse response) throws MalformedURLException{
		String path = this.getServletContext().getRealPath("/download/中文.jpg");
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		
		InputStream in = null;
		OutputStream out = null;
		BufferedInputStream stream = null;
		try {
			in = new FileInputStream(path);
			stream = new BufferedInputStream(in);
			
			out = response.getOutputStream();
			
			//filename必须经过url编码
			response.setHeader("content-disposition",
					"attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
			
			
			byte[] b = new byte[1024];
			int len=0;
			while ((len = stream.read(b, 0, 1024)) > 0){
				out.write(b,0, len);
			}
		}
		catch (Exception e) {
		}finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void test1Writer(HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("UTF-8");
		//response.setHeader("content-type", "text/html;charset=utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String data = "中国";
		PrintWriter writer = response.getWriter();
		writer.write(data);
	}
	
	private void test3OutputStream(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		String data = "中国";
		
		//此处     ，charset=  错误，会导致程序下载   注意：chrome不支持，请用ie测试
		response.setHeader("content-type", "text/html,charset=utf-8");
		
		OutputStream out = response.getOutputStream();
		out.write(data.getBytes("UTF-8"));
	}
	
	//通过html标签meta设置消息格式  注意：chrome不支持，请用ie测试
	private void test2OutputStream(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		String data = "中国";
		
		
		OutputStream out = response.getOutputStream();
		out.write("<meta http-equiv='content-type' content='text/html;charset=utf-8'>".getBytes());
		out.write(data.getBytes("UTF-8"));
	}

	private void test1OutputStream(HttpServletResponse response) throws IOException, UnsupportedEncodingException {
		String data = "中国";
		
		//通过消息头设置编码格�?
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		OutputStream out = response.getOutputStream();
		out.write(data.getBytes("UTF-8"));
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
