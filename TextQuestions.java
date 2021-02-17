package com.torus.A1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.InteractionDialog;
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

public class ClassTextQuestions extends Form {
	
	private Button buttonDialogAgain;
	private Button buttonDialogHome;
	private Button buttonAgain;
	private Button buttonInformation;
	private Button buttonNext;
	private Button buttonOk;
	private ClassFirstRunDialogs cfd = new ClassFirstRunDialogs ();
	private ClassInputArrays classInputArrays = new ClassInputArrays();
	private ClassPojos classPojos = new ClassPojos();
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
	
	public ClassTextQuestions(Form formBack, ClassPojos2 classPojos2, Label label) throws IOException {

		// Back command
	    Command back = new Command("A") {
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	        	label.setText(classPojos2.getintLabelCount() + "/ 95");
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
					dialogFirstRunStart(classPojos);
				}
			}
		});
		timer.schedule(500,false,formTextQuestions);
	    
	    // Load lists 
	    classInputArrays.consolidatedLists();
	    
	    // Readingtext-label
		if (indexNext > 10) {			
			spanLabelText.setText(classInputArrays.listReadingTexts.get(1));			
		} else {
			spanLabelText.setText(classInputArrays.listReadingTexts.get(0));
		}	    
	    
	    // Question label
	    labelQuestion.setText(classInputArrays.listReadingQuestions.get(indQuestions));
	    
	    // Pull container with option-buttons
	    containerOptions();
	    
	    // Put everything together
	    formTextQuestions.add(spanLabelText);
		formTextQuestions.add(new Container().add(FlowLayout.encloseLeftMiddle(labelQuestion)));
	    formTextQuestions.add(containerButtons);
	    formTextQuestions.add(ClassButtonContainer.containerMenu());
	    formTextQuestions.add(labelDummy);
	   
	    styles();
	    
	    formTextQuestions.show();

	   
	    buttonActions(formBack, classPojos2, label);
		
	    
	}
	
	public void buttonActions (Form formBack, ClassPojos2 classPojos2, Label label) {
				
		// Button Information explains the mmatter
		buttonInformation = ClassButtonContainer.getButtonInfromation();
		buttonInformation.addActionListener(l->{
			try {
				classPojos.setintTotal(indQuestions);
				classPojos.setDialogList(classInputArrays.listReadingExplanation);
				new ClassFeedback().dialogExplainFrases(classPojos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		// Button next frame
		buttonNext = ClassButtonContainer.getButtonNext();
		buttonNext.addActionListener(l->{
			
			if (indexNext == classInputArrays.listReadingTextControls.size()) {
				
				try {
					ClassFeedback cf = new ClassFeedback();
					cf.dialogFinal(classPojos);
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
					
					Preferences.set("IndexQuestions",classPojos.getintTotal());
					Preferences.set("QuestionsCorrectAnswers",classPojos.getintCorrects());
					Preferences.set("QuestionsFalseAnswers",classPojos.getintFalse());
					new ClassTextQuestions(formBack, classPojos2, label);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		// Button Ok
		buttonOk = ClassButtonContainer.getButtonOk();
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
			
			classPojos.setintTotal(indexNext);
			
			String stringPojo = classPojos.getStringControlTextQuestions().trim();
			String stringControl = classInputArrays.listReadingTextControls.get(indQuestions).trim();
						
			for (Component component : containerButtons) {
				
				component.setEnabled(false);
//				component = new Button();
				if (stringPojo.equals(stringControl) && component.getName().equals("Clicked")) {
					try {						
						// set feedback sound okay
						new ClassFeedback().soundCorrect(media,inputStream);
						// set background color of button
						component.setUIID("QuestionsButtonChoiceCorrect");					
						// set slider value
						sliderCorrect.setText(String.valueOf(Preferences.get("QuestionsCorrectAnswers",0)+1));
						sliderCorrect.setProgress(Preferences.get("QuestionsCorrectAnswers",0)+1);
						// pass both slider values to next button
						classPojos.setintCorrects(Integer.valueOf(sliderCorrect.getText()));
						classPojos.setintFalse(Integer.valueOf(sliderFalse.getText()));						
					} catch (IOException e) {
						e.printStackTrace();
					}					
				} else if (stringPojo != stringControl && component.getName().equals("Clicked")){
					try {
						new ClassFeedback().soundInCorrect(media,inputStream);
						component.setUIID("QuestionsButtonChoiceFalse");
						sliderFalse.setText(String.valueOf(Preferences.get("QuestionsFalseAnswers",0)+1));
						sliderFalse.setProgress(Preferences.get("QuestionsFalseAnswers",0)+1);
						classPojos.setintCorrects(Integer.valueOf(sliderCorrect.getText()));
						classPojos.setintFalse(Integer.valueOf(sliderFalse.getText()));		
					} catch (IOException e) {
						e.printStackTrace();
					}
								
				}				
			}
		});
		
		// Button again
		buttonAgain = ClassButtonContainer.getButtonAgain();
		buttonAgain.addActionListener(l->{		
			try {
				new ClassTextQuestions(formBack, classPojos2, label);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		// Label indicator of number of current question 
		labelCount = ClassButtonContainer.getLabelCount();
		labelCount.setText(Integer.toString(indexNext) + "/ "  + classInputArrays.listReadingTextControls.size());
		
		// sliders		
		sliderCorrect = ClassButtonContainer.getSliderCorrect();
		sliderCorrect.setText(String.valueOf(Preferences.get("QuestionsCorrectAnswers",0)));
		sliderCorrect.setProgress(Preferences.get("QuestionsCorrectAnswers",0));
		sliderCorrect.setMaxValue(25);
		
		sliderFalse = ClassButtonContainer.geSliderFalse();
		sliderFalse.setText(String.valueOf(Preferences.get("QuestionsFalseAnswers",0)));
		sliderFalse.setProgress(Preferences.get("QuestionsFalseAnswers",0));
		sliderFalse.setMaxValue(25);
		
	}
	
	public void buttonDialogActions(Form formBack, ClassPojos2 classPojos2, Label label) {
		
		// Button dialog again 
		buttonDialogAgain.addActionListener(l-> {					
			try {
				Preferences.set("IndexQuestions",0);
				Preferences.set("QuestionsCorrectAnswers",0);
				Preferences.set("QuestionsFalseAnswers",0);
				new ClassTextQuestions(formBack, classPojos2, label);
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
				new ClassStartScreen().startScreen(classPojos, classPojos2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	public void containerOptions() {
		
		List<String> listOption = classInputArrays.listReadingButtons.get(indQuestions);
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
				classPojos.setStringControlTextQuestions(button.getText());
				// enable Ok button
				buttonOk.setEnabled(true);
				
				if (Preferences.get("FirstRunButtonsQuestions",0) == 0) {		
					formTextQuestions.scrollComponentToVisible(buttonAgain);
					dialogFirstRunButtonOk();
				}
				
			});
		}
	
	}
	
	public void dialogFirstRunStart (ClassPojos classPojos) {

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
					classPojos.setStringFirstRun("Leia o texto acima e responda às perguntas clicando nos botões .... ");
					classPojos.setStringPreferences("FirstRunQuestions");
					// Call first run dialog
					cfd.dialogFirstRunStart(classPojos, formTextQuestions);
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
		
		cfd.firstRunOK(null , this, classPojos, buttonOk , formTextQuestions);
		
		UITimer timerThree = new UITimer(new Runnable() {			
			@Override
			public void run() {		

				classPojos.setStringPreferences("FirstRunButtonsQuestions");
			
				Dialog dialog = cfd.getDialogStart();
				dialog.showPopupDialog(buttonOk);	

			}
		});
		timerThree.schedule(500,false,formTextQuestions);
		
	}
	
	public void dialogFirstNext () throws IOException {
		// Disable components until dialog is  closed
		for (Component component : ClassButtonContainer.containerButtonNext) {
			component.setEnabled(false);
		}
		// Inform wether container  is imported 
		classPojos.setStringFirstRun("imported container");
		// Set preference to disable dialog
		classPojos.setStringPreferences("FirstRunNext");
		// Call first run dialog
		cfd.firstRunNext(classPojos, buttonInformation, buttonNext, buttonAgain, containerNext);
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
		
		containerNext = ClassButtonContainer.getContainerNext();
		containerNext.getAllStyles().setMargin(40,100,0,0);
		
	}

}
