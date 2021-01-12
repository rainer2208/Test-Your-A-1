package com.torus.A1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

public class ClassCorrectWord {
		
	private Button buttonDialogAgain;
	private Button buttonDialogHome;
	private Button buttonNext = new Button();
	public Button buttonAgain = new Button(); 
	public Button buttonInformation = new Button();
	private ClassInputArrays inputArrays;
	private ClassFirstRunDialogs classFirstRunDialogs = new ClassFirstRunDialogs ();
	private ClassFeedback classFeedback = new ClassFeedback();
	private ClassPojos classPojos = new ClassPojos();
	public Command back;
	private Container containerButton = new Container(new GridLayout(2,2));
	private Container containerButtonNext = new Container(new GridLayout(1,4));
	private Container containerProgress = new Container(new GridLayout(1,2));
	private FontImage imageBack;
	private FontImage imageAgain;
	private FontImage imageInfo; 
	public Form formCorrectWords = new Form(new GridLayout(3,1)); 
	private Image imageTop;
	private int indexCorrects;
	private int indexIncorrects;
	private int indexTotal;
	private int intLabelCount;
	private Label labelCount = new Label();
	private List<String> listButtons;
	private InputStream inputStreamCorrect;
	private InputStream inputStreamFalse;
	private Media media;
	public ScaleImageLabel scaleImageLabel = new ScaleImageLabel(); 
	private SpanLabel labelQuery;
	private Slider sliderCorrects;
	private Slider sliderFalses;
	private String stringLabel;
	private String stringControl;
	private Toolbar toolbar = formCorrectWords.getToolbar();
	
