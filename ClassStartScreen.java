package com.torus.A1;

import java.io.IOException;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.messaging.Message;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UITimer;

public class ClassStartScreen {
	
	private Button buttonCorrectWord = new Button();
	private Button buttonImage = new Button();
	private Button buttonTextQuestions = new Button();
	private Button buttonFour = new Button();
	private Button buttonFrases = new Button();
	public Command back;
	public Container containerButton = new Container(new FlowLayout());
	private Container containerDummy = new Container();
	public Form formStart = new Form(new BorderLayout());
	private Image imageFlag;
	private Image imageFlagPressed;
	private Label labelCount = new Label(); 
	private Toolbar toolbar = formStart.getToolbar();
	

	public void startScreen(ClassPojos classPojos , ClassPojos2 classPojos2 ) throws IOException {
		
		firstRunDialog(classPojos);
		firstRunDialogSideMenu(classPojos);
		preferences();
		sideMenu();

		imageFlag = FontImage.createImage("/flag.png");
		imageFlagPressed = FontImage.createImage("/flagPressed.png");
		
		((BorderLayout)formStart.getLayout()).setCenterBehavior(BorderLayout.CENTER_BEHAVIOR_CENTER);
		containerButton.add(buttonCorrectWord).add(buttonFrases).add(buttonTextQuestions).add(buttonFour);

		labelCount.setText(classPojos2.getintLabelCount() + " /95");
		
		// Put everything together 
		formStart.add(BorderLayout.NORTH, containerDummy);
		formStart.add(BorderLayout.NORTH, FlowLayout.encloseCenterBottom(buttonImage));
		formStart.add(BorderLayout.CENTER, containerButton);
		formStart.add(BorderLayout.SOUTH, labelCount);
		
		// Button actions
		buttonCorrectWord.addActionListener( l -> {
			try {
				new ClassCorrectWord().correctWords(formStart, labelCount, classPojos2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		buttonFrases.addActionListener( l -> {
			
			try {
				new ClassFrases(formStart, classPojos2,labelCount);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		buttonTextQuestions.addActionListener( l -> {
			
			try {
				new ClassTextQuestions(formStart, classPojos2, labelCount);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		});
		
		
		buttonFour.addActionListener( l -> {
						
			try {
				new ClassCrossWords(formStart, classPojos2, labelCount);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
		});
		
		styles();
		formStart.show(); 
		
	}
	
	public void firstRunDialog (ClassPojos classPojos) {
		
		if (Preferences.get("FirstRunStart",0) == 0) {
			
			UITimer timerTest = new UITimer (new Runnable() {
				@Override
				public void run() {
					
					classPojos.setStringFirstRun("first run start screen");
					classPojos.setStringPreferences("FirstRunStart");
					
					ClassFirstRunDialogs cfd = new ClassFirstRunDialogs();
					cfd.dialogFirstRunStart(classPojos, formStart);
					cfd.enableStart(containerButton);
					
					Dialog dialog = cfd.getDialogStart();
					dialog.showPopupDialog(containerButton);
					
				}
			});
			timerTest.schedule(1000,false,formStart);
		
		}
		
	}
	
	public void firstRunDialogSideMenu (ClassPojos classPojos) {
		
		if (Preferences.get("FirstRunStartSideMenu",0) == 0) {
			
			UITimer timerTest = new UITimer (new Runnable() {
				@Override
				public void run() {
					
					classPojos.setStringFirstRun("first run side menu");
					classPojos.setStringPreferences("FirstRunStartSideMenu");
					
					ClassFirstRunDialogs cfd = new ClassFirstRunDialogs();
					cfd.dialogFirstRunStart(classPojos, formStart);
					
					Dialog dialog = cfd.getDialogStart();
					dialog.showPopupDialog(containerDummy);
					
				}
			});
			timerTest.schedule(1500,false,formStart);
		
		}
		
	}
	
	
	
	public void preferences () {
		
//		Preferences.clearAll();
//		Preferences.set("NextString",23);
		Preferences.getAndSet("FirstRunWords",0);
		Preferences.getAndSet("FirstRunFrases",0);
		Preferences.getAndSet("FirstRunQuestions",0);
		Preferences.getAndSet("FirstRunStart",0);
		Preferences.getAndSet("FirstRunStartSideMenu",0);
		
		//Next for all
		Preferences.getAndSet("FirstRunNext",0);
		
		//ClassCrossWords
		Preferences.getAndSet("CwCorrectAnswers",0);
		Preferences.getAndSet("CwFalseAnswers",0);
		Preferences.getAndSet("CwIndex",0);
		Preferences.getAndSet("CwFirstRunOk",0);
		Preferences.getAndSet("CwFirstRunStart",0);
		
		// ClassWords
		Preferences.getAndSet("WordsFirstOne",0);
		Preferences.getAndSet("FirstRunWordsStart",0);
		Preferences.getAndSet("NextString",0);
		Preferences.getAndSet("CorrectAnswers",0);
		Preferences.getAndSet("InCorrectAnswers",0);
		
		// ClassFrases
		Preferences.getAndSet("FirstRunFrasesRadio",0);
		Preferences.getAndSet("FirstRunFrasesStart",0);
		Preferences.getAndSet("IndexFrases",0);
		Preferences.getAndSet("FrasesCorrectAnswers",0);
		Preferences.getAndSet("FrasesFalseAnswers",0);
		
		// ClassTextQuestions
		Preferences.getAndSet("IndexQuestions",0);
		Preferences.getAndSet("FirstRunQuestions",0);
		Preferences.getAndSet("FirstRunButtonsQuestions",0);
		Preferences.getAndSet("QuestionsCorrectAnswers",0);
		Preferences.getAndSet("QuestionsFalseAnswers",0);
		
	}
	
	public void preferencesReset() {
		
		//ClassCrossWords
		Preferences.set("CwCorrectAnswers",0);
		Preferences.set("CwFalseAnswers",0);
		Preferences.set("CwIndex",0);
		
		// ClassWords
		Preferences.set("NextString",0);
		Preferences.set("CorrectAnswers",0);
		Preferences.set("InCorrectAnswers",0);
		
		// ClassFrases
		Preferences.set("IndexFrases",0);
		Preferences.set("FrasesCorrectAnswers",0);
		Preferences.set("FrasesFalseAnswers",0);
		
		// ClassTectQuestions
		Preferences.set("IndexQuestions",0);
		Preferences.set("QuestionsCorrectAnswers",0);
		Preferences.set("QuestionsFalseAnswers",0);
		
		
		
	}
	
	public void preferencesResetFirstRuns () {
		
		Preferences.set("FirstRunWords",0);
		Preferences.set("FirstRunFrases",0);
		Preferences.set("FirstRunQuestions",0);
		Preferences.set("FirstRunStart",0);
		Preferences.set("FirstRunStartSideMenu",0);
		
		//Next for all
		Preferences.set("FirstRunNext",0);
		
		//ClassCrossWords
		Preferences.set("CwFirstRunOk",0);
		Preferences.set("CwFirstRunStart",0);
		
		// ClassWords
		Preferences.set("WordsFirstOne",0);
		Preferences.set("FirstRunWordsStart",0);
		
		// ClassFrases
		Preferences.set("FirstRunFrasesRadio",0);
		Preferences.set("FirstRunFrasesStart",0);
		
		// ClassTextQuestions
		Preferences.set("FirstRunQuestions",0);
		Preferences.set("FirstRunButtonsQuestions",0);
		
	}
	
	public void sideMenu () {
		
		Style style = new Style();
		style.setFgColor(0xb30000);
		style.setBgColor(0x1a1a1a);
		style.setBgTransparency(255);
		
		Button buttonSideMenu = new Button("  Zerar meus pontos");
		buttonSideMenu.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE_SWEEP, style,4));
		buttonSideMenu.setUIID("SideMenuButton");
		buttonSideMenu.addActionListener(l-> {

			Container containerDialogBase = new Container(new BorderLayout());
			Button buttonClose = new Button("NÃO");
			Button buttonDialog = new Button("SIM");
			Dialog dialog = new Dialog() {
				@Override
		        protected void onShow() {
		             this.setDisposeWhenPointerOutOfBounds(false);
		        }
			};
			TextArea textArea = new TextArea(4,15);

			containerDialogBase.setUIID("PopupBody");
			dialog.setDialogUIID("Popup");
			textArea.setUIID("SideMenuTextField");	
			textArea.setEditable(false);
			buttonDialog.setUIID("DialogButtonFirstRun");
			buttonClose.setUIID("DialogButtonFirstRun");
			buttonClose.getAllStyles().setAlignment(Component.LEFT);
			
			textArea.setText("                 ATENÇÂO\n\nEssa ação vai zerar todos os pontos que você conseguiu nas questões."
					+ "\n\nQuer continuar?");
			
			containerDialogBase.add(BorderLayout.CENTER, textArea);
	        containerDialogBase.add(BorderLayout.SOUTH,new Container(new BorderLayout()).add(BorderLayout.EAST,buttonClose).add(BorderLayout.WEST,buttonDialog));
			
	        buttonClose.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					dialog.dispose();					
				}  
		    });  
	        
	        buttonDialog.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					preferencesReset();
					labelCount.setText("0/ 95");
					dialog.dispose();		
					
				}  
		    });  

	        dialog.add(containerDialogBase);
			dialog.showPopupDialog(buttonSideMenu);
		});
		
		toolbar.addComponentToSideMenu(buttonSideMenu); 
		toolbar.addComponentToSideMenu(new Label(" "));
		toolbar.addMaterialCommandToSideMenu("  Habiltar tutorial", FontImage.MATERIAL_DASHBOARD, e -> {
			preferencesResetFirstRuns();
			new ClassStartScreen();
		});
		toolbar.addMaterialCommandToSideMenu("  Quero aulas de Alemão", FontImage.MATERIAL_EMAIL, e -> {
			Message m = new Message("Olá, Torus, tenho interesse em aulas de alemão ....");
            Display.getInstance().sendMessage(new String[] {"torus_projects@outlook.com"}, "Aulas de alemão", m);
		});
		toolbar.addMaterialCommandToSideMenu("  Quero um aplicativo", FontImage.MATERIAL_EMAIL, e -> {
			Message m = new Message("Olá, Torus, tenho interesse em um aplicativo ....");
            Display.getInstance().sendMessage(new String[] {"torus_projects@outlook.com"}, "Aplicativo", m);
		});
		toolbar.addMaterialCommandToSideMenu("  Tenho uma sugestões", FontImage.MATERIAL_MAIL, e -> {
			Message m = new Message("Olá, Torus, tenho uma sugestão ....");
            Display.getInstance().sendMessage(new String[] {"torus_projects@outlook.com"}, "Sugestão", m);
		});
		
		SpanLabel spanLabel = new SpanLabel("Somos uma equipe pequena que se dedica ao desenvolvimento de "
				+ "aplicativos didáticos.\nSe perceber alguma falha, por favor, entre em contato conosco."
				+ "\n\nSugestões para novos  desafios também são muito bem-vindas."
				+ "\n\nTem interesse em alulas de alemão? Contate-nos pelo e-mail."
				+ "\n\nGostou do aplicativo e quer um para a sua instituição? Solicite informções pelo e-mail (botões em cima.)");
		spanLabel.setUIID("SideMenuSpanLabel");
		spanLabel.setTextUIID("SideMenuSpanLabelText");
		spanLabel.setScrollableY(true);
		toolbar.addComponentToSideMenu(spanLabel);
	}
	
	public void styles() {
		
		Style styles = new Style();
		styles.setFgColor(ColorUtil.WHITE);
		styles.setBgColor(ColorUtil.BLACK);
		styles.setBgTransparency(255);
		
		Button.setCapsTextDefault(false);
		Display.getInstance().lockOrientation(true);
		
		buttonImage.setIcon(imageFlag);
		buttonImage.setPressedIcon(imageFlagPressed);
		buttonImage.getPressedStyle().setBgTransparency(0);
		buttonImage.getAllStyles().setBgTransparency(0);
		
		buttonCorrectWord.setUIID("StartScreenButton");
		buttonFrases.setUIID("StartScreenButton");
		buttonTextQuestions.setUIID("StartScreenButton");
		buttonFour.setUIID("StartScreenButton");
		
		buttonCorrectWord.setIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_FLORIST, styles, 4));
		buttonFrases.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ECO, styles, 4));
		buttonTextQuestions.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SPA, styles, 4));
		buttonFour.setIcon(FontImage.createMaterial(FontImage.MATERIAL_EMOJI_NATURE, styles, 4));
		
		buttonCorrectWord.getPressedStyle().setBgColor(ColorUtil.GREEN);
		buttonFrases.getPressedStyle().setBgColor(ColorUtil.YELLOW);
		buttonTextQuestions.getPressedStyle().setBgColor(ColorUtil.MAGENTA);
		buttonFour.getPressedStyle().setBgColor(ColorUtil.CYAN);
		
		buttonCorrectWord.getPressedStyle().setBgTransparency(100);
		buttonFrases.getPressedStyle().setBgTransparency(100);
		buttonTextQuestions.getPressedStyle().setBgTransparency(100);
		buttonFour.getPressedStyle().setBgTransparency(100);
		
		labelCount.setUIID("StartScreenLabelCount");
		
		Style styleWord = new Style();
		styleWord.setFgColor(ColorUtil.WHITE);
		styleWord.setBgColor(ColorUtil.GREEN);
		styleWord.setBgTransparency(10);
		buttonCorrectWord.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_FLORIST, styleWord, 5));
		
		Style styleFour = new Style();
		styleFour.setFgColor(ColorUtil.WHITE);
		styleFour.setBgColor(ColorUtil.YELLOW);
		styleFour.setBgTransparency(10);
		buttonFrases.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_ECO, styleFour, 5));
		
		Style styleMatch = new Style();
		styleMatch.setFgColor(ColorUtil.WHITE);
		styleMatch.setBgColor(ColorUtil.YELLOW);
		styleMatch.setBgTransparency(10);		
		buttonTextQuestions.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_SPA, styleMatch, 5));
		
		Style styleThree = new Style();
		styleThree.setFgColor(ColorUtil.WHITE);
		styleThree.setBgColor(ColorUtil.YELLOW);
		styleThree.setBgTransparency(10);	
		buttonFour.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_EMOJI_NATURE, styleThree, 5));
		
		containerButton.setUIID("StartScreenContainerButton");
	
		int intDisplayHeight = Display.getInstance().getDisplayHeight();
//		formStart.getAllStyles().setPadding(intDisplayHeight/5,intDisplayHeight/8,0,0);
		toolbar.getAllStyles().setMargin(10, intDisplayHeight/8,0,0);
		formStart.getAllStyles().setPadding(0,intDisplayHeight/8,0,0);
		formStart.getAllStyles().setBgColor(ColorUtil.BLACK);
		formStart.getAllStyles().setBgTransparency(255);
		
		toolbar.getAllStyles().setBorder(Border.createEmpty());	
		toolbar.getAllStyles().setPadding(0, 0, 0, 0);
	
	}
	
	public Label getLabelCount () {
		return labelCount;
	}
	
	
	
}
