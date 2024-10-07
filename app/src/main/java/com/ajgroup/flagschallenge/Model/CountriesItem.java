package com.ajgroup.flagschallenge.Model;

import com.google.gson.annotations.SerializedName;

public class CountriesItem{

	@SerializedName("country_name")
	private String countryName;

	@SerializedName("id")
	private int id;

	@SerializedName("Check")
	private boolean check = false;

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCountryName(String countryName){
		this.countryName = countryName;
	}

	public String getCountryName(){
		return countryName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CountriesItem{" + 
			"country_name = '" + countryName + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}