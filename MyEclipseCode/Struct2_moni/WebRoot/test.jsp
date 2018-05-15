<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
    </head>
  <body>
       入门的路径:<br>  
      <a href="${pageContext.request.contextPath }/hellowordl/helloworldAction.action">helloworld</a>
      <a href="${pageContext.request.contextPath }/primer/userAction.action.action">userworld</a>
  </body>
</html>
