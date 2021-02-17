package com.torus.A1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.media.Media;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

public class ClassFrases extends Form {

	private Button buttonAgain;
	private Button buttonDialogAgain;
	private Button buttonDialogHome;
	private Button buttonInformation;
	private Button buttonNext;
	private Button buttonOk;
	private ClassPojos classPojos = new ClassPojos();
	private ClassFirstRunDialogs classFirstRunDialogs = new ClassFirstRunDialogs();
	private ClassInputArrays classInputArrays = new ClassInputArrays();
	private Container containerOptions = new Container(new BorderLayout());
	private Container containerButtons = new Container();
	private Container containerDummy = new Container();
	private Image imageTop;
	private int fontSize = Display.getInstance().getDisplayHeight();
	private Font redHatFont = Font.createTrueTypeFont("Red Hat Text Regular", "RedHatText-Regular.ttf").derive(fontSize/40, Font.STYLE_PLAIN); // 30
	private Font redHatFontRadio = Font.createTrueTypeFont("Red Hat Text Regular", "RedHatText-Regular.ttf").derive(fontSize/45, Font.STYLE_PLAIN); // 35
	private Font redHatFontRadioPressed = Font.createTrueTypeFont("Red Hat Text Regular", "RedHatText-Regular.ttf").derive(fontSize/42, Font.STYLE_PLAIN); // 33
	private Form formFrases =  new Form(new BoxLayout(BoxLayout.Y_AXIS));
	private Label labelCount; 
	private InputStream inputStream;
	private int indexFrases = Preferences.get("IndexFrases",0);
	private int indexTotal ;
	private int intLabelCount;
	private Media media;
	private ScaleImageLabel scaleImageLabel = new ScaleImageLabel(); 
	private SpanLabel labelQuery = new SpanLabel();
	private Slider sliderCorrect;
	private Slider sliderFalse;
	
	private Toolbar toolbar = formFrases.getToolbar();
	
	public ClassFrases (Form formBack, ClassPojos2 classPojos2, Label label) throws IOException {
		
	    Command back = new Command("A") {
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	
	        	label.setText(classPojos2.getintLabelCount() + "/ 95");
	        	formBack.showBack();
	        }
	    }; 
	    formFrases.setBackCommand(back);
	    
		if (Preferences.get("FirstRunFrasesStart",0) == 0) {					
			
			dialogFirstRunStart();
		
		}
		
		formFrases.add(labelQuery).add(containerOptions)
		.add(ClassButtonContainer.containerMenu()).add(containerDummy);

	    initialLoads();
	    containerOptions();
	    spanLabelSettings();
	    buttonActions(formBack, classPojos2, label);
		styles();

