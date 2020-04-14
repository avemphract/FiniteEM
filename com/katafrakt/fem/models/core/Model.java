package com.katafrakt.fem.models.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import com.katafrakt.fem.main.Initiate;
import com.katafrakt.fem.main.ViewModel;
import com.katafrakt.fem.system.Element;
import com.katafrakt.fem.system.Matrix;
import com.katafrakt.fem.system.Node;

public abstract class Model {
	double[][] stiffness;
	double[] forceVector;
	
	double[][] modiStiffness;
	double[] modiForceVector;
	
	public Matrix stiffnessMatrix;
	public Matrix forceVectorMatrix;
	
	static Node statNode;
	protected final double al=77000;
	protected final double st=206000;
	public static ArrayList<Model> modelList=new ArrayList<Model>();
	
	public ArrayList<Element> elementList;
	public ArrayList<Node> nodeList;
	public double delta;
	
	public RealVector solution;

	public Model() {
		currentModel=this;
		modelList.add(this);
		nodeList=new ArrayList<Node>();
		elementList=new ArrayList<Element>();
		setModel();
		createStiffness();
		for(Node n:nodeList){
			n.calculation();
		}
		for(Element e:elementList){
			e.calculateElon();
		}
	}
	public void setModel(){

	}
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.drawLine(ViewModel.frame, ViewModel.frame, Initiate.width, ViewModel.frame);
		g.setColor(Color.RED);
		g.drawLine(ViewModel.frame, ViewModel.frame, ViewModel.frame,Initiate.height);
		for(Element e:elementList)
			e.render(g);
	}
	public Color getElementColor(Element e){
		double max=350/2;
		float factor=(float) (e.stress/max);
		Color color;
		if(factor<0.5f)
			color=new Color(factor*2,1f-factor*2f,0);
		else if(factor<1f)
			color=new Color(1f-factor/2f,0,0);
		else
			color=Color.BLACK;
		return color;
	}

	public void createStiffness(){
		stiffness=new double[nodeList.size()*2][nodeList.size()*2];
		forceVector=new double[nodeList.size()*2];
		int i=0;
		for(Node n:nodeList){
			forceVector[2*i]=n.forceX;
			forceVector[2*i+1]=n.forceY;
			i++;
		}
		//Yazdýr.printVector(forceVector);
		for(Element e:elementList){
			double c2=e.constant*Math.pow(Math.cos(e.angle), 2);
			double s2=e.constant*Math.pow(Math.sin(e.angle), 2);
			double cs=e.constant*Math.cos(e.angle)*Math.sin(e.angle);
			stiffness[2*e.node1.index][2*e.node1.index]+=c2;
			stiffness[2*e.node1.index+1][2*e.node1.index]+=cs;
			stiffness[2*e.node1.index+1][2*e.node1.index+1]+=s2;
			stiffness[2*e.node1.index][2*e.node1.index+1]+=cs;
			
			stiffness[2*e.node2.index][2*e.node1.index]+=-c2;
			stiffness[2*e.node2.index+1][2*e.node1.index]+=-cs;
			stiffness[2*e.node2.index+1][2*e.node1.index+1]+=-s2;
			stiffness[2*e.node2.index][2*e.node1.index+1]+=-cs;
			
			stiffness[2*e.node2.index][2*e.node2.index]+=c2;
			stiffness[2*e.node2.index+1][2*e.node2.index]+=cs;
			stiffness[2*e.node2.index+1][2*e.node2.index+1]+=s2;
			stiffness[2*e.node2.index][2*e.node2.index+1]+=cs;
			
			stiffness[2*e.node1.index][2*e.node2.index]+=-c2;
			stiffness[2*e.node1.index+1][2*e.node2.index]+=-cs;
			stiffness[2*e.node1.index+1][2*e.node2.index+1]+=-s2;
			stiffness[2*e.node1.index][2*e.node2.index+1]+=-cs;
			
			/*stiffness[2*e.node2.index][2*e.node1.index]+=-e.constant;
			stiffness[2*e.node2.index][2*e.node2.index]+=e.constant;
			stiffness[2*e.node1.index][2*e.node2.index]+=-e.constant;
			
			stiffness[e.index][e.index]+=temp[0][0];
			stiffness[e.index+1][e.index]+=temp[1][0];
			stiffness[e.index+1][e.index+1]+=temp[1][1];
			stiffness[e.index][e.index+1]+=temp[0][1];*/
		}
		stiffnessMatrix=new Matrix(stiffness);
		forceVectorMatrix=new Matrix(forceVector);
		
		for(Node n:nodeList){
			if(n.statX){
				stiffnessMatrix.addElim(2*n.index);
				forceVectorMatrix.addElim(2*n.index);
			}
			if(n.statY){
				stiffnessMatrix.addElim(2*n.index+1);
				forceVectorMatrix.addElim(2*n.index+1);
			}
		}
		for(int j=0;j<stiffness[0].length;j++){
			boolean b=false;
			if(forceVector[j]!=0)
				b=true;
			for(int k=0;k<stiffness[0].length;k++){
				if(stiffness[j][k]!=0&&stiffness[k][j]!=0){
					b=true;
				}
			}
			if(b==false){
				if(!stiffnessMatrix.eliminated.contains(j)){
					stiffnessMatrix.addElim(j);
					forceVectorMatrix.addElim(j);
				}
			}
		}
		
		RealMatrix coefficient=new Array2DRowRealMatrix(stiffnessMatrix.array);
		//delta=Maths.getDeterminant(stiffnessMatrix.array);
		DecompositionSolver solver = new LUDecomposition(coefficient).getSolver();
		RealVector constant=new ArrayRealVector(forceVectorMatrix.vector);
		solution=solver.solve(constant);
	}	
	
	public static Model currentModel;
	public static Model getModel(){
		return currentModel;
	}
	public static void setModel(Model model){
		currentModel=model;
	}
}
