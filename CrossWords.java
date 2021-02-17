package com.torus.a1test.en;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.io.Preferences;
import com.codename1.media.Media;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

public class CrossWords {
	
	private ArrayList<Button> listDropButtons = new ArrayList<>();
	private ArrayList<Container> listContainers = new ArrayList<>();	
	private Form formCrossWords = new Form(BoxLayout.y()) ;
	private Button buttonOne = new Button();
	private Button buttonTwo = new Button();
	private Button buttonThree = new Button();
	private Button buttonFour = new Button();
	private Button buttonAgain;
	private Button buttonInfo; 
	private Button buttonNext; 
	private Button buttonOk;
	private Button buttonLetters; 
	private Button buttonDialogAgain;
	private Button buttonDialogHome;
	private FirstRunDialogs cfd = new FirstRunDialogs();
	private InputArrays inputArrays = new InputArrays(); 
	private Pojos pojos = new Pojos();
	private Container containerButtons = new Container(new GridLayout(2,6)) {
		@Override
	    public void drop(Component dragged, int x, int y) {
	        super.drop(dragged, x, y); 
	        setUIID("Container");
	    }
	};
	private Container containerBase = new Container(new GridLayout(8,6)) {
		@Override
	    public void drop(Component dragged, int x, int y) {
	        super.drop(dragged, x, y); 
	        setUIID("Container");
	    }
	};
	private Container containerDropTarget = new Container();
	private Container containerNext;
	private InputStream inputStreamInButton;
	private int intCorrects = Preferences.get("CwCorrectAnswers",0);
	private int intFalses = Preferences.get("CwFalseAnswers",0);
	private int intTotal = 25;
	private int intIndex = Preferences.get("CwIndex",0);
	private int intLabelCount;
	private int intIndexFinal = 6;
	private InputStream inputStream;
	private Label labelCount;
	private Media media;
	private Slider sliderCorrect;
	private Slider sliderFalse;

	public CrossWords(Form formBack, Pojos2 pojos2, Label label) throws IOException {
		// Back command
	    Command back = new Command("A") {
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	label.setText(pojos2.getintLabelCount() + "/ 95");
	        	formBack.showBack();
	        }
	    }; 
	    formCrossWords.setBackCommand(back);
	    
	    // call first run start
	    if (Preferences.get("CwFirstRunStart",0) == 0) {
			dialogFirstRunStart(pojos);
		}
	    
	    // Call input arrays 
	    inputArrays.consolidatedLists();
	    
