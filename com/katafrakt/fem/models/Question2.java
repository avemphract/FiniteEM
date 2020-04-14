package com.katafrakt.fem.models;

import com.katafrakt.fem.models.core.Model;
import com.katafrakt.fem.system.Element;
import com.katafrakt.fem.system.Node;

public class Question2 extends Model {
	final double material=200000;
	final double bod=100;
	final double bid=80d;
	final double kod=70d;
	final double kid=60d;
	final double c=1000;
	
	final double t=1000;
	public void setModel(){
		Node U=new Node(0, 0, this,"U");
		U.statX=true;
		U.statY=true;
		Node N=new Node(0,10*t,this,"N");
		Node O=new Node(0,12.5f*t,this,"O");
		Node A=new Node(0,15*t,this,"A");
		
		Node M=new Node(4*t,10*t,this,"M");
		Node P=new Node(4*t,12.5f*t,this,"P");
		Node B=new Node(4*t,15*t,this,"B");
		
		Node L=new Node(8*t,10*t,this,"C");
		L.addForce(0, -5*t);
		Node Q=new Node(8*t,12.5f*t,this,"Q");
		Node C=new Node(8*t,15*t,this,"L");
		
		Node K=new Node(12*t,10*t,this,"K");
		K.addForce(0, -10*t);
		Node D=new Node(12*t,15*t,this,"D");
		
		Node J=new Node(16*t,10*t,this,"J");
		J.addForce(0, -20*t);
		Node R=new Node(16*t,12.5f*t,this,"R");
		Node E=new Node(16*t,15*t,this,"E");
		
		Node I=new Node(20*t,10*t,this,"I");
		Node S=new Node(20*t,12.5f*t,this,"S");
		Node F=new Node(20*t,15*t,this,"F");
		
		Node V=new Node(24*t,0,this,"V");
		V.statY=true;
		Node H=new Node(24*t,10*t,this,"H");
		Node T=new Node(24*t,12.5f*t,this,"T");
		Node G=new Node(24*t,15*t,this,"G");
		
		Element e1=new Element(U, N, bod, bid, material, this);
		Element e2=new Element(U, M, bod, bid, material, this);
		
		Element e3=new Element(N, O, kod, kid, material, this);
		Element e4=new Element(O, A, kod, kid, material, this);

		Element e6=new Element(N, M, kod, kid, material, this);
		Element e7=new Element(O, M, kod, kid, material, this);
		Element e8=new Element(O, B, kod, kid, material, this);	
		Element e9=new Element(A, B, kod, kid, material, this);

		Element e10=new Element(M, P, kod, kid, material, this);
		Element e11=new Element(P, B, kod, kid, material, this);

		Element e12=new Element(M, L, kod, kid, material, this);
		Element e13=new Element(P, L, kod, kid, material, this);
		Element e14=new Element(P, C, kod, kid, material, this);
		Element e15=new Element(B, C, kod, kid, material, this);

		Element e16=new Element(L, Q, kod, kid, material, this);
		Element e17=new Element(Q, C, kod, kid, material, this);

		Element e18=new Element(L, K, kod, kid, material, this);
		Element e19=new Element(Q, K, kod, kid, material, this);
		Element e20=new Element(Q, D, kod, kid, material, this);
		Element e21=new Element(C, D, kod, kid, material, this);
		
		Element e22=new Element(K, J, kod, kid, material, this);
		Element e23=new Element(K, R, kod, kid, material, this);
		Element e24=new Element(D, R, kod, kid, material, this);
		Element e25=new Element(D, E, kod, kid, material, this);
		
		Element ee=new Element(K,D,kod,kid,material,this);
		
		Element e26=new Element(J, R, kod, kid, material, this);
		Element e27=new Element(R, E, kod, kid, material, this);
		
		Element e28=new Element(J, I, kod, kid, material, this);
		Element e29=new Element(J, S, kod, kid, material, this);
		Element e30=new Element(E, S, kod, kid, material, this);
		Element e31=new Element(E, F, kod, kid, material, this);
		
		Element e32=new Element(I, S, kod, kid, material, this);
		Element e33=new Element(S, F, kod, kid, material, this);
		
		Element e34=new Element(I, H, kod, kid, material, this);
		Element e35=new Element(I, T, kod, kid, material, this);
		Element e36=new Element(F, T, kod, kid, material, this);
		Element e37=new Element(F, G, kod, kid, material, this);
		
		Element e38=new Element(H, T, kod, kid, material, this);
		Element e39=new Element(T, G, kod, kid, material, this);
		
		Element e40=new Element(I, V, bod, bid, material, this);
		Element e41=new Element(V, H, bod, bid, material, this);
	}
	@Override
	public String toString() {
		return "Question2";
	}
	
}
