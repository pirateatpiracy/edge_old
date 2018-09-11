package com.edge.algorithm.traceArea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//Coordinate [] one=new Coordinate0, 0{};
		//Area One=new Area("WashBay", new[])
		String data="WashBay::0,35:25,25:35,30:35,35;Parking::0,45:0,35:35,35:35,45;BodyShop::35,30:15,20:15,10:25,10:25,5:15,5:15,0:35,0;"
				+ "Quality::0,0:15,0:15,5:25,5:25,10:15,10:15,15:0,15;ServiceBay::0,15:15,15:15,20:25,25:0,35";
		List <Area> bayTypes=new LinkedList<Area>();
		String [] bayData=data.split(";");
		for (String bayType:bayData) {
			String bayName=bayType.split("::")[0];
			String [] coordinates=bayType.split("::")[1].split(":");
			List<Coordinate> coordinateList=new ArrayList<Coordinate>();
			for (String coordinate:coordinates) {				
				 coordinateList.add(new Coordinate(Integer.parseInt(coordinate.split(",")[0]), Integer.parseInt(coordinate.split(",")[1])));
			}
			bayTypes.add(new Area(bayName, coordinateList));
		}
		System.out.println(bayTypes);

	}

}
//point-in-polygon
//https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
