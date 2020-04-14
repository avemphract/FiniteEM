package com.katafrakt.fem.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.katafrakt.fem.models.core.Model;
import com.katafrakt.fem.system.Node;
import com.katafrakt.framework.util.InputHandler;

@SuppressWarnings("serial")
public class ViewModel extends JPanel{

	public static int height;
	public static int width;
	public static double scale;
	public static int frame=40;
	
	
	private InputHandler inputHandler;
	
	public ViewModel(int width,int height ) {
		ViewModel.width=width;
		ViewModel.height=height;
		setPreferredSize(new Dimension(width,height));
		setBackground(Color.white);
		setFocusable(true);
		requestFocus();
		setScale();
		

	}
	public static void setScale(){
		double scaleX=(width-frame*2)/Node.getMaxX(Model.currentModel);
		double scaleY=(height-frame*2)/Node.getMaxY(Model.currentModel);
		if(scaleX>scaleY)
			scale=scaleY*0.95d;
		else
			scale=scaleX*0.95d;
	}
	
	private void initInput(){
		inputHandler=new InputHandler();
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);
	}
	

	@Override
	public void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		arg0.setColor(Color.LIGHT_GRAY);
		arg0.fillRect(0, 0, width, height);
		
		Model.currentModel.render(arg0);
		FloatingPanel.render(arg0);
	}
	
}
