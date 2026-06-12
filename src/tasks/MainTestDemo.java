package tasks;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;

public class MainTestDemo {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "hello");
		map.put(1, "greetings");

		PrintUtil.print(map);

	}
}
