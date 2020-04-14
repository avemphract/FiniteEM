package com.katafrakt.fem.system;

import java.util.ArrayList;

public class Matrix {
	public double[][] array;
	public double[] vector;
	
	public ArrayList<Integer> eliminated=new ArrayList<Integer>();
	
	public Matrix(double[][] array){
		this.array=array.clone();
	}
	public Matrix(double[] vector){
		this.vector=vector.clone();
	}
	public void addElim(int a){
		this.eliminated.add(a);
		if(array!=null)
			array= Maths.degraArray(getArrayIndex(a), getArrayIndex(a), array);
		if(vector!=null)
			vector= Maths.removeVector(vector, getArrayIndex(a));
	}
	public int getArrayIndex(int b){
		int k=b;
		if(!eliminated.isEmpty()){
			for(int i:eliminated){
				if(b>i)
					k--;
			}
		}
		return k;
	}
}
