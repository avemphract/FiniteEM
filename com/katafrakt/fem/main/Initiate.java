package com.katafrakt.fem.main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.katafrakt.fem.models.FourElementsRod;
import com.katafrakt.fem.models.Question2;
import com.katafrakt.fem.models.SixteenElementsRod;
import com.katafrakt.fem.models.core.Model;

public class Initiate {
	
	public final static int height=800;
	public final static int width=1200;
	
	public static Model fourmodel;
	public static Model sixteenmodel;
	public static Model question2;
	
	public static ViewModel main;
	public static DetailsPanel details;
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Emre Çatalkaya");
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		FloatingPanel.panelList=new ArrayList<FloatingPanel>();
		
		fourmodel=new FourElementsRod();
		sixteenmodel=new SixteenElementsRod();
		question2=new Question2();
		
		main = new ViewModel(800, height);
		frame.add(main,BorderLayout.WEST);
		
		details=new DetailsPanel(400,height);
		frame.add(details);
		
		frame.setVisible(true);
	}
	

}
