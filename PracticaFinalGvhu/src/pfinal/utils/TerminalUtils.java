package pfinal.utils;

import java.util.Scanner;

public class TerminalUtils {
	public static Scanner sc = new Scanner(System.in);
	
	public static void out(String text) {
		System.out.println(text);
	}
	
	public static int getInt() {
		int number = Integer.parseInt(sc.nextLine());
		return number;
	}

	public static String getString() {
		String text = sc.nextLine();
		return text;
	}
	
	public static String inputText() {
		String result = sc.nextLine();
		return result;
	}
	
	public static int inputInt() {
		int result = Integer.parseInt(sc.nextLine());
		return result;
	}

	public static float inputFloat() {
		float result = Float.parseFloat(sc.nextLine());
		return result;
	}
}
