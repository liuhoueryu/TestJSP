package com.neuedu.entity;

import java.io.Serializable;

public class Weather implements Serializable{
	private String province;
	private String city;
	private String weather;
	private String temperature;
	private String rainfall;
	private String date;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getRainfall() {
		return rainfall;
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