	    // Add buttons to list 
	    buttonOne.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "", 4));
	    buttonTwo.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, "", 4));
	    buttonThree.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, "", 4));
	    buttonFour.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, "", 4));
	    
	    // Put everything together 
	    containerNext = ButtonContainer.containerMenu();
	    
	    formCrossWords.add(containerButtons);
		formCrossWords.add(containerBase);
		formCrossWords.add(containerNext);
		
		// Get components form Button Class
		buttonOk = ButtonContainer.buttonOk;
		buttonOkAction(buttonOk);
		buttonOkTwo(buttonOk);
		
		buttonAgain = ButtonContainer.getButtonAgain();
		buttonAgainAction(buttonAgain, pojos2, formBack, label);
		
		buttonInfo = ButtonContainer.getButtonInfromation();
		buttonInfoAction(buttonInfo, pojos);
		
		buttonNext = ButtonContainer.getButtonNext();
		buttonNextAction(buttonNext, pojos, pojos2, formBack, label);
		
		labelCount = ButtonContainer.getLabelCount();
		labelCount.setText(String.valueOf(Preferences.get("CwIndex",0) + 1) + "/ 7");
		
		sliderCorrect = ButtonContainer.getSliderCorrect();
		sliderCorrect.setEditable(false);
		sliderCorrect.setMinValue(0);
		sliderCorrect.setMaxValue(intTotal);
		sliderCorrect.setProgress(Preferences.get("CwCorrectAnswers",0));
		sliderCorrect.setText(String.valueOf(Preferences.get("CwCorrectAnswers",0)));
		sliderFalse = ButtonContainer.geSliderFalse();
		sliderFalse.setEditable(false);
		sliderFalse.setMinValue(0);
		sliderFalse.setMaxValue(intTotal);	
		sliderFalse.setProgress(Preferences.get("CwFalseAnswers",0));
		sliderFalse.setText(String.valueOf(Preferences.get("CwFalseAnswers",0)));
		
		// Acions button queries
		actionListenersButtonQueries();
		// Container for the crossword 
	    containerForCrossWord();
		 // Buttons for the letter
	    buttonsForLetters();
		
		styles();
		
	    formCrossWords.show();
	}
	
	public void actionListenersButtonQueries () {
		
		buttonOne.addActionListener(l -> {
			
			String stringButton = inputArrays.listCrossButtonPopups.get(intIndex).get(0);
			buttonDialog(buttonOne, stringButton);
		});
		
		buttonTwo.addActionListener(l -> {
			
			String stringButton = inputArrays.listCrossButtonPopups.get(intIndex).get(1);
			buttonDialog(buttonTwo, stringButton);
		});
		
		buttonThree.addActionListener(l -> {
			
			String stringButton = inputArrays.listCrossButtonPopups.get(intIndex).get(2);
			buttonDialog(buttonThree, stringButton);
		});
		
		buttonFour.addActionListener(l -> {
			
			String stringButton = inputArrays.listCrossButtonPopups.get(intIndex).get(3);
			buttonDialog(buttonThree, stringButton);
		});
	}
	
	public void actionListenersButtonLetters (Button button) {

		button.addActionListener(l -> {
			try {
				new Feedback().soundButton(media, inputStreamInButton);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		button.addDropListener(l -> {
			
			try {
				new Feedback().soundButton(media, inputStreamInButton);				
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		});		
		
		button.addDragOverListener( l -> {
			
			for (Container container : listContainers) {
				
				if (container.getComponentCount() == 1) {
					container.setDropTarget(false);
				} else if (container.getComponentCount() == 0) {
					container.setDropTarget(true);
				}
			}
			
		});
		
		button.addDragFinishedListener( l -> {			
			
			ArrayList<Integer> numberList = new ArrayList<Integer>();
			
			for (Container container : listContainers) {
				
				int intCount = container.getComponentCount();
				
				if (intCount == 1) {
					numberList.add(intCount);
				}
			}
			
			if (numberList.size() == listContainers.size()) {
				
				if (Preferences.getAndSet("CwFirstRunOk",0) == 0) {
					formCrossWords.scrollComponentToVisible(buttonNext);
					dialogFirstRunButtonOk();
				} else {
					buttonOk.setEnabled(true);
				}		    
			} else {
				buttonOk.setEnabled(false);
			}
			buttonOkAction(buttonOk);

		});
	
	}
	
	public void buttonAgainAction (Button button, Pojos2 pojos2, Form formBack, Label label) {
		
		button.addActionListener(l-> {
			try {
				new CrossWords(formBack, pojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	public void buttonNextAction (Button button , Pojos cPojos, Pojos2 pojos2, Form formBack, Label label) {
		
		button.addActionListener(l-> {
			
			try {
			
				int intIndexOld = Preferences.get("CwIndex",0);
				int intCorNew = cPojos.getintCorrects();
				int intFalseNew = cPojos.getintFalse();
				int intIndexNew = intIndexOld + 1;
				
				if (intIndexNew > intIndexFinal) {
					
					cPojos.setintCorrects(intCorNew);
					cPojos.setintFalse(intFalseNew);
					cPojos.setintTotal(intTotal);
					
					Feedback cf = new Feedback();					
					cf.dialogFinal(cPojos);	
					
					buttonDialogAgain = cf.getButtonDialogRepeat();
					buttonDialogHome = cf.getButtonDialogHome();
					
					dialogActions(formBack, pojos2, label);
					
				} else {
					
					intLabelCount = sliderCorrect.getProgress() + Preferences.get("CorrectAnswers",0) 
					+ Preferences.get("FrasesCorrectAnswers",0) + Preferences.get("QuestionsCorrectAnswers",0);
					pojos2.setintLabelCount(intLabelCount);
					
					Preferences.set("CwCorrectAnswers",intCorNew);
					Preferences.set("CwFalseAnswers",intFalseNew);
					Preferences.set("CwIndex",intIndexNew);		
				
					new CrossWords(formBack, pojos2, label);
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		});
		
	}
	
	public void dialogActions (Form formBack, Pojos2 pojos2, Label label) {
		
		buttonDialogAgain.addActionListener(l-> {
			
			try {
				Preferences.set("CwCorrectAnswers",0);
				Preferences.set("CwFalseAnswers",0);
				Preferences.set("CwIndex",0);	
				
				new CrossWords(formBack , pojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buttonDialogHome.addActionListener(l-> {
			
			try {
				Preferences.set("CwCorrectAnswers",0);
				Preferences.set("CwFalseAnswers",0);
				Preferences.set("CwIndex",0);	
				
				new StartScreen().startScreen(pojos, pojos2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public void buttonInfoAction (Button button , Pojos pojos) {
		
		button.addActionListener(l -> { 
			
			try {
				ArrayList <String> listDialog = inputArrays.listCrossExplanation;
				pojos.setDialogList(listDialog);
				pojos.setintTotal(intIndex);
				new Feedback().dialogExplainFrases(pojos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
	}
	
	public void buttonOkAction (Button buttonOk) {
		
		buttonOk.addActionListener(l -> {
			
			for (Button button : listDropButtons) {
				
				String stringButtonName = button.getText();
				String stringParent= button.getParent().getName();
				
				if (stringButtonName.equals(stringParent)) {
					button.setUIID("CrossWordsChoicesButtonRight");					
				} else if (!stringButtonName.equals(stringParent)) {
					button.setUIID("CrossWordsChoicesButtonWrong");
				}
			}
			
			for (Component component : containerButtons) {
				component.setUIID("CrossWordsChoicesButton");	
			}
			
		});

	}
	
	public void buttonOkTwo (Button button) {
		
		button.addActionListener(l -> {
			
			// disable button for accidenatl pressing 
			buttonOk.setEnabled(false);
			// pull first run dialog next
			if (Preferences.get("FirstRunNext",0) == 0) {
			
				try {		
					formCrossWords.scrollComponentToVisible(buttonAgain);
					dialogFirstNext();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} 
			
			// get child buttons from containers
			ArrayList<List<String>> listOfLists = new ArrayList<>();
			ArrayList<String> listGetChildren = new ArrayList<>();
			
			for (Container container : listContainers) {				
				ArrayList<String> stringsList = new ArrayList<>();
				String stringContainer = container.getChildrenAsList(true).toString();
				
				if (!stringContainer.contains("null")) {					
					StringTokenizer strToken = new StringTokenizer(stringContainer, ",");				
					while (strToken.hasMoreTokens()) {							
						stringsList.add(strToken.nextElement().toString());							
					}					
					listOfLists.add(stringsList);
				}
			}
			
			// Get button letter from the list of strings 
			for (List<?> list : listOfLists) {
				if (list.size() >= 2) {
					String stringControl = list.get(1).toString().trim();
					String stringCut = stringControl.substring(7);
					listGetChildren.add(stringCut);
				}
			}			
			// Get words from crosswords and compare 
			// Get string one 
			StringBuilder stringOne = new StringBuilder();
			String stringControlOne = inputArrays.listCrossControlStrings.get(intIndex).get(0);
			List <Integer> listIntsOne = inputArrays.listCrossControlIntegers.get(intIndex).get(0);
			for (int i : listIntsOne) {
				stringOne.append(listGetChildren.get(i));
				pojos.setStringOneCw(stringOne.toString().trim());
			}
			
			int intCorrectsOne = 0; 
			int intFalsesOne = 0; 
			String stringToCheckOne = pojos.getStringOneCw();
			if (stringToCheckOne.equals(stringControlOne)) {
				intCorrectsOne = 1;
			} else {
				intFalsesOne = 1;
			}
			// Get string two
			StringBuilder stringTwo = new StringBuilder();
			String stringControlTwo = inputArrays.listCrossControlStrings.get(intIndex).get(1);
			List <Integer> listIntsTwo = inputArrays.listCrossControlIntegers.get(intIndex).get(1);
			for (int i : listIntsTwo) {
				stringTwo.append(listGetChildren.get(i));
				pojos.setStringTwoCw(stringTwo.toString().trim());	
			} 
			
			int intCorrectsTwo = 0;
			int intFalsesTwo = 0;
			String stringToCheckTwo = pojos.getStringTwoCw();
			if (stringToCheckTwo.equals(stringControlTwo)) {
				intCorrectsTwo = 1;
			} else {
				intFalsesTwo = 1;
			}
			
			// Get string three
			
			int intCorrectsThree = 0;
			int intFalsesThree = 0;
			
			StringBuilder stringThree = new StringBuilder();
			String stringControlThree = inputArrays.listCrossControlStrings.get(intIndex).get(2);
			List <Integer> listIntsThree = inputArrays.listCrossControlIntegers.get(intIndex).get(2);
			for (int i : listIntsThree) {
				stringThree.append(listGetChildren.get(i));
				pojos.setStringThreeCw(stringThree.toString().trim());	
				
			} 
			String stringToCheckThree = pojos.getStringThreeCw();
			if (stringToCheckThree.equals(stringControlThree)) {
				intCorrectsThree = 1;
			} else {
				intFalsesThree = 1;
			}

			// Get string four
			
			int intCorrectsFour = 0;
			int intFalsesFour = 0;
			
			if (intIndex >= 3) {
				
				StringBuilder stringFour = new StringBuilder();
				String stringControlFour = inputArrays.listCrossControlStrings.get(intIndex).get(3);
				List <Integer> listIntsFour = inputArrays.listCrossControlIntegers.get(intIndex).get(3);
				for (int i : listIntsFour) {
					stringFour.append(listGetChildren.get(i));
					pojos.setStringFourCw(stringFour.toString().trim());						
				} 

				String stringToCheckFour = pojos.getStringFourCw();
				if (stringToCheckFour.equals(stringControlFour)) {
					intCorrectsFour = 1;
				} else {
					intFalsesFour = 1;
				}
			}
			
			// Set slider values
			sliderCorrect.setText(String.valueOf(intCorrects + intCorrectsOne + intCorrectsTwo + intCorrectsThree + intCorrectsFour));
			sliderCorrect.setProgress(intCorrects + intCorrectsOne + intCorrectsTwo + intCorrectsThree + intCorrectsFour);
			pojos.setintCorrects(intCorrects + intCorrectsOne + intCorrectsTwo + intCorrectsThree + intCorrectsFour);
			
			sliderFalse.setText(String.valueOf(intFalses + intFalsesOne + intFalsesTwo + intFalsesThree + intFalsesFour));
			sliderFalse.setProgress(intFalses + intFalsesOne + intFalsesTwo + intFalsesThree + intFalsesFour);	
			pojos.setintFalse(intFalses + intFalsesOne + intFalsesTwo + intFalsesThree + intFalsesFour);
			
			// set all drags to false until class is reloaded
			for (Button component : listDropButtons) {
				component.setDraggable(false);
			}
			// set feedback sound
			int intCorLevel = intCorrectsOne + intCorrectsTwo + intCorrectsThree + intCorrectsFour;
			int intCorLength = inputArrays.listCrossControlStrings.get(intIndex).size();

			if (intCorLevel == intCorLength) {
				try {
					new Feedback().soundCorrect(media, inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					new Feedback().soundInCorrect(media, inputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
		});
		
		
	}

	public void buttonsForLetters () {
		
		List<String> stringsLettersCw = inputArrays.listCrossLetters.get(intIndex);
	    Collections.shuffle(stringsLettersCw);
	    
	    for (String string : stringsLettersCw) {
		buttonLetters = new Button(string);
		buttonLetters.setDraggable(true);
		buttonLetters.setName(string);
		buttonLetters.setUIID("CrossWordsChoicesButton");
		listDropButtons.add(buttonLetters);
	    	containerButtons.add(buttonLetters);
	    	
	    	buttonLetters.addDragOverListener(l-> {		
	    		
				for (Component component : containerBase) {					
					if (component.getName().equals("")) {	
						component.setUIID("CrossWordsContainerLetterHolderNull");
						component.repaint();
					} else {	
						component.setUIID("CrossWordsContainerLetterHolder");
						component.repaint();
					}
					
					if (component.getName().equals("ButtonOne")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonTwo")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonThree")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonFour")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} 
	
				}
				
				containerButtons.setUIID("CrossWordButtonContainer");
			});
			
	    	buttonLetters.addDragFinishedListener(l-> {				
				for (Component component : containerBase) {		
					if (component.getName().equals("")) {						
						component.setUIID("CrossWordsContainerLetterHolderNull");
						component.repaint();
					} else {
						component.setUIID("CrossWordsContainerLetterHolder");
						component.repaint();
					}
					
					if (component.getName().equals("ButtonOne")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonTwo")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonThree")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} else if (component.getName().equals("ButtonFour")) {
		 				component.setUIID("CrossWordsContainerLetterHolderNull");
		 			} 
					
					containerButtons.setUIID("CrossWordButtonContainer");					
				}
			});
	    		    	
	    	actionListenersButtonLetters(buttonLetters);
		}
		
	}
	
	public void containerForCrossWord () {
		
		List<String> stringsCrossWord = inputArrays.listCrossWords.get(intIndex); 

 	    for (String string : stringsCrossWord) {
 			
 	    	containerDropTarget = new Container(new GridLayout(1,1)) {
 			    @Override
 			    public void drop(Component dragged, int x, int y) {
 			        super.drop(dragged, x, y); 
 			        setUIID("Container");
 			    }
 			};
 			containerDropTarget.setName(string);
 			containerBase.add(containerDropTarget);
 			
 			if (!string.equals("")) {
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolder");
 				containerDropTarget.setDropTarget(true);	
 				listContainers.add(containerDropTarget);
 			} else {
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolderNull");
 			}
 			
 			if (containerDropTarget.getName().equals("ButtonOne")) {
 				containerDropTarget.add(buttonOne);
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolderNull");
 			} else if (containerDropTarget.getName().equals("ButtonTwo")) {
 				containerDropTarget.add(buttonTwo);
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolderNull");
 			} else if (containerDropTarget.getName().equals("ButtonThree")) {
 				containerDropTarget.add(buttonThree);
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolderNull");
 			}  else if (containerDropTarget.getName().equals("ButtonFour")) {
 				containerDropTarget.add(buttonFour);
 				containerDropTarget.setUIID("CrossWordsContainerLetterHolderNull");
 			} 

 		}
		
	}
	
	public void buttonDialog (Button button , String string) {
		
		InteractionDialog  dialog = new InteractionDialog();
		TextArea popupArea = new TextArea(2, 9);
		dialog.setDisposeWhenPointerOutOfBounds(true);
        dialog.setUIID("Popup");
        popupArea.setUIID("PopupBody");
        popupArea.setEditable(false);
		popupArea.setText(string);
		dialog.add(popupArea);
		dialog.showPopupDialog(button);

	}
	
	public void dialogFirstNext () throws IOException {
		// Disable components until dialog is  closed		
		for (Component component : ButtonContainer.containerButtonNext) {
			component.setEnabled(false);
		}
		// Inform wether container  is imported 
		pojos.setStringFirstRun("imported container");
		// Set preference to disable dialog
		pojos.setStringPreferences("FirstRunNext");
		// Call first run dialog
		cfd.firstRunNext(pojos, buttonInfo, buttonNext, buttonAgain, containerNext);
		Dialog dialog = cfd.getDialogNext();
		dialog.showPopupDialog(buttonAgain);	
		buttonOk.setEnabled(false);
	}
	
	public void dialogFirstRunButtonOk() {
				
		cfd.firstRunOK(null , null, pojos, buttonOk , formCrossWords);
		
		UITimer timerThree = new UITimer(new Runnable() {			
			@Override
			public void run() {		

				pojos.setStringPreferences("CwFirstRunOk");
			
				Dialog dialog = cfd.getDialogStart();
				dialog.showPopupDialog(buttonOk);	
				
			}
		});
		timerThree.schedule(500,false,formCrossWords);
		
	}
	
	public void dialogFirstRunStart (Pojos pojos) {

		UITimer timerTwo = new UITimer(new Runnable() {			
				@Override
				public void run() {	
					pojos.setStringFirstRun("Clique nos botões para ver a pergunta e arraste as letras para os campos abaixo.... ");
					pojos.setStringPreferences("CwFirstRunStart");
					// Call first run dialog
					cfd.dialogFirstRunStart(pojos, formCrossWords);
					// Call avtivation constructor
					cfd.enableStart(containerButtons);
					// Create interaction dialog
					Dialog dialog = cfd.getDialogStart();
					dialog.showPopupDialog(buttonOne);
					
			}
		});
		timerTwo.schedule(500,false,formCrossWords);
		
	}
	
	public void styles () throws IOException {
		
		Display.getInstance().lockOrientation(true);
		Image imageTop = Image.createImage("/germanyb.jpeg");
		
		containerButtons.setUIID("CrossWordButtonContainer");
		containerButtons.setDropTarget(true);
		
		containerBase.setUIID("CrossWordsCcontainerBase");
		
		containerNext.getAllStyles().setMargin(60,80,50,50);
	
		buttonOne.setUIID("CrossWordButtonQuestion");
		buttonTwo.setUIID("CrossWordButtonQuestion");
		buttonThree.setUIID("CrossWordButtonQuestion");
		buttonFour.setUIID("CrossWordButtonQuestion");
		
		if (intIndex == 0) {
			buttonThree.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "", 4));
		}
		
		if (intIndex == 4) {
			buttonTwo.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "", 4));
		}
		
		if (intIndex == 5) {
			buttonTwo.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "", 4));
		}
		
		if (intIndex == 6) {
			buttonTwo.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_DOWNWARD, "", 4));
			buttonFour.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 4));
		}
		
		formCrossWords.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
		formCrossWords.getAllStyles().setBgImage(imageTop);
		formCrossWords.getAllStyles().setBgColor(ColorUtil.BLACK);
		formCrossWords.getAllStyles().setBgTransparency(255);
		formCrossWords.getAllStyles().setPadding(0,150,0,0);
		
		Toolbar toolbar = formCrossWords.getToolbar();
		toolbar.setEnabled(false);

	}
	
}
