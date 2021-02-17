package com.torus.a1test.en;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
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
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

public class TextQuestions extends Form {
	
	private Button buttonDialogAgain;
	private Button buttonDialogHome;
	private Button buttonAgain;
	private Button buttonInformation;
	private Button buttonNext;
	private Button buttonOk;
	private FirstRunDialogs cfd = new FirstRunDialogs ();
	private InputArrays inputArrays = new InputArrays();
	private Pojos pojos = new Pojos();
	private Container containerButtons;
	private Container containerNext;
	private Form formTextQuestions = new Form(new BoxLayout(BoxLayout.Y_AXIS));
	private Label labelCount;
	private Label labelDummy = new Label();
	private int disHeight = Display.getInstance().getDisplayHeight();
	private int indQuestions = Preferences.get("IndexQuestions",0);
	private int indexNext = indQuestions + 1;
	private int intLabelCount;
	private InputStream inputStream;
	private Media media;
	private Font redHatFont = Font.createTrueTypeFont("Red Hat Text Medium", "RedHatText-Regular.ttf").derive(disHeight/50, Font.STYLE_PLAIN); 
	private SpanLabel spanLabelText = new SpanLabel();
	private SpanLabel labelQuestion = new SpanLabel();
	private Slider sliderCorrect;
	private Slider sliderFalse;
	private Toolbar toolbar = formTextQuestions.getToolbar();
	
	public TextQuestions(Form formBack, Pojos2 pojos2, Label label) throws IOException {

		// Back command
	    Command back = new Command("A") {
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	label.setText(pojos2.getintLabelCount() + "/ 95");
	        	formBack.showBack();
	        }
	    }; 
	    formTextQuestions.setBackCommand(back);
	    // Scroll to ButtonOk
	    UITimer timer = new UITimer(new Runnable() {			
			@Override
			public void run() {		
				if (Preferences.get("FirstRunQuestions",0) == 0) {		
					formTextQuestions.scrollComponentToVisible(buttonOk);
					dialogFirstRunStart(pojos);
				}
			}
		});
		timer.schedule(500,false,formTextQuestions);
	    
	    // Load lists 
	    inputArrays.consolidatedLists();
	    
	    // Readingtext-label
		if (indexNext > 10) {			
			spanLabelText.setText(inputArrays.listReadingTexts.get(1));			
		} else {
			spanLabelText.setText(inputArrays.listReadingTexts.get(0));
		}	    
	    
	    // Question label
	    labelQuestion.setText(inputArrays.listReadingQuestions.get(indQuestions));
	    
	    // Pull container with option-buttons
	    containerOptions();
	    
	    // Put everything together
	    formTextQuestions.add(spanLabelText);
		formTextQuestions.add(new Container().add(FlowLayout.encloseLeftMiddle(labelQuestion)));
	    formTextQuestions.add(containerButtons);
	    formTextQuestions.add(ButtonContainer.containerMenu());
	    formTextQuestions.add(labelDummy);
	   
	    styles();
	    
