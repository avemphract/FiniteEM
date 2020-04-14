package com.katafrakt.fem.system;

import com.katafrakt.framework.util.Yazdýr;

public class Maths {

	public static double getDeterminant(double[][] array){
		double sonuc=0;

		if(array[0].length==1){
			sonuc=array[0][0];
			return sonuc;
		}
		if(array[0].length==2){
			sonuc=array[0][0]*array[1][1]-array[0][1]*array[1][0];
			return sonuc;
		}
		if(haveZeroRow(array))
			return 0;
		for(int i=0;i<array[0].length;i++){
			
			if(array[0][i]==0)
				continue;
			double gecici[][]=new double[array.length-1][array.length-1];
			for(int j=1;j<array[0].length;j++){
				for(int k=0;k<array[0].length;k++){
					if(k<i){
						gecici[j-1][k]=array[j][k];
					}else if(k>i){
						gecici[j-1][k-1]=array[j][k];
					}
				}
			}
			
			sonuc+=array[0][i]*Math.pow(-1, i)*getDeterminant(gecici);
			if(array[0].length>15)
			System.out.println(array[0].length +" : "+sonuc);
		}
		return sonuc;
		
	}

	public static double[][] gaussElim(double[][] array){
		Yazdýr.printArray(array);
		System.out.println(getDeterminant(array));
		for(int i=0;i<array[0].length-1;i++){
			//System.out.println("i++ i: "+i);
			for(int l=i+1;l<array[0].length;l++){
				//System.out.println("l++ l: "+ l);
				double constant=array[l][i]/array[i][i];
				/*System.out.println("cs:"+array[l][i]/constant);
				System.out.println("te:"+array[i][i]);
				System.out.println(constant);*/
				for(int m=i;m<array[0].length;m++){
					//System.out.println("m++ m: "+m);

					array[m][l]=array[m][l]-(array[m][i]/constant);
					System.out.println(array[m][l]+" =["+m+"]["+l+"]-["+m+"]["+i+"]");
				}
				//Yazdýr.printArray(array);
				System.out.println();
				
			}
			System.out.println();
		}
		Yazdýr.printArray(array);

		System.out.println(getDeterminant(array));
		return array;
	}

	public static double[][] degraArray(int x,int y,double[][] array){
		int m = 0,n = 0;
		int size= array[0].length;
		double[][] result=new double[size-1][size-1];
		for(int i=0;i<size;i++){
			if(i==y)
				continue;
			
			for(int j=0;j<size;j++){
				if(j==x)
					continue;
				result[m][n]=array[j][i];
				m++;
			}
			m=0;
			n++;
		}
		return result;
	}

	public static boolean haveZeroRow(double array[][]){

		for(int i=0;i<array[0].length;i++){
			boolean row=true;
			boolean colon=true;
			for(int k=0;k<array[0].length;k++){
				if(array[k][i]!=0){
					row=false;
				}
				if(array[i][k]!=0){
					colon=false;
				}
			}
			if(row||colon)
				return true;
		}
		return false;
	}
	public static double[][] changeColumn(double[][] array,double[] vector,int x){
		double[][] result= new double[array[0].length][array[0].length];
		for(int i=0;i<array[0].length;i++)
			for(int j=0;j<array[0].length;j++){
				result[i][j]=array[i][j];
			}
		for(int i=0;i<result[0].length;i++){
			//System.out.print(i);
			result[i][x]=vector[i];
		}
		return result;
	}
	public static double[] removeVector(double[] vector,int x){
		double[] result=new double[vector.length-1];
		for(int i=0;i<vector.length-1;i++){
			if(i<x)
				result[i]=vector[i];
			else
				result[i]=vector[i+1];
		}
		return result;
	}

}

