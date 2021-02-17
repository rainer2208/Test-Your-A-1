/**
 * 
 */
package com.torus.A1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rainer
 *
 */
public class ClassInputArrays {
	
	// ClassCrossWords	
	public ArrayList<List<String>> listCrossButtonPopups = new ArrayList<List<String>>();
	public ArrayList<List<List<Integer>>> listCrossControlIntegers = new ArrayList<List<List<Integer>>>();
	public ArrayList<List<String>> listCrossControlStrings = new ArrayList<List<String>>();
	public ArrayList<String>listCrossExplanation = new ArrayList<String>(); 
	public ArrayList<List<String>> listCrossLetters = new ArrayList<List<String>>(); 
	public ArrayList<List<String>> listCrossWords = new ArrayList<List<String>>(); 

	// ClassWord
	public ArrayList<String>listExplanation = new ArrayList<String>(); 
	public ArrayList<String> listLabel = new ArrayList<String>(); 
	public ArrayList<List<String>> listButtons = new ArrayList<List<String>>(); 
	public ArrayList<String> listcontrolStrings = new ArrayList<String>();

	// ClassFrases
	public ArrayList<String> listFrasesHeaders = new ArrayList<String>(); 
	public ArrayList<List<String>> listFrasesOption = new ArrayList<List<String>>(); 
	public ArrayList<String> listFrasescontrolStrings = new ArrayList<String>(); 
	public ArrayList<String>listFrasesExplanation = new ArrayList<String>(); 
	
	// ClassTextQuestions
	public ArrayList<String>listReadingTexts = new ArrayList<String>(); 
	public ArrayList<List<String>> listReadingButtons = new ArrayList<List<String>>();
	public ArrayList<String>listReadingTextControls = new ArrayList<String>();
	public ArrayList<String>listReadingQuestions = new ArrayList<String>();
	public ArrayList<String>listReadingExplanation = new ArrayList<String>();

	public void crossWordsCrosses() {

		String [] listOne = {"","","","ButtonOne","","",	
 				"","","","B","","ButtonThree",
     			"","","","A","","D",
     			"","","","N","","I",
     "ButtonTwo","T","R","A","G","E",
     			"","","","N","","",
     			"","","","E","","",
     			"","","","N","",""};		
		List<String> list1 = Arrays.asList(listOne);

		String [] listTwo = {	
			    	"","","","ButtonOne","","",	
		 				"","","","S","","",
		     "ButtonTwo","M","A","C","H","",
		     			"","","","H","","",
	   	                "","","","W","","",
		     			"","","","E","","",
		   "ButtonThree","N","A","S","E","N",
		     			"","","","T","","",
		     			"","","","E","","",
		     			"","","","R","",""
		};
		List<String> list2 = Arrays.asList(listTwo);
		
		String [] listThree = {	
		    	"","","ButtonOne","","","",	
	 				"","","G","","","",
	                "","","E","","","",
	     			"","","G","","","",
   	                "","","E","","","",
	       "","ButtonTwo","S","I","N","D",
	                "","","S","","","",
	     			"","","E","","","",
	    "ButtonThree","U","N","T","E","R"	     			
		};		
		List<String> list3 = Arrays.asList(listThree);		
		
		String [] listFour = {	
		    	"","","","ButtonOne","","",	
	 				"","","","K","","",
	                "","","","Ä","","",
	     			"","","","S","","",
   	      "","ButtonTwo","M","E","I","N",
	     			"","","","B","","",
	   "ButtonThree","G","E","R","N","",
	     			"","","","O","","",
	      "","","ButtonFour","T","E","E",
		};
		List<String> list4 = Arrays.asList(listFour);		
		
		String [] listFive = {	
		    	"","","","ButtonOne","","",	
	 	   "","ButtonTwo","","M","","",
	               "","K","","O","","",
	     		   "","A","","R","","",
   	   "ButtonThree","T","A","G","E","",
	     		   "","Z","","E","","",
	     "ButtonFour","E","I","N","E","N",
	     		   "","N","","S","","",
		};
		List<String> list5 = Arrays.asList(listFive);	
		
		String [] listSix = {	
		    	"","","","","ButtonOne","",	
	 	  "","","ButtonTwo","","A","",
	              "","","G","","B","",
	  "ButtonThree","B","E","R","G","E",
   	               "","","S","","E","",
	     		   "","","U","","H","",
	               "","","N","","O","",
	    "","ButtonFour","G","E","L","D",
	     		   "","","E","","T","",
	     		   "","","N","","","",
		};
		List<String> list6 = Arrays.asList(listSix);	
		
		String [] listSeven = {	
	       "","ButtonOne","","","ButtonTwo","",	
	 	           "","D","","","G","",
	   "ButtonThree","E","N","K","E","L",
	               "","Z","","","T","",
   	             "F","E","I","E","R","ButtonFour",
	     		   "","M","","","U","",
	               "","B","","","N","",
	               "","E","","","K","",
	     		   "","R","","","E","",
	     	  	    "","","","","N","",
		};
		List<String> list7 = Arrays.asList(listSeven);	
		
		listCrossWords.add(list1);
		listCrossWords.add(list2);
		listCrossWords.add(list3);
		listCrossWords.add(list4);
		listCrossWords.add(list5);
		listCrossWords.add(list6);
		listCrossWords.add(list7);
		
	}
	
