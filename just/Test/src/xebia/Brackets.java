package xebia;

import java.util.Scanner;
import java.util.Stack;

public class Brackets {

	public static void main(String[] args) {
		System.out.println("Enter Input");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] datas = input.split(" ");
		int len= Integer.parseInt(datas[0]);
		int counter = 0;
		Stack stack = new Stack<>();
		String flag = "YES";
		for(int i=1;i<=len;i++) {
			String data=datas[i];
			
				char[] chunk = data.toCharArray();
				for (char ltr : chunk) {
					if (ltr == '[' || ltr == '{' || ltr == '(') {
						stack.push(ltr);
					} else if ((ltr == ']' || ltr == '}' || ltr == ')') && !stack.isEmpty()) {
						if (ltr == ']' && ((char) stack.pop()) != '[') flag = "No";
						else if (ltr == '}' && ((char) stack.pop()) != '{') flag = "No";
						else if (ltr == ')' && ((char) stack.pop()) != '(') flag = "No";
					}
				}
			System.out.print(flag+" ");
			flag="YES";
		}  

	}

}
