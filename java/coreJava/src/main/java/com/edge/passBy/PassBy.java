package com.edge.passBy;

public class PassBy {
	 public static void main(String[] args)
	    {
	        int i = 5;
	        change(i);
	        System.out.println(i);
	        
	        Employee e=new Employee("Edge",30,200000,"Bangalore");
	        
	        change(e);
	        System.out.println(e);
	        change2(e);
	        System.out.println(e);
	        
	        Employee x=new Employee("X",30,200000,"Bangalore");
	        Employee y=new Employee("Y",30,200000,"Bangalore");
	        
	        System.out.println("\n\n");
	        
	        System.out.println(x);
	        System.out.println(y);
	        
	        swap(x,y);
	        
	        System.out.println(x);
	        System.out.println(y);
	        
	        
	    }
	    public static void change(int x)
	    {
	        x = 10;
	    }
	    
	    public static void change(Employee  e)
	    {
	        Employee d=e;
	        d.setName("Dimono");
	        Employee f=new Employee();
	        f=e;
	        
	    }
	    public static void change2(Employee  e)
	    {	       
	        Employee f=new Employee();
	        System.out.println(f);
	        f=e;
	        System.out.println(f);
	        f.setName("Pirate");
	    }
	    public static void swap(Employee  x,Employee y) {
	    	Employee temp =x;
	    	x=y;
	    	change(x);
	    	y=temp;
	    	
	    }
}



