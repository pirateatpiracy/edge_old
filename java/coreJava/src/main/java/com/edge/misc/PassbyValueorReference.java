/**
 * 
 */
package com.edge.misc;

/**
 * @author edge
 *
 */
public class PassbyValueorReference {

	/**
	 * @param args
	 */

	public static void swap(Object pnt1, Object pnt2)
	{		
	  System.out.println("\n2:pnt1 "+pnt1+" pnt2 "+pnt2);
	  Object temp = pnt1;
	  System.out.println("3:pnt1 "+pnt1+" pnt2 "+pnt2 +" temp "+temp);
	  pnt1 = pnt2;
	  System.out.println("4:pnt1 "+pnt1+" pnt2 "+pnt2 +" temp "+temp);
	  pnt2 = temp;
	  System.out.println("5:pnt1 "+pnt1+" pnt2 "+pnt2 +" temp "+temp);
	}
	public static void assign(Point pnt1, Point pnt2) {
		pnt1.x = 100;
		pnt1.y = 100;
	}
	public static void main(String [] args)
	{
		int i=100,j=0;		
		System.out.println(" i "+i+" j "+j);
		swap(i,j);
		System.out.println(" \ni "+i+" j "+j);
		
	  Point pnt1 = new Point(0,0);
	  Point pnt2 = new Point(0,0);
	  System.out.println("\nA:pnt1 "+pnt1+" pnt2 "+pnt2);
	  assign(pnt1,pnt2);
	  System.out.println("B:pnt1 "+pnt1+" pnt2 "+pnt2);
	  swap(pnt1,pnt2);
	  System.out.println("\nC:pnt1 "+pnt1+" pnt2 "+pnt2);  
	}
}

class Point {
	int x,y;
	Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public String toString(){
		
		return x+" "+y;
		}
	
}
