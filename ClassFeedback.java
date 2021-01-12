package com.torus.A1;

import java.io.IOException;
import java.io.InputStream;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.MultipleCategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.DoughnutChart;
import com.codename1.charts.views.PieChart;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;

class ClassFeedback {
	
	public Button buttonDialog = new Button("repetir");
	public Button buttonHome = new Button("início");
	public Button buttonExplain = new Button("OKAY");
	private Container containerDialog = new Container(new BorderLayout());
	private Container containerGraph = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	private Dialog dialogFinal = new Dialog(new BorderLayout());
	private Dialog dialogExplain = new Dialog(new BorderLayout());
	private ClassInputArrays inputArrays = new ClassInputArrays();
	private SpanLabel spanLabelDialogFeedback = new SpanLabel();
	private Image imageLabel;
	private int index;

	
	public void dialogExplainFrases (ClassPojos cp) throws IOException {
		
		commons();
		
		index = cp.getintTotal();
		spanLabelDialogFeedback.setText("    EXPLICAÇÂO\n\n" + cp.getDialogList().get(index));
		
		buttonExplain.addActionListener(l -> dialogExplain.dispose());
		
		// Put everything together
		dialogExplain.add(BorderLayout.CENTER, containerDialog);
		containerDialog.add(BorderLayout.CENTER, spanLabelDialogFeedback);
		containerDialog.add(BorderLayout.SOUTH, buttonExplain);
		
		dialogExplain.setDisposeWhenPointerOutOfBounds(true);
		
		dialogExplain.show();
		
	}
	
	
	
	public void dialogFinal (ClassPojos cp) throws IOException {
		
		buttonDialog = new Button("repetir");
		buttonHome = new Button("início");
		
		commons();

		spanLabelDialogFeedback.setText("   PARABÉNS!\n\nVocê terminou esta fase do jogo. Abaixo tem o resumo da sua "
				+ "pontuação, em %.\nVocê pode recomeçar esta etapa, ou ir para a tela inicial");
		
		dialogFinal.add(BorderLayout.NORTH, containerDialog);
		dialogFinal.add(BorderLayout.CENTER, containerGraph);
		dialogFinal.add(BorderLayout.NORTH, spanLabelDialogFeedback);
		dialogFinal.add(BorderLayout.SOUTH, new Container(new GridLayout(2)).add(buttonHome).add(buttonDialog));

		createPieChartForm(cp);

		int intHeight = Display.getInstance().getDisplayHeight();
		
		dialogFinal.setDisposeWhenPointerOutOfBounds(false);
		dialogFinal.getAllStyles().setPadding(intHeight/20,intHeight/7,0,0);
		containerGraph.getAllStyles().setPadding(30,0,0,0);
		
		dialogFinal.showModeless();
		
	}
	
	protected void soundCorrect(Media media, InputStream inputStreamCorrect ) throws IOException {
		
		inputStreamCorrect = Display.getInstance().getResourceAsStream(getClass(), "/" + "children-hooray.wav");
		media = MediaManager.createMedia(inputStreamCorrect, "audio/wav");
		media.play();
	}
	
	protected void soundInCorrect(Media media, InputStream inputStreamInCorrect ) throws IOException {
		
		inputStreamInCorrect = Display.getInstance().getResourceAsStream(getClass(), "/" + "wrong.wav");
		media = MediaManager.createMedia(inputStreamInCorrect, "audio/wav");
		media.play();
	}
	
	protected void soundButton(Media media, InputStream inputStreamInButton ) throws IOException {
		
		inputStreamInButton = Display.getInstance().getResourceAsStream(getClass(), "/" + "button_click.wav");
		media = MediaManager.createMedia(inputStreamInButton, "audio/wav");
		media.play();
	}
	
	
	public void commons() throws IOException {
		
		Dialog.setDefaultBlurBackgroundRadius(8);
		imageLabel = Image.createImage("/icon.png");
		inputArrays.consolidatedLists();
		
		buttonDialog.remove();
		containerDialog.remove();
		containerGraph.remove();
		spanLabelDialogFeedback.remove(); 
		
		buttonExplain.setUIID("DialogButton");
		buttonDialog.setUIID("DialogButtonFinal");
		buttonHome.setUIID("DialogButtonFinal");
		containerDialog.setUIID("DialogContainer");
		
		dialogExplain.setDialogUIID("DialogExplain");
		dialogFinal.setDialogUIID("DialogExplain");
		dialogFinal.getDialogComponent().getAllStyles().setBgColor(0x404040);
		dialogFinal.getDialogComponent().getAllStyles().setBgTransparency(255);
		dialogFinal.getDialogComponent().getAllStyles().setMargin(Component.LEFT, 50);
		dialogFinal.getDialogComponent().getAllStyles().setMargin(Component.RIGHT, 50);
		dialogFinal.setDisposeWhenPointerOutOfBounds(true);
		
		spanLabelDialogFeedback.setUIID("DialogSpanLabel");
		spanLabelDialogFeedback.setTextUIID("DialogSpanLabelText");
		spanLabelDialogFeedback.setIcon(imageLabel);
		
	}	
	
	public void createPieChartForm(ClassPojos cp) {
		
		int cor = cp.getintCorrects();
		int fal = cp.getintFalse();
		int total = cp.getintTotal();
			
		int porcCor = cor* 100/ total ;
		int porcFal = fal *100 / total;
		
		
        // Generate the values
        double[] values = new double[]{porcFal, porcCor};

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, 0x009933};
        
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
       
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
        

        // Create the chart ... pass the values and renderer to the chart object.

        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
        
        containerGraph.add(c);

	 }
	 
	protected CategorySeries buildCategoryDataset(String title, double[] values) {
	        CategorySeries series = new CategorySeries(title);
	        
	        String[] strings = new String[]{"Errado %", "Certo %"};
	       
	        for (int i = 0 ; i != values.length ; i++) {
	            series.add(strings[i], values[i]);
	        }
	        
			return series;
	        
	}
	 
	private DefaultRenderer buildCategoryRenderer(int[] colors) {
	        DefaultRenderer renderer = new DefaultRenderer();
	        renderer.setLabelsTextSize(40);
	        renderer.setShowLegend(false);
	        for (int color : colors) {
	            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
	            r.setColor(color);
	            renderer.addSeriesRenderer(r);
	            
	        }
	        return renderer;
	 }

	public Button getButtonDialogRepeat (){
		return buttonDialog;
	}	
	
	public Button getButtonDialogHome (){
		return buttonHome;
	}
}
