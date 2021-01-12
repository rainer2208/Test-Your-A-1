package com.torus.A1;

import java.io.IOException;

import com.codename1.components.InteractionDialog;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;

public class ClassFirstRunDialogs {
	
	private Dialog dialogStart = new Dialog() {
    	@Override
        protected void onShow() {
             this.setDisposeWhenPointerOutOfBounds(false);
        }
    };
	private Dialog dialogNext = new Dialog() {
    	@Override
        protected void onShow() {
             this.setDisposeWhenPointerOutOfBounds(false);
        }
    };

	private Button buttonClose;
	private Button buttonDialog;
		
	public void dialogSettings (Button buttonClose, Button buttonDialog, Container containerDialogBase , Label label, Dialog dialog, TextArea popupBody) throws IOException {
		
	buttonDialog.setUIID("DialogButtonFirstRun");
	buttonClose.setUIID("DialogButtonFirstRun");
	buttonClose.getAllStyles().setAlignment(Component.LEFT);	
		
	containerDialogBase.setUIID("DialogContainerBase");
		
	dialog.setLayout(new BorderLayout());
        dialog.setDialogUIID("Popup");
        dialog.setDisposeWhenPointerOutOfBounds(false);
		
	label.getAllStyles().setAlignment(Component.CENTER);
	label.getAllStyles().setPadding(0, 0, 20, 20);
	label.getAllStyles().setMargin(0, 0, 0, 0);
		
	popupBody.setUIID("PopupBody");
        popupBody.setEditable(false);
		
	}
	
