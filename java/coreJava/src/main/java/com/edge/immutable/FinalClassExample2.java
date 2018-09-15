package com.edge.immutable;

public class FinalClassExample2 {
	
	public static void main(String[] args) {
		 
		 Age age = new Age();
		 age.setDay(1);
		 age.setMonth(1);
		 age.setYear(1992);
		 
		 MmutableStudent mmutablestudent = new MmutableStudent(1, "Alex", age);
		 ImmutableStudent immutablestudent = new ImmutableStudent(1, "Alex", age);
		 
		 System.out.println("Alex age year before modification(mmutable) = " + mmutablestudent.getAge().getYear());
		 System.out.println("Alex age year before modification(immutable) = " + immutablestudent.getAge().getYear());
		 
		 
		 age.setYear(1993);
		 
		 System.out.println("Alex age year after modification(mmutable) = " + mmutablestudent.getAge().getYear());
		 System.out.println("Alex age year after modification(immutable) = " + immutablestudent.getAge().getYear());
		 

	
	
	 
	 }
}

final class MmutableStudent {
	 
	 private final int id;
	 private final String name;
	 private final Age age;
	 
	 public MmutableStudent(int id, String name, Age age) {
	 this.name = name;
	 this.id = id;
	 this.age = age;
	 }
	 
	 public int getId() {
	 return id;
	 }
	 
	 public String getName() {
	 return name;
	 }
	 
	 public Age getAge() {
	 return age;
	 }
	}

final class ImmutableStudent {
	 
	 private final int id;
	 private final String name;
	 private final Age age;
	 
	 public ImmutableStudent(int id, String name, Age age) {
		 this.name = name;
		 this.id = id;
		 Age copyAge = new Age();
		 copyAge.setDay(age.getDay());
		 copyAge.setMonth(age.getMonth());
		 copyAge.setYear(age.getYear());
		 this.age = copyAge;
		 }
	 
	 public int getId() {
	 return id;
	 }
	 
	 public String getName() {
	 return name;
	 }
	 
	 public Age getAge() {
	 return age;
	 }
	}

class Age {
	 
	 private int day;
	 private int month;
	 private int year;
	 
	 public int getDay() {
	 return day;
	 }
	 
	 public void setDay(int day) {
	 this.day = day;
	 }
	 
	 public int getMonth() {
	 return month;
	 }
	 
	 public void setMonth(int month) {
	 this.month = month;
	 }
	 
	 public int getYear() {
	 return year;
	 }
	 
	 public void setYear(int year) {
	 this.year = year;
	 }
	 
	}