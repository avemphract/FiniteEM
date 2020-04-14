package com.katafrakt.fem.system;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.katafrakt.fem.main.ViewModel;
import com.katafrakt.fem.models.Question2;
import com.katafrakt.fem.models.core.Model;

public class Element {

	//Nodes
	public Node node1;
	public Node node2;
	//Given parameters
	public double outerDia;
	public double inletDia;
	public double lenght;
	public double material;
	//While construction Calculated
	public double angle;
	public double area;
	public double constant;
	//After calculation
	public double elongation;
	private double elongationX;
	private double elongationY;
	public double force;
	public double forceX;
	public double forceY;
	public double stress;
	public double inertia;
	Model model;
	public int index;

	public Element(Node node1,Node node2,double outerDia,double inletDia,double material,Model model) {
		this.node1=node1;
		this.node2=node2;
		this.outerDia=outerDia;
		this.inletDia=inletDia;
		this.material=material;
		
		this.model=model;
		index=model.elementList.size();
		
		lenght=Math.pow(Math.pow(node1.x-node2.x, 2)+Math.pow(node1.y-node2.y, 2), 0.5d);
		angle=Math.atan((node2.y-node1.y)/(node2.x-node1.x));
		area=(Math.pow(outerDia, 2)-Math.pow(inletDia, 2))*Math.PI/4;
		constant=(material*area)/lenght;
		inertia=(Math.pow(outerDia, 4)-Math.pow(inletDia, 4))*Math.PI/64;
		
		node1.addElement(this);
		node2.addElement(this);
		model.elementList.add(this);
	}
	public double tensileTest(){
		return (stress/355f);
	}
	public double bucklingTest(){
		double sigmakare=area*Math.pow(lenght,2)/inertia;
		double critStress=material*Math.pow(Math.PI,2)/sigmakare;
		//System.out.print(critStress);
		return (stress/critStress);
	} 
	//After Creation
	public void calculateElon(){
		elongationX=node2.dX-node1.dX;
		forceX=elongationX*constant*Math.cos(angle);
		elongationY=node2.dY-node1.dY;
		forceY=elongationY*constant*Math.sin(angle);
		
		force=Math.pow(Math.pow(forceY, 2)+Math.pow(forceX, 2),0.5d);
		
		stress=Math.pow(Math.pow(forceX,2)+Math.pow(forceY,2), 0.5d)/area;
		
		elongation=Math.pow(Math.pow(elongationX,2)+Math.pow(elongationY,2), 0.5d);
	}	
	//Rendering
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke(new BasicStroke((int)(outerDia/10)));
	    if(model instanceof Question2)
	    	g2.setColor(model.getElementColor(this));
	    else
	    	g2.setColor(Color.BLACK);
		g2.drawLine(getPixel(node1.x), getPixel(node1.y), getPixel(node2.x), getPixel(node2.y));
		g2.setColor(Color.BLACK);
		if(node1.name!=null &&node2.name!=null){
			g2.drawString(node1.name+"-"+node2.name, getPixel(node2.x-(node2.x-node1.x)/2), getPixel(node2.y-(node2.y-node1.y)/2)-10);
		}
		else
			g2.drawString("e:"+index, getPixel(node2.x-(node2.x-node1.x)/2), getPixel(node2.y-(node2.y-node1.y)/2)-10);
		node1.render(g);
		node2.render(g);
	}
	public int getPixel(double lenght){
		return (int)(lenght*ViewModel.scale+ViewModel.frame);
	}
	@Override
	public String toString() {
		if(node1.name!=null&&node2.name!=null)
			return node1.name+"-"+node2.name;
		return "Element: "+index;
	}
	
}
