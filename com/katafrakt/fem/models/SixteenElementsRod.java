package com.katafrakt.fem.models;

import com.katafrakt.fem.models.core.Model;
import com.katafrakt.fem.system.Element;
import com.katafrakt.fem.system.Node;

public class SixteenElementsRod extends Model {
	public SixteenElementsRod(){
		super();
	}
	@Override
	public void setModel(){
		Node node1=new Node(0,0,this);
		node1.statX=true;
		Node node2=new Node(75,0,this);
		Node node3=new Node(150,0,this);
		Node node4=new Node(225,0,this);
		Node node5=new Node(300,0,this);
		
		Node node6=new Node(371,0,this);
		Node node7=new Node(442,0,this);
		Node node8=new Node(514,0,this);
		Node node9=new Node(585,0,this);
		Node node10=new Node(657,0,this);
		Node node11=new Node(728,0,this);
		Node node12=new Node(800,0,this);
		node12.addForce(2400d, 0);
		
		Node node13=new Node(880,0,this);
		Node node14=new Node(960,0,this);
		Node node15=new Node(1040,0,this);
		Node node16=new Node(1120,0,this);
		Node node17=new Node(1200,0,this);
		node17.addForce(-1200d, 0);
		
		Element element1=new Element(node1,node2,59.1d,24d,al,this);
		Element element2=new Element(node2,node3,57.4d,24d,al,this);
		Element element3=new Element(node3,node4,55.6d,24d,al,this);
		Element element4=new Element(node4,node5,53.9d,24d,al,this);
		
		Element element5=new Element(node5,node6,52.2d,0,al,this);
		Element element6=new Element(node6,node7,50.5d,0,al,this);
		Element element7=new Element(node7,node8,48.8d,0,al,this);
		Element element8=new Element(node8,node9,47.2d,0,al,this);
		Element element9=new Element(node9,node10,45.5d,0,al,this);
		Element element10=new Element(node10,node11,43.8d,0,al,this);
		Element element11=new Element(node11,node12,42.2d,0,al,this);
		
		Element element12=new Element(node12,node13,40.4d,0,st,this);
		Element element13=new Element(node13,node14,38.5d,0,st,this);
		Element element14=new Element(node14,node15,36.7d,0,st,this);
		Element element15=new Element(node15,node16,34.8d,0,st,this);
		Element element16=new Element(node16,node17,32.9d,0,st,this);
	}
	@Override
	public String toString() {
		return "SixteenElements";
	}
	
}
