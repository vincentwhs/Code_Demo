<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
  </head>
  
  <body>

	<a href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a>
	<a href="">登录</a>    


  </body>
</html>