	public void correctWords(Form a, Label label, ClassPojos2 classPojos2) throws IOException {
	
		initialLoads();
		
		if (Preferences.get("WordsFirstOne",0) == 0) {
			
			dialogFirstRunStart(classPojos);
		}
		
		Command back = new Command("A") {
	        @Override
	        public void actionPerformed(ActionEvent evt) {

	        	label.setText(classPojos2.getintLabelCount() + "/ 95");
	            a.showBack();
	            
	        }
	    };
	    formCorrectWords.setBackCommand(back);

		// load lists 
		listButtons = inputArrays.listButtons.get(Preferences.get("NextString",0));
		stringLabel = inputArrays.listLabel.get(Preferences.get("NextString",0));
		stringControl = inputArrays.listcontrolStrings.get(Preferences.get("NextString",0));
		
		// random order items from list 
		Collections.shuffle(listButtons);
		
		// add sliders and set
		containerProgress.add(sliderCorrects).add(sliderFalses);
		
		String stringCorrects = String.valueOf(indexCorrects);
		sliderCorrects.setText(stringCorrects);
		sliderCorrects.setEditable(false);
		sliderCorrects.setMinValue(0);
		sliderCorrects.setMaxValue(indexTotal);
		sliderCorrects.setText(stringCorrects);
		sliderCorrects.setProgress(indexCorrects);
		
		String stringFalses = String.valueOf(indexIncorrects);
		sliderFalses.setText(stringFalses);
		sliderFalses.setEditable(false);
		sliderFalses.setMinValue(0);
		sliderFalses.setMaxValue(indexTotal);
		sliderFalses.setText(stringFalses);
		sliderFalses.setProgress(indexIncorrects);
		
		// query label instantiate and populate
		labelQuery = new SpanLabel(stringLabel);
		
		// disable next buttons until needed 
		buttonAgain.setEnabled(false);
		buttonInformation.setEnabled(false);
		buttonNext.setEnabled(false);
		
		// create options buttons, actions and style
		for (int i = 0; i < listButtons.size(); i++) {
			
			String stringButtton = listButtons.get(i); 
			
			SpanButton buttonChoice = new SpanButton(stringButtton);
			buttonChoice.setUIID("ButtonChoice");
			buttonChoice.setTextUIID("ButtonChoiceText");
			
			containerButton.add(buttonChoice); // add buttons to container
			
			buttonChoice.addActionListener(l -> {
				
				buttonChoice.setName("clicked");
				
				String stringTextField = buttonChoice.getText().trim();
				
				if (stringTextField == stringControl) {
					
					buttonChoice.getAllStyles().setBgColor(0x009933);
					buttonChoice.getAllStyles().setBgTransparency(255);
					
					buttonInformation.setEnabled(true);
					buttonNext.setEnabled(true);
					buttonAgain.setEnabled(true);
					
					int indOld = Preferences.get("CorrectAnswers",0);
					int indNew = indOld + 1; 

					Preferences.set("CorrectAnswers" , indNew);
					
					sliderCorrects.setText(String.valueOf(indNew));
					sliderCorrects.setProgress(indNew);
					
					try {
						classFeedback.soundCorrect(media, inputStreamCorrect);
					} catch (IOException e) {
					}
					
					
				} else {
					
					buttonChoice.getAllStyles().setBgColor(0xff1a1a);
					buttonChoice.getAllStyles().setBgTransparency(255);
					
					buttonInformation.setEnabled(true);
					buttonNext.setEnabled(true);
					buttonAgain.setEnabled(true);
					
					int indOld = Preferences.get("InCorrectAnswers",0);
					int indNew = indOld + 1; 

					Preferences.set("InCorrectAnswers" , indNew);
					
					sliderFalses.setText(String.valueOf(indNew));
					sliderFalses.setProgress(indNew);
					
					try {
						classFeedback.soundInCorrect(media, inputStreamFalse);
					} catch (IOException e) {
						
					}
					
				}
				
				for (Component component : containerButton) {
										
					component.setEnabled(false);
					
					// set bg-color of unused buttons
					if (component.getName() != ("clicked")) {
						
						component.getAllStyles().setBgColor(0x1a1a1a);
						component.getAllStyles().setBgTransparency(255);
						
					} 
													
				}
				
				if (Preferences.get("FirstRunNext",0) == 0) {
					
					formCorrectWords.scrollComponentToVisible(buttonAgain);
					UITimer timerButtons = new UITimer(new Runnable() {
						
						@Override
						public void run() {
							
							if (Preferences.get("FirstRunWordsStart",0) == 0) {
								
								try {
									dialogFirstRunNext();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					});
					timerButtons.schedule(500,false,formCorrectWords);

				}
				
			});
			
		}

		actions(a, label, classPojos2);
	
		styles();
		
		// put everything together 
		
		toolbar.add(BorderLayout.CENTER,scaleImageLabel);
		
		containerNext(containerButtonNext);

		formCorrectWords.setScrollable(false);
		formCorrectWords.add(labelQuery).add(containerButton).add(new Container(new GridLayout(2,1)).add(containerProgress).add(containerButtonNext));

		formCorrectWords.show();
		
		
	}

	public void dialogActions (Form formBack, Label label, ClassPojos2 classPojos2) {
		
		buttonDialogAgain.addActionListener(l-> {
			try {
				Preferences.set("NextString",0); 
				Preferences.set("InCorrectAnswers",0);
				Preferences.set("CorrectAnswers" , 0);
				new ClassCorrectWord().correctWords(formBack, label, classPojos2);
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
				e.printStackTrace();
			}
		});
	}
	
	public void actions (Form a, Label label, ClassPojos2 classPojos2) {
		
		buttonAgain.addActionListener(l -> {
			
			try {		
				changeTransitions(formCorrectWords);
				new ClassCorrectWord().correctWords(a , label, classPojos2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		buttonInformation.addActionListener(l -> {
			
			try {
				
				ArrayList <String> listDialog = inputArrays.listExplanation;
				
				classPojos.setDialogList(listDialog);
				classPojos.setintTotal(Preferences.get("NextString",0));
				classFeedback.dialogExplainFrases(classPojos);

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		buttonNext.addActionListener(l -> {

			int index = Preferences.get("NextString",0);
			int indexNext = index + 1;  
			
			if (indexNext == indexTotal) {
				
				try {
					int corrects = sliderCorrects.getProgress();
					int falses = sliderFalses.getProgress();

					classPojos.setintTotal(indexTotal);
					classPojos.setintCorrects(corrects);
					classPojos.setintFalse(falses);

					ClassFeedback cf = new ClassFeedback();
					cf.dialogFinal(classPojos);
					buttonDialogAgain = cf.getButtonDialogRepeat();
					buttonDialogHome = cf.getButtonDialogHome();
					dialogActions(a , label, classPojos2);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				
				Preferences.set("NextString",indexNext);
				
				intLabelCount = Preferences.get("CwCorrectAnswers",0) + indexCorrects + 1
				+ Preferences.get("FrasesCorrectAnswers",0) + Preferences.get("QuestionsCorrectAnswers",0);
				classPojos2.setintLabelCount(intLabelCount);
				
				try {
					changeTransitions(formCorrectWords);
					new ClassCorrectWord().correctWords(a , label, classPojos2);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
			
		});
		
	}

	public void dialogFirstRunNext () throws IOException {
		// Disable components until dialog is  closed
		for (Component component : containerButtonNext) {
			component.setEnabled(false);
		}
		// Set preference to disable dialog 
		classPojos.setStringPreferences("FirstRunNext");
		// Call first run dialog 
		classFirstRunDialogs.firstRunNext(classPojos, buttonInformation, buttonNext, buttonAgain, containerButtonNext);
		Dialog dialog = classFirstRunDialogs.getDialogNext();
		dialog.showPopupDialog(buttonAgain);	
		
	}
	
	public void dialogFirstRunStart (ClassPojos classPojos) {
		
		UITimer timerTwo = new UITimer(new Runnable() {			
			@Override
			public void run() {	
//				// Disable all buttons until dialog is closed
				for (Component component : containerButton) {
					component.setEnabled(false);
				}
				// Set dialog text and disable preference 
				classPojos.setStringFirstRun("Escolha embaixo um botão que responde a esta pergunta .... ");
				classPojos.setStringPreferences("WordsFirstOne");
				// Call first run dialog
				classFirstRunDialogs.dialogFirstRunStart(classPojos, formCorrectWords);
				// Call avtivation constructor
				classFirstRunDialogs.enableStart(containerButton);
				// Create interaction dialog
				Dialog dialog = classFirstRunDialogs.getDialogStart();
				dialog.showPopupDialog(labelQuery);

				}
			});
			timerTwo.schedule(500,false,formCorrectWords);

		
	}
	
	public void initialLoads() throws IOException {
	
		// Default settings
		Button.setCapsTextDefault(false);
		imageTop = Image.createImage("/germany.jpeg");
		sliderCorrects = new Slider();
		sliderFalses = new Slider();
		
		inputArrays = new ClassInputArrays();
		inputArrays.consolidatedLists();

		// Index loads
		indexCorrects = Preferences.get("CorrectAnswers",0);
		indexIncorrects = Preferences.get("InCorrectAnswers",0);
		indexTotal = inputArrays.listcontrolStrings.size();

		
	}

	public static void changeTransitions(Form formCorrectWords) {
		
//		formCorrectWords.setTransitionInAnimator(CommonTransitions.createCover(CommonTransitions.SLIDE_VERTICAL, true, 300));
//		formCorrectWords.setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, false, 300));        

	    final Transition originalOut = formCorrectWords.getTransitionOutAnimator();
	    final Transition originalIn = formCorrectWords.getTransitionInAnimator();
	    formCorrectWords.setTransitionOutAnimator(CommonTransitions.createEmpty());
	    formCorrectWords.setTransitionInAnimator(CommonTransitions.createEmpty());
	    formCorrectWords.addShowListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	            formCorrectWords.setTransitionOutAnimator(originalOut);
	            formCorrectWords.setTransitionInAnimator(originalIn);
	            formCorrectWords.removeShowListener(this);
	        }
	    });
	}
	
	public void styles() {
		
		Display.getInstance().lockOrientation(true);		
		int intColor = 0x004d00;
		
		Style style = new Style();
		style.setBgColor(intColor);
		style.setFgColor(ColorUtil.WHITE);
		style.setBgTransparency(255);
		
		imageBack = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD_IOS, style);
		imageAgain = FontImage.createMaterial(FontImage.MATERIAL_REPEAT, style);
		imageInfo = FontImage.createMaterial(FontImage.MATERIAL_HELP_OUTLINE, style);
		
		buttonAgain.setIcon(imageAgain);
		buttonNext.setIcon(imageBack);
		buttonNext.setTextPosition(Component.LEFT);
		buttonInformation.setIcon(imageInfo);
		
		buttonAgain.setUIID("ButtonNext");
		buttonNext.setUIID("ButtonNext");
		buttonInformation.setUIID("ButtonNext");	
		
		containerButtonNext.getAllStyles().setMargin(10,50,30,30);
		containerProgress.setUIID("ContainerProgress");
		
		formCorrectWords.getAllStyles().setBgColor(ColorUtil.BLACK);
		formCorrectWords.getAllStyles().setBgTransparency(255);
		
		labelCount.setUIID("LabelCount");
		
		labelQuery.setUIID("LabelQuery");
		labelQuery.setTextUIID("LabelQuery");
		
		// Set margins through display 
		int displayHeight = Display.getInstance().getDisplayHeight();

		labelQuery.getAllStyles().setMargin(displayHeight/18,displayHeight/20,30,30);
		containerProgress.getAllStyles().setMargin(displayHeight/20,10,50,50);
		containerButton.getAllStyles().setMargin(0,0,30,30); 
		
		scaleImageLabel.getAllStyles().setBgImage(imageTop);
		scaleImageLabel.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FIT);
		scaleImageLabel.getAllStyles().setPadding(0, 0, 0, 0);

		sliderCorrects.setUIID("SliderCorrects");
		sliderFalses.setUIID("SliderFalses");
		
		toolbar.getAllStyles().setBorder(Border.createEmpty());	
		toolbar.getAllStyles().setPadding(0, 0, 0, 0);

	}

	public Container getContainerButtons () {
		return containerButton;
	}
	
	public Container containerNext (Container containerButtonNext) {
		
		buttonAgain.remove();
		
		containerButtonNext.add(buttonAgain);
		containerButtonNext.add(labelCount);
		containerButtonNext.add(buttonInformation);
		containerButtonNext.add(buttonNext);

		int indexNext = Preferences.get("NextString",0)+1;
//		indexTotal = inputArrays.listcontrolStrings.size();		
		labelCount.setText(indexNext +"/ " + indexTotal); 
		return containerButtonNext;
	}
	
	public Container getContainer ( Container container) {
		return containerButton;
	}

}