	public void croswordCheck() {
		
		ArrayList<List<Integer>> listCrossControlIntegersOne = new ArrayList<List<Integer>>();		
		List<Integer> listIntsOneOne = new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8, 9));
		List<Integer> listIntsOneTwo = new ArrayList<Integer>(Arrays.asList(0, 1, 3, 7, 10, 11, 12));
		List<Integer> listIntsOneThree = new ArrayList<Integer>(Arrays.asList(2, 4, 9));
		listCrossControlIntegersOne.add(listIntsOneOne);
		listCrossControlIntegersOne.add(listIntsOneTwo);	
		listCrossControlIntegersOne.add(listIntsOneThree);
		listCrossControlIntegers.add(listCrossControlIntegersOne);
		
		ArrayList<List<Integer>> listCrossControlIntegersTwo = new ArrayList<List<Integer>>();		
		List<Integer> listIntsTwoOne = new ArrayList<Integer>(Arrays.asList(0, 3, 5, 6, 7, 10, 13, 14, 15));
		List<Integer> listIntsTwoTwo = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		List<Integer> listIntsTwoThree = new ArrayList<Integer>(Arrays.asList(8, 9, 10, 11, 12));
		listCrossControlIntegersTwo.add(listIntsTwoOne);
		listCrossControlIntegersTwo.add(listIntsTwoTwo);
		listCrossControlIntegersTwo.add(listIntsTwoThree);
		listCrossControlIntegers.add(listCrossControlIntegersTwo);
		
		ArrayList<List<Integer>> listCrossControlIntegersThree = new ArrayList<List<Integer>>();		
		List<Integer> listIntsThreeOne = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 8, 9, 11));
		List<Integer> listIntsThreeTwo = new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7));
		List<Integer> listIntsThreeThree = new ArrayList<Integer>(Arrays.asList( 10, 11, 12, 13, 14));
		listCrossControlIntegersThree.add(listIntsThreeOne);
		listCrossControlIntegersThree.add(listIntsThreeTwo);
		listCrossControlIntegersThree.add(listIntsThreeThree);
		listCrossControlIntegers.add(listCrossControlIntegersThree);
		 
		ArrayList<List<Integer>> listCrossControlIntegersFour = new ArrayList<List<Integer>>();
		List<Integer> listIntsFourOne = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 4, 7, 10, 12, 13));
		List<Integer> listIntsFouTwo = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 6));
		List<Integer> listIntsFourThree = new ArrayList<Integer>(Arrays.asList(8, 9, 10, 11));
		List<Integer> listIntsFourFour = new ArrayList<Integer>(Arrays.asList(13, 14, 15));
		listCrossControlIntegersFour.add(listIntsFourOne);
		listCrossControlIntegersFour.add(listIntsFouTwo);
		listCrossControlIntegersFour.add(listIntsFourThree);
		listCrossControlIntegersFour.add(listIntsFourFour);
		listCrossControlIntegers.add(listCrossControlIntegersFour);
		
		ArrayList<List<Integer>> listCrossControlIntegersFive = new ArrayList<List<Integer>>();
		List<Integer> listIntsFiveOne = new ArrayList<Integer>(Arrays.asList(0, 2, 4, 7, 8, 13, 17));
		List<Integer> listIntsFiveTwo = new ArrayList<Integer>(Arrays.asList( 1, 3, 5, 9, 10, 13));
		List<Integer> listIntsFiveThree = new ArrayList<Integer>(Arrays.asList( 5, 6, 7, 8));
		List<Integer> listIntsFiveFour = new ArrayList<Integer>(Arrays.asList( 11, 12, 13, 14, 15));
		listCrossControlIntegersFive.add(listIntsFiveOne);
		listCrossControlIntegersFive.add(listIntsFiveTwo);
		listCrossControlIntegersFive.add(listIntsFiveThree);
		listCrossControlIntegersFive.add(listIntsFiveFour);
		listCrossControlIntegers.add(listCrossControlIntegersFive);
		
		ArrayList<List<Integer>> listCrossControlIntegersSix = new ArrayList<List<Integer>>();
		List<Integer> listIntsSixOne = new ArrayList<Integer>(Arrays.asList(0, 2, 6, 7, 11, 13, 16, 19));
		List<Integer> listIntsSixTwo = new ArrayList<Integer>(Arrays.asList( 1, 4, 8, 10, 12, 14, 15, 20));
		List<Integer> listIntsSixThree = new ArrayList<Integer>(Arrays.asList(2, 4, 5, 6, 7));
		List<Integer> listIntsSixFour = new ArrayList<Integer>(Arrays.asList( 14, 15, 16, 17));
		listCrossControlIntegersSix.add(listIntsSixOne);
		listCrossControlIntegersSix.add(listIntsSixTwo);
		listCrossControlIntegersSix.add(listIntsSixThree);
		listCrossControlIntegersSix.add(listIntsSixFour);
		listCrossControlIntegers.add(listCrossControlIntegersSix);
		
		ArrayList<List<Integer>> listCrossControlIntegersSeven = new ArrayList<List<Integer>>();
		List<Integer> listIntsSevenOne = new ArrayList<Integer>(Arrays.asList(0, 2, 7, 10, 14, 16, 18, 20));
		List<Integer> listIntsSevenTwo = new ArrayList<Integer>(Arrays.asList( 1, 2, 8, 13, 15, 17, 19, 21, 22));
		List<Integer> listIntsSevenThree = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5, 6));
		List<Integer> listIntsSevenFour = new ArrayList<Integer>(Arrays.asList(9, 10, 11, 12, 13));
		listCrossControlIntegersSeven.add(listIntsSevenOne);
		listCrossControlIntegersSeven.add(listIntsSevenTwo);
		listCrossControlIntegersSeven.add(listIntsSevenThree);
		listCrossControlIntegersSeven.add(listIntsSevenFour);
		listCrossControlIntegers.add(listCrossControlIntegersSeven);
		
		ArrayList<String> listCrossControlStringsOne = new ArrayList<String>();
		listCrossControlStringsOne.add("TRAGE");
		listCrossControlStringsOne.add("BANANEN");
		listCrossControlStringsOne.add("DIE");
		
		ArrayList<String> listCrossControlStringsTwo = new ArrayList<String>();
		listCrossControlStringsTwo.add("SCHWESTER");
		listCrossControlStringsTwo.add("MACH");
		listCrossControlStringsTwo.add("NASEN");
		
		ArrayList<String> listCrossControlStringsThree = new ArrayList<String>();
		listCrossControlStringsThree.add("GEGESSEN");
		listCrossControlStringsThree.add("SIND");
		listCrossControlStringsThree.add("UNTER");
		
		ArrayList<String> listCrossControlStringsFour = new ArrayList<String>();
		listCrossControlStringsFour.add("KÄSEBROT");
		listCrossControlStringsFour.add("MEIN");
		listCrossControlStringsFour.add("GERN");
		listCrossControlStringsFour.add("TEE");
		
		ArrayList<String> listCrossControlStringsFive = new ArrayList<String>();
		listCrossControlStringsFive.add("MORGENS");
		listCrossControlStringsFive.add("KATZEN");
		listCrossControlStringsFive.add("TAGE");
		listCrossControlStringsFive.add("EINEN");
		
		ArrayList<String> listCrossControlStringsSix = new ArrayList<String>();
		listCrossControlStringsSix.add("ABGEHOLT");
		listCrossControlStringsSix.add("GESUNGEN");
		listCrossControlStringsSix.add("BERGE");
		listCrossControlStringsSix.add("GELD");
		
		ArrayList<String> listCrossControlStringsSeven = new ArrayList<String>();
		listCrossControlStringsSeven.add("DEZEMBER");
		listCrossControlStringsSeven.add("GETRUNKEN");
		listCrossControlStringsSeven.add("ENKEL");
		listCrossControlStringsSeven.add("FEIER");
		
		listCrossControlStrings.add(listCrossControlStringsOne);
		listCrossControlStrings.add(listCrossControlStringsTwo);
		listCrossControlStrings.add(listCrossControlStringsThree);
		listCrossControlStrings.add(listCrossControlStringsFour);
		listCrossControlStrings.add(listCrossControlStringsFive);
		listCrossControlStrings.add(listCrossControlStringsSix);
		listCrossControlStrings.add(listCrossControlStringsSeven);
		
	}
	
	public void crossWordExplain () {
		
		String stringOne = "1. TRAGE\n2.BANANEN\n3.DIE";
		String stringTwo = "1. SCHWESTER\n2. MACH\n3. NASEN";
		String stringThree = "1. GEGESSEN\n2. SIND\n3. UNTER";
		String stringFour = "1. KÄSEBROT\n2. MEIN\n3. GERN\n4. TEE";
		String stringFive = "1. MORGENS\n2. KATZEN\n3. TAGE\n4. EINEN";
		String stringSix = "1. ABGEHOLT\n2. GESUNGEN\n3. BERGE\n4. GELD";
		String stringSeven = "1. DEZEMBER\n2 .GETRUNKEN\n3 ENKEL.\n4. FEIER";
		
		listCrossExplanation.add(stringOne);
		listCrossExplanation.add(stringTwo);
		listCrossExplanation.add(stringThree);
		listCrossExplanation.add(stringFour);
		listCrossExplanation.add(stringFive);
		listCrossExplanation.add(stringSix);
		listCrossExplanation.add(stringSeven);

		
	}
	
	public void crossWordLetters() {
		
		List<String> listLettersOne = new ArrayList<String>(Arrays.asList("B","A","N","A","N","E","N","T","R","G","E","C","O","D","I")); 
		List<String> listLettersTwo = new ArrayList<String>(Arrays.asList("S","C","H","W","E","S","T","E","R","M","A","H","N","A","E","N","B","K"));
		List<String> listLettersThree = new ArrayList<String>(Arrays.asList("G","E","G","E","S","S","E","N","S","I","D","U","T","E","R","T","N","M"));
		List<String> listLettersFour = new ArrayList<String>(Arrays.asList("K","Ä","S","E","B","R","O","T","M","I","N","G","E","N","T","E","E","K"));
		List<String> listLettersFive = new ArrayList<String>(Arrays.asList("M","O","R","G","E","N","S","K","A","T","Z","E","N","A","E","I","E","N"));
		List<String> listLettersSix = new ArrayList<String>(Arrays.asList("A","B","G","E","H","L","T","G","S","U","N","G","E","N","B","R","E","G","D","T","E","E","O","D"));
		List<String> listLettersSeven = new ArrayList<String>(Arrays.asList("D","E","Z","C","E","M","B","E","R","G","E","T","R","U","N","K","E","N",
				"N","K","L","F","I","E"));
		
		listCrossLetters.add(listLettersOne);
		listCrossLetters.add(listLettersTwo);
		listCrossLetters.add(listLettersThree);
		listCrossLetters.add(listLettersFour);
		listCrossLetters.add(listLettersFive);
		listCrossLetters.add(listLettersSix);
		listCrossLetters.add(listLettersSeven);
		
	}
	
	public void crossWordPopUpString() {
		
		List<String> listPopupOne = new ArrayList<String>(Arrays.asList("Tropische Frucht, Plural","Verb 'tragen', ich : ... ","bestimmter Artikel, feiminin")); 
		List<String> listPopupTwo = new ArrayList<String>(Arrays.asList("Eva ist meine kleine ..... ","Verb 'machen', Imperativ", "Sie ist zum Riechen (Plural)"));
		List<String> listPopupThree = new ArrayList<String>(Arrays.asList("Opa hat gestern Pizza ..... ","Danach .... sie ins Kino gefahren.", "Das Buch ist .... dem Bett."));
		List<String> listPopupFour = new ArrayList<String>(Arrays.asList("Ein Brot mit Käse ist ein ....","Possessivar- tikel von: ich",
				"Arbeiten ist schön. Ich arbeite ...","Möchten Sie Kaffee oder ....?")); 
		List<String> listPopupFive = new ArrayList<String>(Arrays.asList("Ein Synonym für: jeden Morgen.","Ein Tier (Plural). Viele Leute haben es.",
				"Die Woche hat sieben ...","Unbestimmter Artikel, Akkusativ")); 
		List<String> listPopupSix = new ArrayList<String>(Arrays.asList("Mein Bruder hat Oma am Flughafen ........","Sie haben ein Lied .......",
				"Die Alpen sind ......","Damit kann man bezahlen.")); 
		List<String> listPopupSeven = new ArrayList<String>(Arrays.asList("Weihnachten ist im ........","Alle sind gekommen und haben Wein .......",
				"Die ...... von Oma waren auch da.","Es war eine sehr schöne ...... .")); 
		
		listCrossButtonPopups.add(listPopupOne);
		listCrossButtonPopups.add(listPopupTwo);
		listCrossButtonPopups.add(listPopupThree);
		listCrossButtonPopups.add(listPopupFour);
		listCrossButtonPopups.add(listPopupFive);
		listCrossButtonPopups.add(listPopupSix);
		listCrossButtonPopups.add(listPopupSeven);		
		
	}

	public void frasesHeaderList () {
		
		listFrasesHeaders.add("Wie heisst du?");
		listFrasesHeaders.add("Bist du verheiratet?");
		listFrasesHeaders.add("Wie heisst das auf Deutsch?");
		listFrasesHeaders.add("Wie alt ist deine Mutter?");
		listFrasesHeaders.add("Woher kommen Sie?");
		listFrasesHeaders.add("Hat Peter einen Hund?");
		listFrasesHeaders.add("Wer ist das?");
		listFrasesHeaders.add("Ist das deine Katze?");
		listFrasesHeaders.add("Wie geht es Ihnen?"); 
		listFrasesHeaders.add("Guten Morgen, Manuel"); //10
		
		listFrasesHeaders.add("Arbeiten Sie gerne?");
		listFrasesHeaders.add("Wie heisst eine Pizza mit Tomaten?");
		listFrasesHeaders.add("Tim möchte etwas essen.");
		listFrasesHeaders.add("Gehen wir ins Kino?");
		listFrasesHeaders.add("Mama kommt morgen in München an.");
		listFrasesHeaders.add("Was hat deine Mutter gestern gemacht");
		listFrasesHeaders.add("Wir waren im Januar in Berlin.");
		listFrasesHeaders.add("Wie lange warst du in Deutschland?");
		listFrasesHeaders.add("Hast du schon Shakespeare gelesen?");
		listFrasesHeaders.add("Ich mache morgen eine Party.");		
		
		listFrasesHeaders.add("Was ist dein Lieblingsessen?");
		listFrasesHeaders.add("Möchstest du einen Kaffee?");	
		listFrasesHeaders.add("Ich kann morgen nicht kommen.");
		listFrasesHeaders.add("Ihr seid letztes Jahr nach Paris geflogen.");	
		listFrasesHeaders.add("Fahren Sie auch gerne Fahrrad?");

		
	}
	
	public void frasesRadioList() {
		
		List<String> listHeisstDu = Arrays.asList("Ich heisse Peter, und du?", "Das ist Emma.", "Ich Tarzan, du Jane.", "Ja, ja .......", "My name is Smith.");
		List<String> listVerheiratet = Arrays.asList("Ja, ich bin verheiratet.", "Ja, du bist verheiratet.", "Ich komme aus Köln.", "Er spricht Englisch." , "Das ist richtig.");
		List<String> listAufDeutsch = Arrays.asList("Geld, das ist Geld.", "Ich bin Tarzan.", "Das ist keine Uhr.", "Das ist Peter." , "Nummer vier.");
		List<String> listDeineMutter = Arrays.asList("Sie ist vierundvierzig.", "Sie ist vier.", "Sie hat sechzig jahre alt.", "Meine Oma ist achtundsechszig." , "Er ist neunzig Jahre alt.");
		List<String> listWoher = Arrays.asList("Ich bin aus Argentinien.", "Wohnt er in Bremen.", "Sie kommmt aus Recife.", "Ach, ja .." , "Er wohnt in Bonn");
		List<String> listHund = Arrays.asList("Ja, er heisst Paul.", "Nein, er hat einen Hund.", "Ja, es ist schön.", "Nein, es ist eine Katze." , "Nein, er hat nicht einen Hund.");
		List<String> listWer = Arrays.asList("Das ist Tarzan.", "Er ist Adam.", "Sie heisst Jane.", "Nein, er heisst Freund." , "Das ist ein Auto.");
		List<String> listKatze = Arrays.asList("Nein, ich habe keine Katze.", "Nein, ich habe nicht eine Katze.", "Ja, ich habe keine Katze.", "Nein, ich habe nicht Katze." , "Ja, es heisst Perla.");
		List<String> listWieGeht = Arrays.asList("Mir geht es gut.", "Ich bin gut.", "Ich habe gut.", "Ihnen geht es gut." , "Ihnen ist gut."); //10
		List<String> listGutenMorgen = Arrays.asList("Danke, Emma, wie geht's?", "Tach", "Guten Abend.", "Ich heisse Peter, und du?", "Ich bin hier.");
		
		List<String> listArbeitenGerne = Arrays.asList("Ja, ich liebe meine Arbeit.", "Nein, ich liebe meine Arbeit.", "Das ist schön.", "Das ist mein Boss.", "Ja, ich arbeite nicht gerne.");
		List<String> listTomatenPizza = Arrays.asList("Tomatenpizza", "Pizzatomate", "TomatenPizza", "Tomaten Pizza", "Pizza Tomate");
		List<String> listTimEssen = Arrays.asList("Okay, hier ist ein Apfel.", "Okay, hier ist ein Tee.", "Kommen Sie mit ins Kino?", "Tim kannt eine Banane essen.", "Das ist ein Brot.");
		List<String> listInsKino = Arrays.asList("Ja, gerne, wann gehen wir?", "Ja, ich mag ins Kino gehen.", "Ich gehe ins Kino.", "Wir arbeiten morgen nicht.", "Nein, Kino ist interessant.");
		List<String> listMutterMunchen = Arrays.asList("Okay, kannst du sie abholen?", "Okay, kannst du sie abholst?", "Okay, ich abhole sie.", "Nein, sie ist meine Mutter.", "Wir sind in München.");
		List<String> listMutterGemacht = Arrays.asList("Sie hat gestern gearbeitet.", "Das ist schön.", "Ja, sie hat gemacht.", "Sie hat gearbeitet gemacht.", "Sie hat gemacht gearbeitet.");
		List<String> listJanBerlin = Arrays.asList("Was habt ihr gemacht?", "Wir haben nach Rom gefahren.", "Woher kommen Sie?", "Mein Bruder hat keine Zeit.", "Deutschland ist eine schöne Stadt.");
		List<String> listLangeDland = Arrays.asList("Ich war drei Wochen dort.", "Ich war vier Tage Zeit.", "Gestern lange Zeit.", "Wir haben eine Woche gewesen.", "Wir haben drei Monate sein.");
		List<String> listShaespeare = Arrays.asList("Ja, Shakespeare ist toll.", "Er kommt am Montag.", "Nein, ich mag Shakespeare.", "Shakespeare ist schon lange tot.", "Shakespeare wohnt jetzt in Rom.");
		List<String> listMorgenParty = Arrays.asList("Super, ich komme gerne.", "Super, ich komme nach Party.", "Wann war die Party?", "Hat deine Vater Zeit.", "Kannst du mich holen ab?");
		
		List<String> listLiebingsEssen = Arrays.asList("Mein Lieblingsessen ist Käsebrot.", "Mein Lieblingsessen ist von Köln.", "Mein Lieblingsessen ist Bier.", "Peter und Maria essen immer Kuchen.", "Alle kommen aus Paris.");
		List<String> listMoKaffee = Arrays.asList("Nein, ich mag keinen Kaffee.", "Ja, Kaffee hat viel Zucker.", "Kaffee kommt aus Brasilien.", "Das ist kein Kaffee nicht.", "Das ist nicht ein Kaffee.");
		List<String> listMorgenKommen = Arrays.asList("Kein Problem.", "Kein Problem nicht.", "Schön, ich habe Zeit.", "Wann kommst sie.", "Am Montag hat Maria einen cancel.");
		List<String> listParisGeflogen = Arrays.asList("Ja, dieses Jahr fahren wir wieder nach Paris.", "Ja, sie waren auch in Rom gefahren.", "Die Erde ist rund.", "Am Montag kommt meine Mutter.", "Jean kommt aus Paris.");
		List<String> listFahrrad = Arrays.asList("Ich habe leider kein Fahrrad.", "Nein, ich fahre gerne Fahrrad.", "Ja, ich reise gerne mit dem Auto.", "Mein Fahrrad ist rot und blau.", "Nein, ich habe nicht ein Fahrrad.");
		
		
		listFrasesOption.add(listHeisstDu);
		listFrasesOption.add(listVerheiratet);
		listFrasesOption.add(listAufDeutsch);
		listFrasesOption.add(listDeineMutter);
		listFrasesOption.add(listWoher);
		listFrasesOption.add(listHund);
		listFrasesOption.add(listWer);
		listFrasesOption.add(listKatze);
		listFrasesOption.add(listWieGeht); 
		listFrasesOption.add(listGutenMorgen);//10
		
		listFrasesOption.add(listArbeitenGerne);
		listFrasesOption.add(listTomatenPizza);
		listFrasesOption.add(listTimEssen);
		listFrasesOption.add(listInsKino);
		listFrasesOption.add(listMutterMunchen);
		listFrasesOption.add(listMutterGemacht);
		listFrasesOption.add(listJanBerlin);
		listFrasesOption.add(listLangeDland);
		listFrasesOption.add(listShaespeare);
		listFrasesOption.add(listMorgenParty); // 10
		
		listFrasesOption.add(listLiebingsEssen);
		listFrasesOption.add(listMoKaffee);
		listFrasesOption.add(listMorgenKommen);
		listFrasesOption.add(listParisGeflogen);
		listFrasesOption.add(listFahrrad);
	}
	
	public void frasesExpanationList() {
		
		listFrasesExplanation.add("'du' in der Frage ist 'ich' in der Antwort; nach dem Subjekt kommt ein Verb");
		listFrasesExplanation.add("'bist du .. ' in der Frage ist 'ich bin .. ' in der Antwort");
		listFrasesExplanation.add("Die Antwort muss mit 'das ist ...' sein, keine Person und positiv");
		listFrasesExplanation.add("Die Antwort muss mit 'sie ...' sein; nur die Antwort macht Sinn;");
		listFrasesExplanation.add("'Sie' in der Frage ist 'ich' in der Antwort");
		listFrasesExplanation.add("Das Pronomen für Hund ist 'er'; die Antwort muss positiv sein, oder mit 'keinen'");
		listFrasesExplanation.add("Die Antwort muss mit 'das ist ...' sein, und eine Person");
		listFrasesExplanation.add("Die Antwort muss mit 'ja, eine ...' oder 'nein, keine ... ' sein");
		listFrasesExplanation.add("'Ihnen geht es' in der Frage ist 'mir geht es' in der Antwort");
		listFrasesExplanation.add("Nur die Antwort macht Sinn");
		
		listFrasesExplanation.add("Die Antwort muss mit 'ja' oder 'nein' sein; 'du' in der Frage ist 'ich'in der Antwort");
		listFrasesExplanation.add("'Tomatenpizza' ist die korrekte Form für das kombinierte Substantiv");
		listFrasesExplanation.add("'Tee' ist kein Essen; 'er kann' ist falsch konjugiert, der Rest macht keinen Sinn");
		listFrasesExplanation.add("Die Antwort muss mit 'ja' oder 'nein' sein, 'mag gehen' = 'gehe gerne', der Rest macht keinen Sinn");
		listFrasesExplanation.add("'Abholen' ist ein trennbares Verb; 'können' ist mit Infinitiv am Ende");
		listFrasesExplanation.add("'Machen' ist hier der Joker für das Verb");
		listFrasesExplanation.add("'Fahren' ist dem Hilfsverb 'sein'; Deutschland ist ein Land, keine Stadt");
		listFrasesExplanation.add("'sind gewesen' funktioniert; 'haben' mit Infinitiv funktioniert nicht");
		listFrasesExplanation.add("Die anderen Antworten beantworten nicht die Frage oder machen keinen Sinn");
		listFrasesExplanation.add("'dein Vater' ist falsch dekliniert; '... zur Party' funktioniert, 'die Party ist ...'");
		
		listFrasesExplanation.add("'aus Köln'; 'Bier' ist kein Essen; 'dein' in der Frage ist 'mein' in der Antwort");
		listFrasesExplanation.add("'kein ... nicht' funktioniert nicht ('kein' oder 'nicht'), 'nicht ein' = 'kein'");
		listFrasesExplanation.add("'kein ... nicht' funktioniert nicht ('kein' oder 'nicht'); 'cancel' ist kein Substantiv; die anderen Antworten beantworten nicht die Frage");
		listFrasesExplanation.add("die anderen Antworten beantworten nicht die Frage");
		listFrasesExplanation.add("die Antwort muss 'ja, ein ...' oder 'nein, ... kein' sein, 'nicht ein' = 'kein'");
		
		
	}
	
	public void frasesControlsStrings() {
		
		listFrasescontrolStrings.add("Ich heisse Peter, und du?");
		listFrasescontrolStrings.add("Ja, ich bin verheiratet.");
		listFrasescontrolStrings.add("Geld, das ist Geld.");
		listFrasescontrolStrings.add("Sie ist vierundvierzig.");
		listFrasescontrolStrings.add("Ich bin aus Argentinien.");
		listFrasescontrolStrings.add("Ja, er heisst Paul.");
		listFrasescontrolStrings.add("Das ist Tarzan.");
		listFrasescontrolStrings.add("Nein, ich habe keine Katze.");
		listFrasescontrolStrings.add("Mir geht es gut."); 
		listFrasescontrolStrings.add("Danke, Emma, wie geht's?"); //10
		
		listFrasescontrolStrings.add("Ja, ich liebe meine Arbeit.");
		listFrasescontrolStrings.add("Tomatenpizza");
		listFrasescontrolStrings.add("Okay, hier ist ein Apfel.");
		listFrasescontrolStrings.add("Ja, gerne, wann gehen wir?");
		listFrasescontrolStrings.add("Okay, kannst du sie abholen?");
		listFrasescontrolStrings.add("Sie hat gestern gearbeitet.");
		listFrasescontrolStrings.add("Was habt ihr gemacht?");
		listFrasescontrolStrings.add("Ich war drei Wochen dort.");
		listFrasescontrolStrings.add("Ja, Shakespeare ist toll.");
		listFrasescontrolStrings.add("Super, ich komme gerne.");
		
		listFrasescontrolStrings.add("Mein Lieblingsessen ist Käsebrot.");
		listFrasescontrolStrings.add("Nein, ich mag keinen Kaffee.");
		listFrasescontrolStrings.add("Kein Problem.");
		listFrasescontrolStrings.add("Ja, dieses Jahr fahren wir wieder nach Paris.");
		listFrasescontrolStrings.add("Ich habe leider kein Fahrrad.");
		
	}
	
	public void textQuestionsReadingTexts() {
		
		listReadingTexts.add("Mein Name ist Anna. Ich komme aus Österreich und lebe seit drei Jahren in Deutschland. "
				+ "Ich bin 15 Jahre alt und habe zwei Geschwister: Meine Schwester heißt Klara und ist 13 Jahre alt, "
				+ "mein Bruder Michael ist 18 Jahre alt. Wir wohnen mit unseren Eltern in einem Haus in der Nähe von "
				+ "München. Meine Mutter ist Köchin, mein Vater arbeitet in einer Bank.\n" + 
				"\n" + 
				"Ich lese gerne und mag Tiere: Wir haben einen Hund, zwei Katzen und im Garten einen Teich mit "
				+ "Goldfischen, aber keine Kaninchen. Ich gehe auch gerne in die Schule, mein Lieblingsfach ist Mathematik. "
				+ "Physik und Chemie mag ich nicht so gerne.\n" + 
				"\n" + 
				"Nach der Schule gehe ich oft mit meinen Freundinnen im Park spazieren, manchmal essen wir ein Eis. "
				+ "Am Samstag gehen wir oft ins Kino. Am Sonntag schlafe ich lange, dann koche ich mit meiner "
				+ "Mutter das Mittagessen. Nach dem Essen gehen wir mit dem Hund am See spazieren. "
				+ "Sonntag ist mein Lieblingstag!");
		
		listReadingTexts.add("Juliana kommt aus Paris. Das ist die Hauptstadt von Frankreich. In diesem Sommer macht sie einen "
				+ "Sprachkurs in Freiburg. Das ist eine Universitätsstadt im Süden von Deutschland.\n\n" + 
				"Es gefällt ihr hier sehr gut. Morgens um neun beginnt der Unterricht, um halb drei ist er zu Ende. In ihrer "
				+ "Klasse sind außer Juliana noch 14 weitere Schüler, acht Mädchen und sechs Jungen. Sie kommen alle aus "
				+ "Frankreich, aber nicht aus Paris.\n\n" + 
				"Julianas beste Freundin  Marie macht auch gerade einen Sprachkurs, aber in Hamburg, das liegt ganz im Norden "
				+ "von Deutschland. Sie sind schon zwei Wochen hier.\n\n" + 
				"Wenn die beiden ihre Schule beendet haben, wollen sie in Deutschland studieren. Juliana will Tierärztin werden, "
				+ "ihre beste Freundin auch. Aber Maries Eltern sind beide Zahnärzte, deshalb wird Marie wahrscheinlich auch "
				+ "Zahnärztin werden.\n\n" + 
				"Juliana und Marie verbringen insgesamt sechs Wochen in Deutschland. Sie gehen oft ins Kino"
				+ " und fahren Fahrrad. Nach dem Sprachkurs machen sie eine Prüfung und bekommen die Note.");
	}
	
	public void textQuestions() {
		
		listReadingQuestions.add("Wie alt ist Annas Schwester?");
		listReadingQuestions.add("Wo arbeitet Annas Vater?");
		listReadingQuestions.add("Was ist Annas Lieblingsfach in der Schule?");
		listReadingQuestions.add("Was macht Anna nach der Schule?");
		listReadingQuestions.add("Wo geht die Familie am Sonntag mit dem Hund spazieren?");
		listReadingQuestions.add("Was ist Anas Mutter von Beruf?");
		listReadingQuestions.add("Welches Fach mag Anna nicht?");
		listReadingQuestions.add("Wo wohnt Anna?");
		listReadingQuestions.add("Wann geht Anna ins Kino?"); //10
		listReadingQuestions.add("Welche Tiere hat Anna");
		
		listReadingQuestions.add("Woher kommt Juliana?");
		listReadingQuestions.add("Wie viele Schüler sind in Julianas Sprachkurs?");
		listReadingQuestions.add("In welcher Stadt macht Marie einen Sprachkurs?");
		listReadingQuestions.add("Warum macht Marie einen Sprachkurs?");
		listReadingQuestions.add("Was möchte Juliana später werden?");
		listReadingQuestions.add("Wie lange dauert der Sprachkurs?");
		listReadingQuestions.add("Was machen sie nach dem Sprachkurs?");
		listReadingQuestions.add("Was macht Marie gerade?");
		listReadingQuestions.add("Seit wann sind Juliana und Marie in Deutschland?");
		listReadingQuestions.add("Wie lange bleiben sie in Deutschland?");		
	}
	
	public void textQuestionsButtons() {
		
		List<String> listWieAlt = Arrays.asList("dreizehn\nJahre", "fünfzehn\nJahre", "zwölf\nJahre", "vierzehn\nJahre");
		List<String> listWoArbeitet = Arrays.asList("in einer\nBank", "in der\nSchule", "im\nKino", "in\nÖsterreich");
		List<String> listLiegFach = Arrays.asList("Mathematik", "Chemie", "Physik", "Kochen");
		List<String> listNachSchu = Arrays.asList("Sie geht\nspazieren.", "Sie schläft\nlange.", "Sie geht\nins Kino.", "Sie kocht\ndas Mittagessen.");
		List<String> listHundSpa = Arrays.asList("am\nSee", "im\nPark", "im\nGarten", "im\nHaus");
		List<String> listBerMu = Arrays.asList("Köchin", "Ärztin", "Bäckerin", "Hausfrau");
		List<String> listFachNi = Arrays.asList("Chemie", "Mathematik", "Tiere", "Kaninchen");
		List<String> listWoWohnt = Arrays.asList("in einem\nHaus", "in\nMünchen", "bei der\nOma", "in\nÖsterreich");
		List<String> listWannKino = Arrays.asList("am\nSamstag", "am\nSonntag", "mit den\nFreundinnen", "nach dem\nEssen");
		List<String> listTiere = Arrays.asList("einen\nHund", "eine\nKatze", "einen\nGoldfisch", "ein\nKaninchen");
		
		List<String> listWoherJu = Arrays.asList("aus\nParis", "aus\nDeutschland", "aus\nHamburg", "aus\nFreiburg");
		List<String> listWieViele = Arrays.asList("15", "14", "13", "8");
		List<String> listWoMarie = Arrays.asList("Hamburg", "Bremen", "Freiburg", "Paris");
		List<String> listWarumKurs = Arrays.asList("Sie möchte in Deutschland studieren", "Sie mag die deutsche Sprache", "Ihre Eltern leben in Deutschland", "Sie hat einen deutschen Freund");
		List<String> listJuWerden = Arrays.asList("Tierärztin", "Zahnärztin", "Anwältin", "Lehrerin");
		List<String> listWieLange= Arrays.asList("5,5\nStunden", "4,5\nStunden", "5\nStunden", "eine\nWoche");
		List<String> listWasNach = Arrays.asList("eine\nPrüfung", "sie gehen ins Kino", "eine\nFahrradtour", "sie fliegen nach hause");
		List<String> listMarieGerade = Arrays.asList("einen\nSprachkurs", "in\nHamburrg", "Zahnärztin", "Tierärztin");
		List<String> listSeitWann = Arrays.asList("zwei\nWochen", "sechs\nWochen", "einem\nMonat", "fünfzehn\nTagen");
		List<String> listWieLanDland = Arrays.asList("eineinhalb\nMonate", "einen\nMonat", "vier\nWochen", "dreissig\nTage");		
		
		listReadingButtons.add(listWieAlt);
		listReadingButtons.add(listWoArbeitet);
		listReadingButtons.add(listLiegFach);
		listReadingButtons.add(listNachSchu);
		listReadingButtons.add(listHundSpa);
		listReadingButtons.add(listBerMu);
		listReadingButtons.add(listFachNi);
		listReadingButtons.add(listWoWohnt);
		listReadingButtons.add(listWannKino);
		listReadingButtons.add(listTiere);
		
		listReadingButtons.add(listWoherJu);
		listReadingButtons.add(listWieViele);
		listReadingButtons.add(listWoMarie);
		listReadingButtons.add(listWarumKurs);
		listReadingButtons.add(listJuWerden);
		listReadingButtons.add(listWieLange);
		listReadingButtons.add(listWasNach);
		listReadingButtons.add(listMarieGerade);
		listReadingButtons.add(listSeitWann);
		listReadingButtons.add(listWieLanDland);
	}
	
	public void textQuestionControls () {
		
		listReadingTextControls.add("dreizehn\nJahre");
		listReadingTextControls.add("in einer\nBank");
		listReadingTextControls.add("Mathematik");
		listReadingTextControls.add("Sie geht\nspazieren.");
		listReadingTextControls.add("am\nSee");
		listReadingTextControls.add("Köchin");
		listReadingTextControls.add("Chemie");
		listReadingTextControls.add("in einem\nHaus");
		listReadingTextControls.add("am\nSamstag");
		listReadingTextControls.add("einen\nHund");
		
		listReadingTextControls.add("aus\nParis");
		listReadingTextControls.add("15");
		listReadingTextControls.add("Hamburg");
		listReadingTextControls.add("Sie möchte in Deutschland studieren");
		listReadingTextControls.add("Tierärztin");
		listReadingTextControls.add("5,5\nStunden");
		listReadingTextControls.add("eine\nPrüfung");
		listReadingTextControls.add("einen\nSprachkurs");
		listReadingTextControls.add("zwei\nWochen");
		listReadingTextControls.add("eineinhalb\nMonate");
	}
	
	public void textQuestionsExpanationList() {
		
		listReadingExplanation.add("... Meine Schwester heißt Klara und ist 13 Jahre alt ... ");
		listReadingExplanation.add("... mein Vater arbeitet in einer Bank ... ");
		listReadingExplanation.add("... mein Lieblingsfach ist Mathematik ...");
		listReadingExplanation.add("... Nach der Schule gehe ich oft mit meinen Freundinnen im Park spazieren ...");
		listReadingExplanation.add("... dem Essen gehen wir mit dem Hund am See spazieren ... ");
		listReadingExplanation.add("... Meine Mutter ist Köchin ...");
		listReadingExplanation.add("... Chemie mag ich nicht so gerne ...");
		listReadingExplanation.add("... Wir wohnen mit unseren Eltern in einem Haus...");
		listReadingExplanation.add("... Am Samstag gehen wir oft ins Kino ...");
		listReadingExplanation.add("... Wir haben einen Hund...");
		
		listReadingExplanation.add("Juliana kommt aus Paris ...");
		listReadingExplanation.add("... außer Juliana noch 14 weitere Schüler (14 + Juliana = 15 ...");
		listReadingExplanation.add("... Marie macht auch gerade einen Sprachkurs, aber in Hamburg ...");
		listReadingExplanation.add("...Wenn die beiden ihre Schule beendet haben, wollen sie in Deutschland studieren ...");
		listReadingExplanation.add("... Juliana will Tierärztin werden ...");
		listReadingExplanation.add("... um neun beginnt der Unterricht, um halb drei ist er zu Ende (halb drei = 14h30) ...");
		listReadingExplanation.add("... Nach dem Sprachkurs machen sie eine Prüfung ...");
		listReadingExplanation.add("... Marie macht auch gerade einen Sprachkurs ...");
		listReadingExplanation.add("... Sie sind schon zwei Wochen hier (in Deutschland) ...");
		listReadingExplanation.add("... Juliana und Marie verbringen insgesamt sechs Wochen in Deutschland ...");
		
	}

	private void explanationList () {
		
		String stringHeisstDu = "Os verbos precisam ser flexionados para as respectivas pessoas dos verbos 'heissen' e 'sein'";
		String stringFrau = "'Herr' é usado para pessoas do sexo masculino, com sobrenome, apenas, ou com nome completo";
		String stringWieGehtDir = "A construção é usada com o dativo de 'du', o qual é 'dir'";
		String stringAlter = "A grafia correta é 'siebzehn'";
		String stringWieGehtIhnen = "No tratamento formal, a construção é usada com o dativo de 'Sie', o qual é 'Ihnen', com letra maiúscula";
		String stringUhrHalb = "No alemão, a lógica é que falta meia hora para completar 9h00";
		String stringTasche = "'Tasche' é um substantivo de gênero feminino. O artigo indefinido é 'eine', o pronome feminino é 'sie'";
		String stringDoch = "Para contradizer a negativa 'nicht', usa-se 'doch', na resposta";
		String stringArbeiten = "Nega-se um verbo com 'nicht', e um substantivo, com 'kein'";
		String stringKann = "O verbo 'können' conjuga-se 'kann', na terceira pessoa do singular. A construção pede o infinitivo no final da frase";
		String stringInput01 = "O verbo irregular 'lesen' é conjugado com mudança de vogal na segunda e terceira pessoas do singular";
		String stringInput02 = "O verbo irregular 'sprechen' é conjugado com mudança de vogal na segunda e terceira pessoas do singular. Na pergunta, o verbo fica antes do sujeito.";
		String stringInput03 = "'fernsehen' é um verbo do tipo separável";
		String stringInput04 = "Para verbos (gostar de fazer algo), usa-se advérbio 'gerne', nos demais casos, 'mögen'";
		String stringInput05 = "Substantivos são negativados com 'kein'. O substantivo masculino recebe um -en, quando é objeto da oração e no singular";
		String stringInput06 = "O verbo 'kommen' pede, quando usado com lugares, a preposição 'aus'. O verbo 'wohnen' pede a preposição 'in'";
		String stringInput07 = "O pronomes 'ich' e 'du' viram 'mich' e 'dich', quando são usados como objetos (acusativo) da frase";
		String stringInput08 = "A construção pede, já tendo um verbo auxiliar conjugado, um infinitivo no final da frase";
		String stringInput09 = "'Fahren' é um verbo de movimentação, que pede 'sein' como verbo auxiliar. O 'Perfekt' é construído com o verbo auxiliar e o particípio, que fica no final da oração";
		String stringInput10 = "O artigo possessivo 'sein' (seu/ dele) é objeto masculino, que pede -en como terminação. O pronome 'er' é 'ihn'  no acusativo";
		String stringIm = "Mês requer 'im' como preposição";
		String stringAnkommen = "'Ankommen' é um verbo de movimentação, que pede 'sein' como verbo auxiliar. O 'Perfekt' é construído com o verbo auxiliar e o particípio, que fica no final da oração. Para os verbos separáveis, a 'trennbare Vorsilbe' fica antes do particípio";
		String stringAm = "Dia requer 'am' como preposição";
		String stringBus = "O verbo irregular 'nehmen' é conjugado com mudança de vogal na segunda e terceira pessoas do singular. 'Bus' é um substantivo do gênero masculino, cuja declinação no acusativo é 'den'";
		String stringGerne = "Para verbos (gostar de fazer algo), usa-se o advérbio 'gerne', nos demais casos, 'mögen'";
		
		listExplanation.add(stringHeisstDu);
		listExplanation.add(stringFrau);
		listExplanation.add(stringWieGehtDir);
		listExplanation.add(stringAlter);
		listExplanation.add(stringWieGehtIhnen);
		listExplanation.add(stringUhrHalb);
		listExplanation.add(stringTasche);
		listExplanation.add(stringDoch);
		listExplanation.add(stringArbeiten);
		listExplanation.add(stringKann);
		listExplanation.add(stringInput01);
		listExplanation.add(stringInput02);
		listExplanation.add(stringInput03);
		listExplanation.add(stringInput04);
		listExplanation.add(stringInput05);
		listExplanation.add(stringInput06);
		listExplanation.add(stringInput07);
		listExplanation.add(stringInput08);
		listExplanation.add(stringInput09);
		listExplanation.add(stringInput10);
		listExplanation.add(stringIm);
		listExplanation.add(stringAnkommen);
		listExplanation.add(stringAm);
		listExplanation.add(stringBus);
		listExplanation.add(stringGerne);
		
	}
	
	public void labelLists() {
		
		String stringHeisstDu = "Ich ______ Emma.\nWer  ______  du.";
		String stringFrau = "Hallo, guten Tag.\nIch bin Herr _______.";
		String stringWieGehtDir = "Wie geht es _______, Otto?\nMir geht es gut.";
		String stringAlter = "Mein Sohn Peter\nist _______ Jahre alt.";
		String stringWieGehtIhnen = "Guten Tag. Wie geht\nes _______ Herr _______.";
		String stringUhrHalb = "Es ist 8:30 Uhr.\nWir arbeiten schon.";
		String stringTasche = "Das ist _______ Tasche\n und ______ ist blau.";
		String stringDoch = "Ist Marion nicht verheiratet?\n______, sie ist verheiratet.";
		String stringArbeiten = "Carlos arbeitet ______ \nund hat ______ Auto.";
		String stringKann = "Peter _______ wirklich\nsehr gut _______.";
		String stringInput01 = "Ich ______ eine Zeitung.\nDu  ______  ein Buch.";
		String stringInput02 = "Meine Oma spricht Spanisch.\nWelche Sprachen ______ ______?";
		String stringInput03 = "Was machst du morgen?\nIch __________.";
		String stringInput04 = "Du magst deine Arbeit.\nDu _______ ________";
		String stringInput05 = "Ich habe schon eine Katze.\nIch brauche  ______  Hund.";
		String stringInput06 = "Ana und Udo kommen ______ Köln.\nSie wohnen _______ Bonn.";
		String stringInput07 = "Ich liebe ______ .\nUnd du liebst ______ .";
		String stringInput08 = "Mein Vater möchte im Mai nach Hawai ________.";
		String stringInput09 = "Maria _____ gestern mit\ndem schönen neuen Auto _______ .";
		String stringInput10 = "Pedro liebt _____ Hund\nund sein Hund liebt _______ .";
		String stringIm = "Du und ich haben _____ Januar\nGeburtstag.";
		String stringAnkommen = "Wir _____ schon in\nMailand ________ .";
		String stringAm = "Wir haben _____ 23. März Geburtstag.";
		String stringBus = "Ich nehme ein Taxi.\n_______ du ______ Bus?.";
		String stringGerne = "Die Kinder _______ Kuchen\nund trinken ______ Milch.";
		
		listLabel.add(stringHeisstDu);
		listLabel.add(stringFrau);
		listLabel.add(stringWieGehtDir);
		listLabel.add(stringAlter);
		listLabel.add(stringWieGehtIhnen);
		listLabel.add(stringUhrHalb);
		listLabel.add(stringTasche);
		listLabel.add(stringDoch);
		listLabel.add(stringArbeiten);
		listLabel.add(stringKann);
		listLabel.add(stringInput01);
		listLabel.add(stringInput02);
		listLabel.add(stringInput03);
		listLabel.add(stringInput04);
		listLabel.add(stringInput05);
		listLabel.add(stringInput06);
		listLabel.add(stringInput07);
		listLabel.add(stringInput08);
		listLabel.add(stringInput09);
		listLabel.add(stringInput10);
		listLabel.add(stringIm);
		listLabel.add(stringAnkommen);
		listLabel.add(stringAm);
		listLabel.add(stringBus);
		listLabel.add(stringGerne);
		
	}
	
	public void listForLabel () {
		
		List<String> listHeisstDu = Arrays.asList("heisse -\nbist", "heisse -\nheisst", "sein -\nsein", "heissen -\nsein");
		List<String> listFrau = Arrays.asList("Maier", "Peter", "Petra Maier", "Maier Peter");
		List<String> listGehDir = Arrays.asList("dir", "du", "dich", "dein");
		List<String> listAlter = Arrays.asList("siebzehn", "siebenzehn", "siebenzig", "siehben");
		List<String> listGehtIhnen = Arrays.asList("Ihnen -\nKühne", "Sie -\nKühne", "Ihnen -\nPeter", "ihnen -\nKühne");
		List<String> listHalb = Arrays.asList("halb\nneun", "halb\nacht", "halb\nneunzehn", "acht\ndreissig");
		List<String> listTasche = Arrays.asList("eine - sie", "Ein - er", "eine - er", "ein - sie");
		List<String> listDoch = Arrays.asList("Doch", "Ja", "Nicht", "Nein");
		List<String> listArbeiten = Arrays.asList("nicht -\nkein", "nicht -\nnicht", "nicht -\nkeinen", "kein -\nnicht");
		List<String> listKann = Arrays.asList("kann -\nsingen", "kannt -\nsingen", "kannt -\nsingt", "kannt -\nsingen"); //10
		List<String> list01 = Arrays.asList("lese - lest", "lese - leset", "liese - liest", "lese - liest");
		List<String> list02 = Arrays.asList("sprechst - du", "du - sprechst", "sprichst - dich", "sprichst - du");
		List<String> list03 = Arrays.asList("fernsehen", "fernsehe", "fern sehe", "sehe fern");
		List<String> list04 = Arrays.asList("arbeitest -\ngerne", "gerne -\narbeitest", "magst -\narbeiten", "magst -\narbeitest");
		List<String> list05 = Arrays.asList("nicht ein", "nicht\neinen", "kein", "keinen");		
		List<String> list06 = Arrays.asList("aus - in", "von - aus", "in - aus", "für - von");		
		List<String> list07 = Arrays.asList("ich - du", "ich - mir", "dir - mir", "dich - mich");
		List<String> list08 = Arrays.asList("fliege", "fliegt", "geflogen", "fliegen");
		List<String> list09 = Arrays.asList("hat\nfahren", "ist\nfahren", "hat\ngefahren", "ist\ngefahren");
		List<String> list10 = Arrays.asList("sein - er", "sein - ihn", "seinen -\nihn", "dein - mein");
		List<String> listIm = Arrays.asList("im", "am", "in", "an");
		List<String> listAnkommen = Arrays.asList("sind -\nangekommen", "sind -\nankommen", "sind -\ngekommen an", "haben -\nangekommmen");
		List<String> listAm = Arrays.asList("am", "an", "im", "in");
		List<String> listBus = Arrays.asList("Nimmst -\nden", "Nimmst -\ndas", "Nehmst -\nden", "Nehmst -\ndas");
		List<String> listGerne = Arrays.asList("mögen -\ngerne", "mögen -\nmögen", "gernen -\ngerne", "gernen -\nmögen");
		
		listButtons.add(listHeisstDu);
		listButtons.add(listFrau);
		listButtons.add(listGehDir);
		listButtons.add(listAlter);
		listButtons.add(listGehtIhnen);
		listButtons.add(listHalb);
		listButtons.add(listTasche);
		listButtons.add(listDoch);
		listButtons.add(listArbeiten);
		listButtons.add(listKann);
		listButtons.add(list01);
		listButtons.add(list02);
		listButtons.add(list03);
		listButtons.add(list04);
		listButtons.add(list05);
		listButtons.add(list06);
		listButtons.add(list07);
		listButtons.add(list08);
		listButtons.add(list09);
		listButtons.add(list10);
		listButtons.add(listIm);
		listButtons.add(listAnkommen);
		listButtons.add(listAm);
		listButtons.add(listBus);
		listButtons.add(listGerne);
	}
	
	public void controlStrings () {
		
		String controlStringHeisstDu = "heisse -\nbist";
		String controlStringFrau = "Maier";
		String controlStringGehtDir = "dir";
		String controlStringAlter = "siebzehn";
		String controlStringGehtIhnen = "Ihnen -\nKühne";
		String controlStringHalb = "halb\nneun";
		String controlStringTasche = "eine - sie";
		String controlStringDoch = "Doch";
		String controlStringArbeiten = "nicht -\nkein";
		String controlStringKann = "kann -\nsingen";
		
		String controlString01 = "lese - liest";
		String controlString02 = "sprichst - du";
		String controlString03 = "sehe fern";
		String controlString04 = "arbeitest -\ngerne";
		String controlString05 = "keinen";
		String controlString06 = "aus - in";
		String controlString07 = "dich - mich";
		String controlString08 = "fliegen";
		String controlString09 = "ist\ngefahren";
		String controlString10 = "seinen -\nihn";
		
		String controlStringIm = "im";
		String controlStringAnkommen = "sind -\nangekommen";
		String controlStringAm = "am";
		String controlStringBus = "Nimmst -\nden";
		String controlStringGerne = "mögen -\ngerne";
		
		listcontrolStrings.add(controlStringHeisstDu);
		listcontrolStrings.add(controlStringFrau);
		listcontrolStrings.add(controlStringGehtDir);
		listcontrolStrings.add(controlStringAlter);
		listcontrolStrings.add(controlStringGehtIhnen);
		listcontrolStrings.add(controlStringHalb);
		listcontrolStrings.add(controlStringTasche);
		listcontrolStrings.add(controlStringDoch);
		listcontrolStrings.add(controlStringArbeiten);
		listcontrolStrings.add(controlStringKann);
		
		listcontrolStrings.add(controlString01);
		listcontrolStrings.add(controlString02); 
		listcontrolStrings.add(controlString03);
		listcontrolStrings.add(controlString04);
		listcontrolStrings.add(controlString05);
		listcontrolStrings.add(controlString06);
		listcontrolStrings.add(controlString07);
		listcontrolStrings.add(controlString08);
		listcontrolStrings.add(controlString09);
		listcontrolStrings.add(controlString10);
		
		listcontrolStrings.add(controlStringIm);
		listcontrolStrings.add(controlStringAnkommen);
		listcontrolStrings.add(controlStringAm);
		listcontrolStrings.add(controlStringBus);
		listcontrolStrings.add(controlStringGerne);
		
	}
	
	public void consolidatedLists () {
		
		//ClassCrossWords
		croswordCheck();
		crossWordsCrosses();
		crossWordExplain();
		crossWordLetters();
		crossWordPopUpString();
		
		// ClassWords
		explanationList();
		labelLists();
		listForLabel();
		controlStrings();
		
		// ClassFrases
		frasesControlsStrings();
		frasesExpanationList();
		frasesHeaderList();
		frasesRadioList();
		
		// ClassTextQuestions
		textQuestions();
		textQuestionControls();
		textQuestionsExpanationList();
		textQuestionsReadingTexts();
		textQuestionsButtons();
	}
	
}
