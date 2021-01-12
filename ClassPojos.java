package com.torus.A1;

import java.util.ArrayList;

import com.codename1.io.Preferences;
import com.codename1.util.Wrapper;

public class ClassPojos {

	private ArrayList <String> listDialog; 
	private String stringOneCw;
	private String stringTwoCw;
	private String stringThreeCw;
	private String stringFourCw;
	private String stringFirstRun;
	private String stringLevel;
	private String stringRadioFrases;
	private String stringControlTextQuestions;
	private String stringPreferences; 
	private int intCorrects;
	private int intFalse;
	private int intTotal;
	
	
	public ClassPojos() {
		
		listDialog = new ArrayList<String>();
		stringOneCw = "";
		stringTwoCw = "";
		stringThreeCw = "";
		stringFourCw = "";
		stringFirstRun = "";
		stringLevel = "";
		stringRadioFrases = "";
		stringControlTextQuestions = "";
		stringPreferences = "";
		intCorrects = 0;
		intFalse = 0;
		intTotal = 0;
	}
	
	public int getintCorrects () {

		return intCorrects;
	}

	public void setintCorrects (int intCorrects) {

		this.intCorrects = intCorrects;
	}
	
	public int getintFalse () {

		return intFalse;
	}

	public void setintFalse (int intFalse) {

		this.intFalse = intFalse;
	}
	
	
	public int getintTotal () {

		return intTotal;
	}

	public void setintTotal (int intTotal) {

		this.intTotal = intTotal;
	}
	
	
	public ArrayList getDialogList () {

		return listDialog;
	}

	public void setDialogList (ArrayList listDialog) {

		this.listDialog = listDialog;

	}
	
	public String getStringOneCw () {

		return stringOneCw;
	}

	public void setStringOneCw (String stringOneCw) {

		this.stringOneCw = stringOneCw;

	}
	
	public String getStringTwoCw () {

		return stringTwoCw;
	}

	public void setStringTwoCw (String stringTwoCw) {

		this.stringTwoCw = stringTwoCw;

	}
	
	public String getStringThreeCw () {

		return stringThreeCw;
	}

	public void setStringThreeCw (String stringThreeCw) {

		this.stringThreeCw = stringThreeCw;

	}
	
	public String getStringFourCw () {

		return stringFourCw;
	}

	public void setStringFourCw (String stringFourCw) {

		this.stringFourCw = stringFourCw;

	}
	
	
	public String getStringFirstRun () {

		return stringFirstRun;
	}

	public void setStringFirstRun (String stringFirstRun) {

		this.stringFirstRun = stringFirstRun;

	}
	
	public String getStringControlTextQuestions () {

		return stringControlTextQuestions;
	}

	public void setStringControlTextQuestions (String stringControlTextQuestions) {

		this.stringControlTextQuestions = stringControlTextQuestions;

	}

	
	public String getStringLevel () {

		return stringLevel;
	}

	public void setStringLevel (String stringLevel) {

		this.stringLevel = stringLevel;

	}
	
	
	public String getStringPreferences () {

		return stringPreferences;
	}

	public void setStringPreferences (String stringPreferences) {

		this.stringPreferences = stringPreferences;

	}
	
	public String getStringRadioFrases() {

		return stringRadioFrases;
	}

	public void setStringRadioFrases (String stringRadioFrases) {

		this.stringRadioFrases = stringRadioFrases;

	}
	
	

}
