package com.neuedu.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.Weather;
import com.neuedu.util.DBManager;

public class WeatherQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet);

		
		// 转码
		request.setCharacterEncoding("utf-8");
		// 接收数据
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		
		if (city == null)
			city = "";
		if (province == null)
			province = "";
		DBManager dbManager = DBManager.getInstance();
		String sql = "select * from weather where city like ? and province like ? order by province asc, city asc,date asc";
		ResultSet rs = dbManager.execQuery(sql, "%" + city + "%", "%" + province + "%");
		List<Weather> list = new ArrayList<>();

		try {
			while (rs.next()) {
				Weather weather = new Weather();
				weather.setProvince(rs.getString(1));
				weather.setCity(rs.getString(2));
				weather.setWeather(rs.getString(3));
				weather.setTemperature(rs.getString(4));
				weather.setRainfall(rs.getString(5));
				weather.setDate(rs.getString(6));

				list.add(weather);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}
		request.setAttribute("list", list);
		request.setAttribute("province", province);
		request.setAttribute("city", city);
		request.getRequestDispatcher("weather_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
