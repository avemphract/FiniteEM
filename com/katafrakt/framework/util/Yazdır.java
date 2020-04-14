package com.katafrakt.framework.util;

public class Yazd�r {
	public static void onlyStr(String string){
		System.out.println(string);
	}
	public static void strAndDou(String string,double dou){
		System.out.println(string+dou);
	}
	public static void printArray(double[][] array){
		for(double[] co:array){
			for(double p:co){
				System.out.print(String.format(("%.1e"), p)+" ");
			}
			System.out.println(";");
		}
	}
	public static void printVector(double[] vector){
		System.out.println();
		for(double e:vector){
			System.out.print(e+" ");
		}
	}

}
