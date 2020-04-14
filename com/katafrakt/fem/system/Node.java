package com.katafrakt.fem.system;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.katafrakt.fem.main.ViewModel;
import com.katafrakt.fem.models.core.Model;

public class Node {
	
	public static int nodeSize=20;
	public double x,y;
	public double dX=0,dY=0;
	public int index;
	Model model;
	ArrayList<Element> elements;
	
	public double forceX=0;
	public double forceY;
	public boolean statX=false;
	public boolean statY=false;
	
	
	String name;
	
	public Node(double d,double y,Model model) {
		this.x=d;
		this.y=y;
		this.model=model;
		index=model.nodeList.size();
		model.nodeList.add(this);
		elements=new ArrayList<Element>();
	}
	public Node(double d,double y,Model model,String name) {
		this.x=d;
		this.y=y;
		this.model=model;
		index=model.nodeList.size();
		model.nodeList.add(this);
		this.name=name;
		elements=new ArrayList<Element>();
	}
	public void addElement(Element element){ elements.add(element);}
	//After creation
	public void addForce(double xForce,double yForce){
		this.forceX=xForce;
		this.forceY=yForce;
	}
	public void calculation(){
		if(statX||model.forceVectorMatrix.eliminated.contains(2*index))
			dX=0;
		else{
			dX=model.solution.getEntry(model.forceVectorMatrix.getArrayIndex(2*index));
		}
		if(statY||model.forceVectorMatrix.eliminated.contains(2*index+1))
			dY=0;
		else{
			dY=model.solution.getEntry(model.forceVectorMatrix.getArrayIndex(2*index+1));
			}
	}
	public double getElementForceX(Element element){
		ArrayList<Element> temp=(ArrayList<Element>)elements.clone();
		temp.remove(element);
		double nodeForce=0;
		if(temp.size()!=0)
			for(Element e:temp){
				nodeForce+=e.forceX;
			}
		return nodeForce;
	}
	public double getElementForceY(Element element){
		ArrayList<Element> temp=(ArrayList<Element>)elements.clone();
		temp.remove(element);
		double nodeForce=0;
		if(temp.size()!=0)
			for(Element e:temp){
				nodeForce+=e.forceY;
			}
		return nodeForce;
	}
	//Rendering
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.BLACK);
		g2.drawOval(getPixel(x)-nodeSize/2, getPixel(y)-nodeSize/2, nodeSize, nodeSize);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval(getPixel(x)-nodeSize/2+2, getPixel(y)-nodeSize/2+2, nodeSize-4, nodeSize-4);
		if(statX==true){
			g2.setColor(Color.WHITE);
			g2.drawLine(getPixel(x), getPixel(y)-35, getPixel(x), getPixel(y)+35);
		}
		if(statY==true){
			g2.setColor(Color.WHITE);
			g2.drawLine(getPixel(x)-35, getPixel(y), getPixel(x)+35, getPixel(y));
		}
		if(forceX!=0||forceY!=00){
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.CYAN);
			g2.drawString((Math.pow(Math.pow(forceX, 2)+Math.pow(forceY,2), 0.5d))+" N", getPixel(x-forceX/20-(forceX/20)/2)+5, getPixel(y+forceY/5)+0);
			g2.drawLine(getPixel(x-forceX/20), getPixel(y+forceY/4), getPixel(x), getPixel(y));
			g2.fillOval(getPixel(x)-5, getPixel(y)-5, 10, 10);
		}
		g2.setColor(Color.BLACK);
		if(name==null)
			g2.drawString("n:"+index, getPixel(x), getPixel(y)-15);
		else{
			g2.drawString(name,getPixel(x)-4,getPixel(y)-15);
		}
	}
	public int getPixel(double lenght){
		return (int)(lenght*ViewModel.scale+ViewModel.frame);
	}
	//Statics
	public static Node getNode(int index){
		for(Node n:Model.currentModel.nodeList){
			if(n.index==index)
				return n;
		}
		return null;
	}
	public static double getMaxX(Model model){
		double x=0;
		for(Node nod:model.nodeList){
			if(nod.x>x)
				x=nod.x;
		}
		return x;
	}
	public static double getMaxY(Model model){
		double y=0;
		for(Node nod:model.nodeList){
			if(nod.y>y)
				y=nod.y;
		}
		return y;
		
	}
	@Override
	public String toString() {
		if(name!=null)
			return "Node: " + name;
		else
			return "Node no: "+index;
	}

}
