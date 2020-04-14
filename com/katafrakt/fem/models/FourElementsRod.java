package com.katafrakt.fem.models;

import com.katafrakt.fem.models.core.Model;
import com.katafrakt.fem.system.Element;
import com.katafrakt.fem.system.Node;

public class FourElementsRod extends Model {
	public FourElementsRod(){
		super();
	}
	@Override
	public void setModel(){
		Node node1=new Node(0,0,this);
		node1.statX=true;
		Node node2=new Node(300,0,this);
		Node node3=new Node(550,0,this);
		Node node4=new Node(800,0,this);
		node4.addForce(2400, 0);
		Node node5=new Node(1200,0,this);
		node5.addForce(-1200, 0);
		
		new Element(node1,node2,56d,24d,al,this);
		new Element(node2,node3,50.083333d,0,al,this);
		new Element(node3,node4,44.25d,0,al,this);
		new Element(node4,node5,36.666d,0,st,this);
	}
	@Override
	public String toString() {
		return "FourElements";
	}

}
