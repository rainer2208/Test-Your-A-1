package com.torus.A1;

import java.io.IOException;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.Transition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

public class ClassButtonContainer {

	public static Button buttonAgain = new Button();
	public static Button buttonInformation;
	public static Button buttonNext = new Button();
	public static Button buttonOk = new Button();
	private static Container containerBase = new Container() {
		@Override
	    public void drop(Component dragged, int x, int y) {
	        super.drop(dragged, x, y); 
	        setUIID("Container");
	    }
	};
	private static Container containerBaseProgress;
	public static Container containerButtonNext;
	private static Container containerProgress;
	private static FontImage imageBack;
	private static FontImage imageAgain;
	private static FontImage imageInfo;
	private static Label labelCount = new Label();
	private static Slider sliderCorrects = new Slider();
	private static Slider sliderFalses = new Slider();

	public static Container containerMenu() {
		
		

		componentSettings();

		containerButtonNext.add(buttonAgain).add(labelCount).add(buttonInformation).add(buttonNext);
		containerProgress.add(sliderCorrects).add(sliderFalses);
		containerBaseProgress.add(buttonOk).add(containerProgress);
		containerBase.add(containerBaseProgress).add(containerButtonNext);

		actions();
		styles();

		return containerBase;
	}

	public static void actions() {

		buttonOk.addActionListener(l -> {
			buttonAgain.setEnabled(true);
			buttonNext.setEnabled(true);
			buttonInformation.setEnabled(true);
		});

	}

	public static void styles() {

		int intColor = 0x004d00;
		int height = Display.getInstance().getDisplayHeight();

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
		buttonOk.setUIID("ButtonNextOK");
		buttonAgain.getAllStyles().setPadding(height / 65, height / 65, 5, 5);
		buttonNext.getAllStyles().setPadding(height / 65, height / 65, 5, 5);
		buttonInformation.getAllStyles().setPadding(height / 65, height / 65, 5, 5);
		buttonOk.getAllStyles().setPadding(height / 55, height / 55, 60, 60);

		buttonAgain.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonInformation.setEnabled(false);
		buttonOk.setEnabled(false);

		containerBaseProgress.getAllStyles().setMargin(0, height / 75, 0, 0);
		containerProgress.setUIID("ContainerProgress");
		containerBase.getAllStyles().setPadding(0, height / 65, height / 100, 20);
		containerProgress.getAllStyles().setMargin(5, 10, 10, 10);

		labelCount.setUIID("LabelCount");
		labelCount.getAllStyles().setPadding(height / 65, height / 65, 5, 5);

		sliderCorrects.setUIID("SliderCorrects");
		sliderFalses.setUIID("SliderFalses");

		sliderCorrects.getAllStyles().setPadding(25, 25, 12, 12);
		sliderFalses.getAllStyles().setPadding(25, 25, 12, 12);

	}

	private static void componentSettings() {

		buttonAgain = new Button();
		buttonInformation = new Button();
		buttonNext = new Button();
		buttonOk = new Button("OK?");
		containerBase = new Container(new GridLayout(2, 1));
		containerBaseProgress = new Container(new BoxLayout(BoxLayout.X_AXIS));
		containerButtonNext = new Container(new GridLayout(1, 4));
		containerProgress = new Container(new GridLayout(1, 2)) {
			@Override
		    public void drop(Component dragged, int x, int y) {
		        super.drop(dragged, x, y); 
		        setUIID("Container");
		    }
		};
		sliderCorrects = new Slider();
		sliderFalses = new Slider();

		int indexCorrects = Preferences.get("CorrectAnswers", 0);
		int indexIncorrects = Preferences.get("InCorrectAnswers", 0);
		int indexTotal = new ClassInputArrays().listcontrolStrings.size();

		labelCount = new Label("James");

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

	}

	public static Button getButtonAgain() {
		return buttonAgain;
	}

	public static Button getButtonInfromation() {
		return buttonInformation;
	}

	public static Button getButtonNext() {
		return buttonNext;
	}

	public static Button getButtonOk() {
		return buttonOk;
	}
	
	public static Container getContainerNext () {
		return containerBase;	
	}

	public static Label getLabelCount() {
		return labelCount;
	}

	public static Slider getSliderCorrect() {
		return sliderCorrects;
	}

	public static Slider geSliderFalse() {
		return sliderFalses;
	}

}
