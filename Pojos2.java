package com.torus.a1test.en;

import com.codename1.io.Preferences;

public class Pojos2 {
	

	private int intLabelCount;
	

	public Pojos2() {
		
		intLabelCount = Preferences.get("CwCorrectAnswers",0) + Preferences.get("CorrectAnswers",0) 
		+ Preferences.get("FrasesCorrectAnswers",0) + Preferences.get("QuestionsCorrectAnswers",0);
		
	}
	
	public int getintLabelCount () {

		return intLabelCount;
	}

	public void setintLabelCount (int intLabelCount) {

		this.intLabelCount = intLabelCount;
	}
	

}
