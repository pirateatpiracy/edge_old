package theDataTeam;
/*
1:2,3;2:4,5,6,7;5:8,9,10;10:11,12,13,14;11:15,16;14:17,18,19;16:20,21
11
*/   
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;



public class Ideoneee {
	static Map<String, String[]> hrrcy = new HashMap<String, String[]>();
	static Map<String, String[]> hrrcyf = new HashMap<String, String[]>();
	public static void main(String[] args) { 
		System.out.println("Enter Hierarchy and Emp Id ");
		Scanner sc = new Scanner(System.in);
		String hierarchy = sc.nextLine();
		// System.out.println("Enter Employee ID ");
		String empId = sc.nextLine();
		String[] empList = hierarchy.split(";");
		hrrcy = new HashMap<String, String[]>();
		for (String emp : empList) {
			String mng = emp.split(":")[0];
			String[] sbdnts = emp.split(":")[1].split(",");
			hrrcy.put(mng, sbdnts);
		}

		for (Map.Entry<String, String[]> entry : hrrcy.entrySet()) {
			String mngr = entry.getKey();
			String[] emps = entry.getValue();

			System.out.print(mngr + "	:");
			for (String empl : emps)
				System.out.print(empl + ", ");
			System.out.println("");
			String [] empss=emps;
			for (String empl : emps) {
				String[] sbdnts;
				//= {"xx","yy"};
				sbdnts = chckSbrdnt(empl);
				if(sbdnts!=null)
					
				empss=Stream.of(empss, sbdnts).flatMap(Stream::of).toArray(String[]::new);
				
			}hrrcyf.put(mngr, empss);
		}

		if (hrrcyf.containsKey(empId)) {
			System.out.print("\n\n\n"+empId + " : ");
			String[] res = hrrcyf.get(empId);
			for (String emp : res) {
				System.out.print(emp + ",");
			}
		}

		else {
			System.out.println("\n\n\nEmp: "+empId+" is not a manager");
		}
	}

	static String[] chckSbrdnt(String emp) {
		String[] emps ={};
		if (hrrcy.containsKey(emp)) {
			emps = hrrcy.get(emp);
		for (String empl : emps)  {
		
		emps=Stream.of(emps,chckSbrdnt(empl)).flatMap(Stream::of).toArray(String[]::new);}
		
		}
		return emps;

}}