	    formTextQuestions.show();

	   
	    buttonActions(formBack, pojos2, label);
		
	    
	}
	
	public void buttonActions (Form formBack, Pojos2 classPojos2, Label label) {
				
		// Button Information explains the mmatter
		buttonInformation = ButtonContainer.getButtonInfromation();
		buttonInformation.addActionListener(l->{
			try {
				pojos.setintTotal(indQuestions);
				pojos.setDialogList(inputArrays.listReadingExplanation);
				new Feedback().dialogExplainFrases(pojos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		// Button next frame
		buttonNext = ButtonContainer.getButtonNext();
		buttonNext.addActionListener(l->{
			
			if (indexNext == inputArrays.listReadingTextControls.size()) {
				
				try {
					Feedback cf = new Feedback();
					cf.dialogFinal(pojos);
					buttonDialogHome = cf.getButtonDialogHome();
					buttonDialogAgain = cf.getButtonDialogRepeat();
					buttonDialogActions(formBack, classPojos2, label);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				
				try {
					
					intLabelCount = Preferences.get("CwCorrectAnswers",0) + Preferences.get("CorrectAnswers",0) 
					+ Preferences.get("FrasesCorrectAnswers",0) + sliderCorrect.getProgress();
					classPojos2.setintLabelCount(intLabelCount);
					
					Preferences.set("IndexQuestions",pojos.getintTotal());
					Preferences.set("QuestionsCorrectAnswers",pojos.getintCorrects());
					Preferences.set("QuestionsFalseAnswers",pojos.getintFalse());
					new TextQuestions(formBack, classPojos2, label);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		// Button Ok
		buttonOk = ButtonContainer.getButtonOk();
		buttonOk.addActionListener(l->{
			
			buttonOk.setEnabled(false);
			
			if (Preferences.get("FirstRunNext",0) == 0) {	
				formTextQuestions.scrollComponentToVisible(labelDummy);
				
				UITimer timer = new UITimer (new Runnable() {
				@Override
				public void run() {
						try {
							dialogFirstNext();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				timer.schedule(500,false,formTextQuestions);
			}
			
			pojos.setintTotal(indexNext);
			
			String stringPojo = pojos.getStringControlTextQuestions().trim();
			String stringControl = inputArrays.listReadingTextControls.get(indQuestions).trim();
						
			for (Component component : containerButtons) {
				
				component.setEnabled(false);
//				component = new Button();
				if (stringPojo.equals(stringControl) && component.getName().equals("Clicked")) {
					try {						
						// set feedback sound okay
						new Feedback().soundCorrect(media,inputStream);
						// set background color of button
						component.setUIID("QuestionsButtonChoiceCorrect");					
						// set slider value
						sliderCorrect.setText(String.valueOf(Preferences.get("QuestionsCorrectAnswers",0)+1));
						sliderCorrect.setProgress(Preferences.get("QuestionsCorrectAnswers",0)+1);
						// pass both slider values to next button
						pojos.setintCorrects(Integer.valueOf(sliderCorrect.getText()));
						pojos.setintFalse(Integer.valueOf(sliderFalse.getText()));						
					} catch (IOException e) {
						e.printStackTrace();
					}					
				} else if (stringPojo != stringControl && component.getName().equals("Clicked")){
					try {
						new Feedback().soundInCorrect(media,inputStream);
						component.setUIID("QuestionsButtonChoiceFalse");
						sliderFalse.setText(String.valueOf(Preferences.get("QuestionsFalseAnswers",0)+1));
						sliderFalse.setProgress(Preferences.get("QuestionsFalseAnswers",0)+1);
						pojos.setintCorrects(Integer.valueOf(sliderCorrect.getText()));
						pojos.setintFalse(Integer.valueOf(sliderFalse.getText()));		
					} catch (IOException e) {
						e.printStackTrace();
					}
								
				}				
			}
		});
		
		// Button again
		buttonAgain = ButtonContainer.getButtonAgain();
		buttonAgain.addActionListener(l->{		
			try {
				new TextQuestions(formBack, classPojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		// Label indicator of number of current question 
		labelCount = ButtonContainer.getLabelCount();
		labelCount.setText(Integer.toString(indexNext) + "/ "  + inputArrays.listReadingTextControls.size());
		
		// sliders		
		sliderCorrect = ButtonContainer.getSliderCorrect();
		sliderCorrect.setText(String.valueOf(Preferences.get("QuestionsCorrectAnswers",0)));
		sliderCorrect.setProgress(Preferences.get("QuestionsCorrectAnswers",0));
		sliderCorrect.setMaxValue(25);
		
		sliderFalse = ButtonContainer.geSliderFalse();
		sliderFalse.setText(String.valueOf(Preferences.get("QuestionsFalseAnswers",0)));
		sliderFalse.setProgress(Preferences.get("QuestionsFalseAnswers",0));
		sliderFalse.setMaxValue(25);
		
	}
	
	public void buttonDialogActions(Form formBack, Pojos2 pojos2, Label label) {
		
		// Button dialog again 
		buttonDialogAgain.addActionListener(l-> {					
			try {
				Preferences.set("IndexQuestions",0);
				Preferences.set("QuestionsCorrectAnswers",0);
				Preferences.set("QuestionsFalseAnswers",0);
				new TextQuestions(formBack, pojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}					
		});
				
		// Button dialog home to start screen 
		buttonDialogHome.addActionListener(l->{
			try {
				Preferences.set("IndexQuestions",0);
				Preferences.set("QuestionsCorrectAnswers",0);
				Preferences.set("QuestionsFalseAnswers",0);
				new StartScreen().startScreen(pojos, pojos2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	public void containerOptions() {
		
		List<String> listOption = inputArrays.listReadingButtons.get(indQuestions);
		Collections.shuffle(listOption);
		
		containerButtons = new Container(new GridLayout(2,2));
		
		for (String string : listOption) {
			
			SpanButton button = new SpanButton(string);
			containerButtons.add(button);
			button.setUIID("QuestionsButtonChoice");
			button.setTextUIID("QuestionsButtonChoiceText");
			
			button.addActionListener( l-> {

				// clear name
				for (Component component : containerButtons) {
					component.setName("Null");
				}
				// name clicked button
				button.setName("Clicked");
				// style clicked and unclicked buttons
				for (Component component : containerButtons) {					
					if (component.getName().equals("Clicked")) {
						component.setUIID("QuestionsButtonChoiceSelected");
					} else  {
						component.setUIID("QuestionsButtonChoice");
					}
				}
				// get button text to compare with control string
				pojos.setStringControlTextQuestions(button.getText());
				// enable Ok button
				buttonOk.setEnabled(true);
				
				if (Preferences.get("FirstRunButtonsQuestions",0) == 0) {		
					formTextQuestions.scrollComponentToVisible(buttonAgain);
					dialogFirstRunButtonOk();
				}
				
			});
		}
	
	}
	
	public void dialogFirstRunStart (Pojos pojos) {

		UITimer timerTwo = new UITimer(new Runnable() {			
				@Override
				public void run() {	
//					// Disable all buttons until dialog is closed
//					for (Component component : containerButtons) {
//						component.setEnabled(false);
//					}
					// Make form unscrollable until dialog is closed
//					formTextQuestions.setScrollableY(false);
					// Set dialog text and disable preference 
					pojos.setStringFirstRun("Leia o texto acima e responda às perguntas clicando nos botões .... ");
					pojos.setStringPreferences("FirstRunQuestions");
					// Call first run dialog
					cfd.dialogFirstRunStart(pojos, formTextQuestions);
					// Call avtivation constructor
					cfd.enableStart(containerButtons);
					// Create interaction dialog
					Dialog dialog = cfd.getDialogStart();
					dialog.showPopupDialog(spanLabelText);
			}
		});
		timerTwo.schedule(500,false,formTextQuestions);
		
	}
	
	public void dialogFirstRunButtonOk() {
		
		cfd.firstRunOK(null , this, pojos, buttonOk , formTextQuestions);
		
		UITimer timerThree = new UITimer(new Runnable() {			
			@Override
			public void run() {		

				pojos.setStringPreferences("FirstRunButtonsQuestions");
			
				Dialog dialog = cfd.getDialogStart();
				dialog.showPopupDialog(buttonOk);	

			}
		});
		timerThree.schedule(500,false,formTextQuestions);
		
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
		cfd.firstRunNext(pojos, buttonInformation, buttonNext, buttonAgain, containerNext);
		Dialog dialog = cfd.getDialogNext();
		dialog.showPopupDialog(buttonAgain);	
		buttonOk.setEnabled(false);

	}
	
	public void styles () throws IOException {
		
		Button.setCapsTextDefault(false);
		Display.getInstance().lockOrientation(true);
		
		labelQuestion.setUIID("QuestionsLabelQuestion");
		labelQuestion.setTextUIID("QuestionsLabelQuestionText");
				
		formTextQuestions.getAllStyles().setBgColor(ColorUtil.BLACK);
		formTextQuestions.getAllStyles().setBgTransparency(255);
		formTextQuestions.getAllStyles().setPadding(0, 50, 10, 10);
		formTextQuestions.setScrollableY(true);
		formTextQuestions.setScrollVisible(true);
		
		Image imageTop = Image.createImage("/germany.jpeg");
		ScaleImageLabel scaleImageLabel = new ScaleImageLabel(); 
		scaleImageLabel.getAllStyles().setBgImage(imageTop);
		scaleImageLabel.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
		scaleImageLabel.getAllStyles().setPadding(0, 0, 0, 0);
		
		spanLabelText.setUIID("QuestionsSpanLabelText");
		spanLabelText.setTextUIID("QuestionsSpanLabelText");
		spanLabelText.getAllStyles().setMargin(100,60,20,20);
		spanLabelText.getTextAllStyles().setBorder(Border.createEmpty());
		spanLabelText.getTextAllStyles().setFont(redHatFont);
		
		toolbar.add(BorderLayout.CENTER,scaleImageLabel);
		toolbar.getAllStyles().setBorder(Border.createEmpty());	
		toolbar.getAllStyles().setPadding(0, 0, 0, 0);
		
		containerNext = ButtonContainer.getContainerNext();
		containerNext.getAllStyles().setMargin(40,100,0,0);
		
	}

}