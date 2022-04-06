package myCalc;

import java.util.Scanner;

public class Main {
	
	static MyScanner mSc = new MyScanner ();
	static MyRomanNambers myRomanNambers = new MyRomanNambers ();
	static boolean myBoolean = false;
	static String result = null;
	static MyException myExc;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		do {
			myBoolean = false;
			System.out.println("Введіть дані та натисність Enter:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			try {
				result = mSc.start(str);
			} catch (Exception e) {
				myBoolean = true;
				if (e.getClass() == MyException.class) {
					System.out.println(e.toString());
				}
			}
			if (myBoolean) {
				System.out.println("Невірне введення! Повторіть введення.");
			} else {
				System.out.println(result);
				myBoolean = false;
			}
		} while (myBoolean);
	}
}