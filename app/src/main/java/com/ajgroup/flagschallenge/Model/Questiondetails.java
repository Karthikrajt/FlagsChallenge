package com.ajgroup.flagschallenge.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Questiondetails{

	@SerializedName("questions")
	private List<QuestionsItem> questions;

	public void setQuestions(List<QuestionsItem> questions){
		this.questions = questions;
	}

	public List<QuestionsItem> getQuestions(){
		return questions;
	}

	@Override
 	public String toString(){
		return 
			"Questiondetails{" + 
			"questions = '" + questions + '\'' + 
			"}";
		}
}