package theDataTeam;

/*
1:2,3;2:4,5,6,7;5:8,9,10;10:11,12,13,14;11:15,16;14:17,18,19;16:20,21
11
*/ 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Ideone {
	static Map<String, ArrayList<String>> hrrcy = new HashMap<String, ArrayList<String>>();
	static Map<String, ArrayList<String>> hrrcyf = new HashMap<String, ArrayList<String>>();
	public static void main(String[] args) { 
		System.out.println("Enter Hierarchy and Emp Id ");
		Scanner sc = new Scanner(System.in);
		String hierarchy = sc.nextLine();
		// System.out.println("Enter Employee ID ");
		String empId = sc.nextLine();
		sc.close();
		String[] empList = hierarchy.split(";");
		hrrcy = new HashMap<String, ArrayList<String>>();
		for (String emp : empList) {
			String mng = emp.split(":")[0];
			String[] sbdnts = emp.split(":")[1].split(",");
			hrrcy.put(mng,new ArrayList<String>(Arrays.asList(sbdnts)) );
		}

		for (Map.Entry<String, ArrayList<String>> entry : hrrcy.entrySet()) {
			String mngr = entry.getKey();
			ArrayList<String> emps = entry.getValue();
			ArrayList<String> sbdnts=new ArrayList<String>();
			//System.out.print(mngr + "	:");
		/*	for (String empl : emps)
				System.out.print(empl + ", ");
			System.out.println("");*/
			sbdnts.addAll(emps);
			for (String empl : emps) {
				
				/*sbdnts = chckSbrdnt(empl);
				if(sbdnts!=null)*/
				
				sbdnts.addAll(chckSbrdnt(empl));
				//	emps=emps.addAll(sbdnts);
				
			}hrrcyf.put(mngr, sbdnts);
		}

		if (hrrcyf.containsKey(empId)) {
			ArrayList<Integer> result=new ArrayList<Integer>();
			System.out.print("\n\n\n"+empId + " : ");
			ArrayList<String> res = hrrcyf.get(empId);
			for(String number : res) {
				result.add(Integer.parseInt(number)); 
				}
			Collections.sort(result);
			for (Integer emp : result) {
				System.out.print(emp + ",");
			}
		}

		else {
			System.out.println("\n\n\n "+empId+": is not a manager");
		}
	}

	static ArrayList<String> chckSbrdnt(String emp) {
		ArrayList<String> emps=new ArrayList<String>();
		ArrayList<String> sbdnts=new ArrayList<String>();
		if (hrrcy.containsKey(emp)) {
			emps = hrrcy.get(emp);
			
			sbdnts.addAll(emps);
		for (String empl : emps) 
			sbdnts.addAll(chckSbrdnt(empl));		
		}
		return sbdnts;

}}

