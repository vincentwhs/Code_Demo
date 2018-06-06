<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
    <script src="js/laydate/laydate.js"></script>

  </head>
  
  <body>
    <%= basePath %>
    
    <form action="${pageContext.request.contextPath }/servlet/RegisterServlet" method="post" ></form>
    <table>
    
    	<tr>
    		<td>登录帐号：
    		</td>
    		<td>
    			<input type="text" name="username" />
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>重复密码：
    		</td>
    		<td>
    			<input type="password" name="password" />
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>确认密码
    		</td>
    		<td>
    			<input type="password" name="repassword" />
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>电子邮件：
    		</td>
    		<td>
    			<input type="text" name="email" />
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>生日：
    		</td>
    		<td>
    			<!-- <input type="date" name="birthday" /> -->
    			<input type="text" id="birthday">
    			<script>
					//执行一个laydate实例
					laydate.render({
					  elem: '#birthday' //指定元素
					});
				</script>
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>昵称：
    		</td>
    		<td>
    			<input type="text" name="nickname" />
    		</td>
    		<td></td>
    	</tr>
    	
    	<tr>
    		<td>图片认证：
    		</td>
    		<td>
    			<input type="text" name="reconform" />
    		</td>
    		<td>
    			<img src="#" height="20px" />
    		</td>
    	</tr>
    	
    	<tr>
    	
    		<td></td>
    		<td>
    			<input type="reset"  value="重置"/>
    			&nbsp; &nbsp;
    			<input type="submit" value="注册" />
    		</td>
    		<td>
    		</td>
    	</tr>
    
    </table>
    
  </body>
</html>