		formFrases.show();
	};
	
	public void buttonActions(Form formBack, ClassPojos2 classPojos2, Label label) {
		
		buttonAgain.addActionListener(l-> {
			try {
				new ClassFrases(formBack, classPojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buttonInformation.addActionListener(l -> {

			try {
				ArrayList <String> listDialog = classInputArrays.listFrasesExplanation;
				classPojos.setDialogList(listDialog);
				classPojos.setintTotal(indexFrases);
				new ClassFeedback().dialogExplainFrases(classPojos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		// Next button definitions
		int indexNext = Preferences.get("IndexFrases",0) + 1;
		buttonNext.addActionListener(l -> {
			if (indexNext == indexTotal) {
				try {
					int corrects = sliderCorrect.getProgress();
					int falses = sliderFalse.getProgress();

					classPojos.setintTotal(indexTotal);
					classPojos.setintCorrects(corrects);
					classPojos.setintFalse(falses);

					ClassFeedback cf = new ClassFeedback();
					cf.dialogFinal(classPojos);
					buttonDialogAgain = cf.getButtonDialogRepeat();
					buttonDialogHome = cf.getButtonDialogHome();
					buttonActionsDialog(formBack, classPojos2, label);
							
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					
					intLabelCount = Preferences.get("CwCorrectAnswers",0) + Preferences.get("CorrectAnswers",0) 
					+ sliderCorrect.getProgress() + Preferences.get("QuestionsCorrectAnswers",0);
					classPojos2.setintLabelCount(intLabelCount);
					
					Preferences.set("FrasesCorrectAnswers",classPojos.getintCorrects());
					Preferences.set("FrasesFalseAnswers",classPojos.getintFalse());
					Preferences.set("IndexFrases",indexNext);
					new ClassFrases(formBack, classPojos2, label);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonOk.addActionListener(l -> {
			if (Preferences.get("FirstRunNext",0) == 0) {	
	    		formFrases.scrollComponentToVisible(containerDummy);
	    		UITimer timerTwo = new UITimer(new Runnable() {
	    			@Override
	    			public void run() {
	    				try {
							dialogFirstRunNext();
						} catch (IOException e) {
							e.printStackTrace();
						}
	    			}	
	    		});
	    		timerTwo.schedule(500,false, formFrases);									
			}
		});
		
	}
	
	public void buttonActionsDialog (Form formBack, ClassPojos2 classPojos2, Label label) {
		
		buttonDialogAgain.addActionListener(l-> {
			try {
				Preferences.set("IndexFrases",0); 
				Preferences.set("FrasesCorrectAnswers",0);
				Preferences.set("FrasesFalseAnswers" , 0);
				new ClassFrases(formBack, classPojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buttonDialogHome.addActionListener(l-> {
			
			try {
				Preferences.set("IndexFrases",0); 
				Preferences.set("FrasesCorrectAnswers",0);
				Preferences.set("FrasesFalseAnswers" , 0);
				new ClassStartScreen().startScreen(classPojos, classPojos2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public void dialogFirstRunButtonOk() {
		
//		buttonOk.setEnabled(false);
		
		for (Component component : containerButtons) {
			component.setEnabled(false);
		}
		
		classFirstRunDialogs.firstRunOK(this, null , classPojos, buttonOk, formFrases);
		
		UITimer timer = new UITimer(new Runnable() {

			@Override
			public void run() {
				
				classPojos.setStringPreferences("FirstRunFrasesRadio");
				
				Dialog dialog = classFirstRunDialogs.getDialogStart();
				dialog.showPopupDialog(buttonOk);	
				
			}
			
		});
		timer.schedule(500,false, formFrases);
		

	}
	
	public void dialogFirstRunStart  () throws IOException {

		UITimer timerTwo = new UITimer(new Runnable() {			
			@Override
			public void run() {	
				// Disable all buttons until dialog is closed
//				for (Component component : containerButtons) {
//					component.setEnabled(false);
//				}
				// Make form unscrollable until dialog is closed
//				formFrases.setScrollableY(false);
				// Set dialog text and disable preference 
				classPojos.setStringFirstRun("Escolha embaixo uma resposta para esta pergunta .... ");
				classPojos.setStringPreferences("FirstRunFrasesStart");
				// Call first run dialog
				classFirstRunDialogs.dialogFirstRunStart(classPojos, formFrases);
				// Call avtivation constructor
				classFirstRunDialogs.enableStart(containerButtons);
				// Create interaction dialog				
				Dialog dialog = classFirstRunDialogs.getDialogStart();
				dialog.setDisposeWhenPointerOutOfBounds(false);
				dialog.showPopupDialog(labelQuery);

				}
			});
			timerTwo.schedule(500,false,formFrases);
			
		
	}
	
	public void dialogFirstRunNext () throws IOException {
		// Disable components until dialog is  closed
		for (Component component : ClassButtonContainer.containerButtonNext) {
			component.setEnabled(false);
		}
		// Inform wether container  is imported 
		classPojos.setStringFirstRun("imported container");
		// Set preference to disable dialog 
		classPojos.setStringPreferences("FirstRunNext");
		// Call first run dialog
		classFirstRunDialogs.firstRunNext(classPojos, buttonInformation, buttonNext, buttonAgain, containerButtons);
		Dialog dialog = classFirstRunDialogs.getDialogNext();
		dialog.showPopupDialog(buttonAgain);
		for (Component component : containerOptions) {
			component.setEnabled(false);
		}		
		for (Component component : ClassButtonContainer.containerButtonNext) {
			component.setEnabled(true);
		}
		buttonOk.setEnabled(false);
	}
	
	public void initialLoads() throws IOException {
		
		buttonAgain = ClassButtonContainer.getButtonAgain();

		buttonInformation = ClassButtonContainer.getButtonInfromation();
		buttonOk = ClassButtonContainer.getButtonOk();
		buttonNext = ClassButtonContainer.getButtonNext();
		classInputArrays.consolidatedLists();
		
		indexTotal = classInputArrays.listFrasescontrolStrings.size();

		imageTop = Image.createImage("/germany.jpeg");
		toolbar.add(BorderLayout.CENTER,scaleImageLabel);
		
		int indexNext = Preferences.get("IndexFrases",0) + 1;
		labelCount = ClassButtonContainer.getLabelCount();
		labelCount.setText(Integer.toString(indexNext) + "/ "  + indexTotal);
		
		sliderCorrect = ClassButtonContainer.getSliderCorrect();
		sliderCorrect.setText(String.valueOf(Preferences.get("FrasesCorrectAnswers",0)));
		sliderCorrect.setEditable(false);
		sliderCorrect.setMinValue(0);
		sliderCorrect.setMaxValue(indexTotal);
		sliderCorrect.setProgress(Preferences.get("FrasesCorrectAnswers",0));
		
		sliderFalse = ClassButtonContainer.geSliderFalse();	
		sliderFalse.setText(String.valueOf(Preferences.get("FrasesFalseAnswers",0)));
		sliderFalse.setEditable(false);
		sliderFalse.setMinValue(0);
		sliderFalse.setMaxValue(indexTotal);
		sliderFalse.setProgress(Preferences.get("FrasesFalseAnswers",0));
	}
	
	public void containerOptions () {
		
		// Get display height for margins and padding
		int height = Display.getInstance().getDisplayHeight();

		// Create radio container
		containerButtons = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		containerButtons.setUIID("FraseContainerOptions");
		
		// Populate radio container
		List<String> listOption = classInputArrays.listFrasesOption.get(indexFrases);
		Collections.shuffle(listOption);
		
		for (String string : listOption) {
			RadioButton radioButton = new RadioButton("  " + string);
		    radioButton.setGroup("locations"); 
		    radioButton.setUIID("FrasesRadio");
		    
		    radioButton.setName(string);
		    containerButtons.addComponent(radioButton);
		    
		    radioButton.addActionListener(l-> {
		    	// Enable ok-button
		    	ClassButtonContainer.buttonOk.setEnabled(true);
		    	// disable ok buttton until first run dialog is deactivated
		    	if (Preferences.get("FirstRunFrasesRadio",0) == 0) {	
		    		formFrases.scrollComponentToVisible(buttonAgain);
		    		dialogFirstRunButtonOk();	
				}	
		    	// pass string for checking
		    	String stringText = radioButton.getText();
		    	classPojos.setStringRadioFrases(stringText);
		    });
		    
		    buttonOk.addActionListener(l -> {
		    			    	
		    	String stringRadio = classPojos.getStringRadioFrases().trim();
				String stringControl = classInputArrays.listFrasescontrolStrings.get(indexFrases);
		    	radioButton.setEnabled(false);
		    	boolean selected = radioButton.isSelected();
		    	if (selected == true && stringRadio.equals(stringControl)) {
		    		classPojos.setintCorrects(Preferences.get("FrasesCorrectAnswers",0) + 1);
		    		classPojos.setintFalse(Preferences.get("FrasesFalseAnswers",0));
		    		sliderCorrect.setText(String.valueOf(Preferences.get("FrasesCorrectAnswers",0) + 1));
		    		sliderCorrect.setProgress(Preferences.get("FrasesCorrectAnswers",0) + 1);
		    		// Styles
		    		radioButton.setUIID("FrasesRadioRight");
					try {
						new ClassFeedback().soundCorrect(media, inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (selected == true && stringRadio != stringControl) {
					classPojos.setintFalse(Preferences.get("FrasesFalseAnswers",0) + 1);
					classPojos.setintCorrects(Preferences.get("FrasesCorrectAnswers",0));
					sliderFalse.setText(String.valueOf(Preferences.get("FrasesFalseAnswers",0) + 1));
		    		sliderFalse.setProgress(Preferences.get("FrasesFalseAnswers",0) + 1);
		    		// Styles
		    		radioButton.setUIID("FrasesRadioWrong");
					try {
						new ClassFeedback().soundInCorrect(media, inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		    	
		    	buttonOk.setEnabled(false);
		    });
		}

		// Put everything together 
		containerOptions.add(BorderLayout.CENTER,containerButtons);
		containerOptions.getAllStyles().setMargin(30,0,30,30);

	}
		
	public void styles () {
		
		Button.setCapsTextDefault(false);
		Display.getInstance().lockOrientation(true);
		int displayHeight = Display.getInstance().getDisplayHeight();
		
		Container containerNext = ClassButtonContainer.getContainerNext();
		containerNext.getAllStyles().setMargin(40,100,0,0);
		
		labelQuery.setUIID("FrasesLabelQuery");
		labelQuery.setTextUIID("FrasesLabelQueryText");
		labelQuery.getAllStyles().setMargin(80,40,30,30);
		labelQuery.getAllStyles().setPadding(displayHeight/30,displayHeight/30,25,25);
		labelQuery.getUnselectedStyle().setFont(redHatFont);
		
		formFrases.getAllStyles().setBgColor(ColorUtil.BLACK);
		formFrases.getAllStyles().setBgTransparency(255);
		formFrases.getAllStyles().setPadding(0, 50, 0, 0);
		formFrases.setScrollableY(true);
		formFrases.setScrollVisible(true);
		
		scaleImageLabel.getAllStyles().setBgImage(imageTop);
		scaleImageLabel.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
		scaleImageLabel.getAllStyles().setPadding(0, 0, 0, 0);
		
		toolbar.getAllStyles().setBorder(Border.createEmpty());	
		toolbar.getAllStyles().setPadding(0, 0, 0, 0);
		
		
	}
	
	public void spanLabelSettings() {
		
		String stringLabel = classInputArrays.listFrasesHeaders.get(indexFrases);
		labelQuery.setText(stringLabel);
	}
	

	
	
	

}
