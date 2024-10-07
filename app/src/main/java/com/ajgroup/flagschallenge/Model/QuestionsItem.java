package com.ajgroup.flagschallenge.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuestionsItem{

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("imageurl")
	private String imageurl;

	@SerializedName("countries")
	private List<CountriesItem> countries;

	@SerializedName("answer_id")
	private int answerId;

	@SerializedName("marked_correctly")
	private boolean markedCorrectly;

	@SerializedName("isMarked")
	private boolean isMarked;

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setImageurl(String imageurl){
		this.imageurl = imageurl;
	}

	public String getImageurl(){
		return imageurl;
	}

	public void setCountries(List<CountriesItem> countries){
		this.countries = countries;
	}

	public List<CountriesItem> getCountries(){
		return countries;
	}

	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}

	public int getAnswerId(){
		return answerId;
	}

	public void setMarkedCorrectly(boolean markedCorrectly){
		this.markedCorrectly = markedCorrectly;
	}

	public boolean isMarkedCorrectly(){
		return markedCorrectly;
	}

	public void setIsMarked(boolean isMarked){
		this.isMarked = isMarked;
	}

	public boolean isIsMarked(){
		return isMarked;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsItem{" + 
			"country_code = '" + countryCode + '\'' + 
			",imageurl = '" + imageurl + '\'' + 
			",countries = '" + countries + '\'' + 
			",answer_id = '" + answerId + '\'' + 
			",marked_correctly = '" + markedCorrectly + '\'' + 
			",isMarked = '" + isMarked + '\'' + 
			"}";
		}
}