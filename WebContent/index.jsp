<%@page import="com.neuedu.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>

<%
	//登录检查
	if(session.getAttribute("user")==null){   //未登录
%>
		
		<script>
			alert("对不起，您尚未登录，请先登录");
			location = "user_login.jsp";
		</script>	
	 
	 
	         //  对不起，您尚未登录，请先<a href="user_login.jsp">登录</a>
<%		
		return;
	}
%>

<%
	//从session属性范围中取出用户名，积分,头像
	/* String username = (String)session.getAttribute("username");
	int score = (Integer)session.getAttribute("score");
	String photo = (String)session.getAttribute("photo"); */
	
	//从session属性范围中取出user对象
	User user = (User)session.getAttribute("user");
	
	//从application属性范围中取出在线人数
	int onlineCount = (Integer)application.getAttribute("onlineCount");
%>

<center><img src="image/photo/<%=user.getPhoto()%>">，欢迎【<%=user.getUsername() %>】光临主页，当前积分为【<%=user.getScore() %>】分，您是第【<%=onlineCount %>】位访客

<br/>
<a href="WeatherQueryServlet">天气查询</a>
<br/>
<a href="UserLogoutServlet">注销</a>
</center>
</body>
</html>