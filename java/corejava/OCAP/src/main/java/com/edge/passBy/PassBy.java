package com.edge.passBy;

public class PassBy {
	 public static void main(String[] args)
	    {
	        int x = 5;
	        change(x);
	        System.out.println(x);
	        
	        Employee e=new Employee("Edge",30,200000,"Bangalore");
	        
	        change(e);
	        System.out.println(e);
	        change2(e);
	        System.out.println(e);
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
}




class Employee {
	String name;
	int age;
	float sal;
	String address;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String name, int age, float sal, String address) {
		super();
		this.name = name;
		this.age = age;
		this.sal = sal;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", sal=" + sal + ", address=" + address + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}