package com.katafrakt.fem.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.katafrakt.fem.system.Node;

public class FloatingPanel {
	public static ArrayList<FloatingPanel> panelList;
	
	private int x,y;
	private Node node;

	public FloatingPanel(int x,int y,Node node) {
		this.x=x;
		this.y=y;
		this.node=node;
		panelList.add(this);
	}
	public void renderPanel(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 96, 32);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 96, 32);
		writeString("Node #: "+Integer.toString(node.index),0,g);
		writeString("X: "+Double.toString(node.x)+"   Y: "+Double.toString(node.y),1,g);
		
	}
	public void removePanel(){
		panelList.remove(this);
	}
	
	public static void render(Graphics g){
		for(FloatingPanel panel:panelList)
			panel.renderPanel(g);
	}
	public void writeString(String str,int coulumb,Graphics g){
		g.drawString(str, x+3, y+coulumb*13+13);
	}
}
