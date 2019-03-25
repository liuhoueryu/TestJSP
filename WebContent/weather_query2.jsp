<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

html, body {
	width: 100%;
	height: 100%;
}

div {
	width: 100%;
	height: 100%;
}
</style>
<body>
	<%
		//转码
		request.setCharacterEncoding("utf-8");
		//接收数据
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		if (city == null)
			city = "";
		if (province == null)
			province = "";
	%>
	<div style="background-image: url('img/timg.jpg');">
		<div align="center">
			<br>
			<br>
			<br>
			<form action="weather_query.jsp" method="post">
				请输入省份<input type="text" name="province" value="<%=province%>" />
				请输入城市<input type="text" name="city" value="<%=city%>" /> <input
					type="submit" value="查询" /><br />
			</form>
			<br>
			<br>
			<table border="1" data-pagination="true"
			data-side-pagination="client"
			data-page-size="10" >
				<%
					DBManager dbManager = DBManager.getInstance();
					String sql = "select * from weather where city like ? and province like ? order by province asc, city asc,date asc";
					ResultSet rs = dbManager.execQuery(sql, "%" + city + "%", "%" + province + "%");
					out.print("<tr><td>省份</td><td>城市</td><td>天气</td><td>温度</td><td>降水量</td><td>日期</td></tr>");
					while (rs.next()) {
						out.print("<tr>");
						out.print("<td>" + StringUtil.convertKeyword(rs.getString(1), province) + "</td>");
						out.print("<td>" + StringUtil.convertKeyword(rs.getString(2), city) + "</td>");
						out.print("<td>" + rs.getString(3) + "</td>");
						out.print("<td>" + rs.getString(4) + "</td>");
						out.print("<td>" + rs.getString(5) + "</td>");
						out.print("<td>" + StringUtil.convertKeyword(rs.getString(6), city) + "</td>");
						out.print("</tr>");
					}
					dbManager.closeConnection();
				%>


			</table>
		</div>
	</div>
</body>
</html>