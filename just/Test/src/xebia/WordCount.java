package xebia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class WordCount {

	public static void main(String[] args) {

		System.out.println("Enter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String [] datas=input.split(" ");
		Map words=new HashMap<String,Integer>();
		for (String data:datas) {
			if(words.containsKey(data)) {
				Integer temp=(Integer) words.get(data);
				words.put(data, temp+1);
			}else {words.put(data, 1);}
			
		}
			
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(words.entrySet());
		for (Entry<String, Integer> entry : list) {
			System.out.println("\"key\":\""+entry.getKey() + "\",\"value\":" + entry.getValue());
		}
			
			
		

	}




}
