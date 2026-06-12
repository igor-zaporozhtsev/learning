package hackerrank.java_preparation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaMap {
	static Map<String, Integer> phoneBook = new HashMap<>();

	public static void main(String[] argh) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String name = in.nextLine();
			int phone = in.nextInt();
			phoneBook.put(name, phone);
			in.nextLine();
		}
		while (in.hasNext()) {
			String s = in.nextLine();
			var result = phoneBook.getOrDefault(s, null);
			System.out.println(result != null ? s + "=" + result : "Not found");
		}
	}
}