package com.edge.math;

import java.math.BigDecimal;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;
import org.nevec.rjm.BigDecimalMath;

public class BigDecimalTest {
	public static void main(String[] arg) {
		int a = 11;
		int b = 2;
		double j = 1.999999;
		double k = 10.99999;
		System.out.println(Math.pow(a, b) + " ||| " + Math.pow(a, j) + "  || " + Math.pow(k, j));

		
		
		
		BigDecimal c = new BigDecimal(11);
		BigDecimal d = new BigDecimal(2);
		BigDecimal result = BigDecimalMath.pow(c, d);
		System.out.println(result);
		System.out.println(result.toPlainString());
		System.out.println(result.toEngineeringString());
		System.out.println(Integer.valueOf(result.intValue()));

		
		
		// System.out.println(c.subtract(d).pow(2));
		Apcomplex f = new Apcomplex(new Apfloat(c, 16));
		Apcomplex g = new Apcomplex(new Apfloat(d, 16));
		Apcomplex resultt = ApcomplexMath.pow(f, g);
		System.out.println(resultt);
		System.out.println(new BigDecimal(String.format("%s", resultt)));

	}

	int hex2decimal(String s) {
		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}
}