	public void dialogFirstRunStart (ClassPojos classPojos, Form form) {
		
		try {
			buttonClose = new Button("fechar");
			buttonDialog = new Button("não mostre +");

			Container containerDialogBase = new Container(new BorderLayout());
			
			Label label = new Label();
			TextArea popupBody = new TextArea(4,12);
			
			// Dialog settings
			dialogSettings (buttonClose,buttonDialog, containerDialogBase, label, dialogStart, popupBody);
			
			if (classPojos.getStringFirstRun().equals("first run start screen")) {
				
			Image imgageBackground = Image.createImage("/Botton_Arrow.png");
			popupBody.setText("Nesta tela tem quatro botões, cada qual para um desafio .... clique para testar!");

			containerDialogBase.add(BorderLayout.CENTER, popupBody);
		        containerDialogBase.add(BorderLayout.SOUTH,label);
		        containerDialogBase.add(BorderLayout.NORTH,new Container(new BorderLayout()).add(BorderLayout.EAST,buttonClose).add(BorderLayout.WEST,buttonDialog));
				
		        label.setIcon(imgageBackground);
		        
			} else if (classPojos.getStringFirstRun().equals("first run side menu")) {
				
				Image imgageBackground = Image.createImage("/Botton_Arrow_Up.png");
				popupBody.setText("Neste menu lateral tem várias opções de personalização e para entar em contato conosco!");

				containerDialogBase.add(BorderLayout.CENTER, popupBody);
		        	containerDialogBase.add(BorderLayout.NORTH,label);
		        	containerDialogBase.add(BorderLayout.SOUTH,new Container(new BorderLayout()).add(BorderLayout.EAST,buttonClose).add(BorderLayout.WEST,buttonDialog));
				
		        	label.setIcon(imgageBackground);
		       	 	label.getAllStyles().setAlignment(Component.LEFT);
				
				
			} else {
				
				Image imgageBackground = Image.createImage("/Botton_Arrow_Up.png");					
				popupBody.setText(classPojos.getStringFirstRun());
				popupBody.setRows(4);
		        	containerDialogBase.add(BorderLayout.CENTER, popupBody);
		       	 	containerDialogBase.add(BorderLayout.NORTH,label);
		        	containerDialogBase.add(BorderLayout.SOUTH,new Container(new BorderLayout()).add(BorderLayout.EAST,buttonClose).add(BorderLayout.WEST,buttonDialog));
		        
		        	label.setIcon(imgageBackground);

			}
	        
	        dialogStart.add(BorderLayout.CENTER, containerDialogBase);
	        dialogStart.setDisposeWhenPointerOutOfBounds(false);
	        
	        buttonClose.addActionListener(l -> {   	
	        	form.setScrollableY(true);
	        	dialogStart.dispose();	        	
	        });
	        
	        buttonDialog.addActionListener(l -> {
	        	String stringPrefences = classPojos.getStringPreferences();
	        	Preferences.set(stringPrefences,1);
	        	form.setScrollableY(true);
	        	dialogStart.dispose();
	        });
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void firstRunNext (ClassPojos classPojos, Button buttonInformation, Button buttonNext, Button buttonAgain, Container containerButtonNext) throws IOException {
		
		buttonClose = new Button("fechar");
		buttonDialog = new Button("okay");

		Container containerDialogBase = new Container(new BorderLayout());
		Container containerDialog =  new Container(new BorderLayout());
		Image imgageBackground = Image.createImage("/Botton_Arrow.png");
		Label label = new Label();
		String stringLevel = classPojos.getStringLevel();
		TextArea popupBody = new TextArea("Aperte este botão à esquerda para fazer a questão novamente.... !", 4, 10);
		
		// Dialog settings
		dialogSettings (buttonClose,buttonDialog, containerDialogBase, label, dialogNext, popupBody);
		label.getAllStyles().setAlignment(Component.LEFT);
		label.setIcon(imgageBackground);

		// Put everything together 
		containerDialog.add(BorderLayout.EAST,buttonDialog);
		
        	containerDialogBase.add(BorderLayout.CENTER, popupBody);
        	containerDialogBase.add(BorderLayout.SOUTH,label);
       		containerDialogBase.add(BorderLayout.NORTH,containerDialog);
        
        	dialogNext.add(BorderLayout.CENTER, containerDialogBase);
       
        
        // Button action
       
        buttonClose.addActionListener(l -> {
        	for (Component component : containerButtonNext) {
        		component.setEnabled(true);
		}
        	
        	dialogNext.dispose();
        });
        
        buttonDialog.addActionListener(l -> {
        	try {
	           String string = classPojos.getStringLevel();		   
	 		   if (string.equals("")) {		
	 			    classPojos.setStringLevel("A");			
	 		   } else if (string.equals("A")) {			   
	 			   classPojos.setStringLevel("B");			   
	 		   } else if (string.equals("B")) {			   
	 			   classPojos.setStringLevel("C");			
	 		   } 
	 		   dialogNext.dispose(); 		   
	 		   firstRunNext(classPojos, buttonInformation, buttonNext, buttonAgain, containerButtonNext);
		} catch (IOException e) {
			e.printStackTrace();
		}        	
        });
        
        switch(stringLevel) {
        case "A" : 
        	label.getAllStyles().setAlignment(Component.CENTER);
     	    	popupBody.setText("...com este botão pode consultar a explicação das questões..");
     	    	dialogNext.showPopupDialog(buttonInformation);
            	break;
        case "B" :
        	label.getAllStyles().setAlignment(Component.RIGHT);
        	buttonDialog.setText("não mostre +");
     	    	popupBody.setText("...e o botão da direita leva você para a próxima questão.");
     	    	containerDialog.add(BorderLayout.EAST,buttonClose);
     	    	containerDialog.add(BorderLayout.WEST,buttonDialog);
            	dialogNext.showPopupDialog(buttonNext); 
            	if (classPojos.getStringFirstRun().equals("imported container")) {
			enableNext();
		}
            enableNextWord(containerButtonNext);
            for (Component component : containerButtonNext) {
        	component.setEnabled(true);
		}
            break;
        case "C":
            dialogNext.dispose();
            Preferences.set(classPojos.getStringPreferences(),1);           
            break;
 	   }		
	}
	
	public void firstRunOK (ClassFrases classFrases , ClassTextQuestions classTextQuestions , ClassPojos classPojos , Button buttonOk , Form form) { 
		
		try {
			
			buttonClose = new Button("fechar");
			buttonDialog = new Button("não mostre +");

			Container containerDialogBase = new Container(new BorderLayout());
			
			Label label = new Label();
			TextArea popupBody = new TextArea("Confirme agora sua escolha com este botão .... ", 4,12);
			
			// Dialog settings
			dialogSettings (buttonClose,buttonDialog, containerDialogBase, label, dialogStart, popupBody);
			
			Image imgageBackground = Image.createImage("/Botton_Arrow.png");
			popupBody.setText("Confirme agora sua escolha com este botão .... ");
			popupBody.setColumns(10);
			popupBody.setRows(3);
			
			containerDialogBase.add(BorderLayout.CENTER, popupBody);
	        	containerDialogBase.add(BorderLayout.SOUTH,label);
	        	containerDialogBase.add(BorderLayout.NORTH,new Container(new BorderLayout()).add(BorderLayout.EAST,buttonClose).add(BorderLayout.WEST,buttonDialog));
			
	        	label.setIcon(imgageBackground);
	        	label.getAllStyles().setAlignment(Component.LEFT);
	        
	        	dialogStart.add(BorderLayout.CENTER, containerDialogBase);
	        	dialogStart.setDisposeWhenPointerOutOfBounds(false);

	        	buttonClose.addActionListener(l -> {   	
	        		buttonOk.setEnabled(true);
	        		form.setScrollableY(true);
	        		dialogStart.dispose();	        	
	       		 });
	        
	        buttonDialog.addActionListener(l -> {
	        	
	        	buttonOk.setEnabled(true);
	        	
	        	String stringPrefences = classPojos.getStringPreferences();
	        	Preferences.set(stringPrefences,1);
	        	
	        	form.setScrollableY(true);
	        	
	        	dialogStart.dispose();
	        });
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void enableStart (Container containerButton) { 
		
		buttonClose.addActionListener(l->{
			
			for (Component component : containerButton) {
				component.setEnabled(true);
			}
		});
		
		buttonDialog.addActionListener(l->{
			
			for (Component component : containerButton) {
				component.setEnabled(true);
			}
		});
		
	}
	
	public void enableNext () {
		
		buttonDialog.addActionListener(l->{
			
			for (Component component : ClassButtonContainer.containerButtonNext) {
					component.setEnabled(true);
				}
			});
		
		buttonClose.addActionListener(l->{
			
			for (Component component : ClassButtonContainer.containerButtonNext) {
					component.setEnabled(true);
				}
			});
		
	}
	
	public void enableNextWord (Container containerButtonNext) {
		
		buttonDialog.addActionListener(l->{
			
			for (Component component : containerButtonNext ) {
					component.setEnabled(true);
				}
			});
		
		buttonClose.addActionListener(l->{
			
			for (Component component : containerButtonNext) {
					component.setEnabled(true);
				}
			});
		
	}
	
	public Dialog getDialogNext ( ) {
		return dialogNext;
	}
	
	public Dialog getDialogStart ( ) {
		return dialogStart;
	}

}
