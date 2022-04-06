package myCalc;

import java.util.ArrayList;

public class MyScanner {
	
	MyRomanNambers myRomanNambers = new MyRomanNambers();
	
	StringBuilder sb = new StringBuilder();

	private int sum = 0, div1 = 0, div2 = 0, sub = 0, mult = 0;
	private float a, b;
	private String strA, strB;
	private String result = null;
	private char index = ' ';
	
	private StringBuilder notSpace (String stringIn) {
		StringBuilder stringBuilderNotSpace = new StringBuilder();
		stringBuilderNotSpace.delete(0, stringBuilderNotSpace.length());
		for(int i = 0; i < stringIn.length(); i++){
			 if(stringIn.charAt(i) != ' ') {
				 stringBuilderNotSpace.append(stringIn.charAt(i));
			 }
		}
		return stringBuilderNotSpace;
	}
	
	private char indexChar (String str) {
		div1 = 0;
		div2 = 0;
		mult = 0;
		sum = 0;
		sub = 0;
		sb.delete(0, sb.length());
		sb.append(str);
		for (int i = 0; i < sb.length(); i ++) {
			 if (sb.charAt(i) == '/') {
				 index = '/';
				 div1 ++;
			 } else if (sb.charAt(i) == ':') {
				 index = ':';
				 div2 ++;
			 } else if (sb.charAt(i) == '*') {
				 index = '*';
				 mult ++;
			 } else if (sb.charAt(i) == '+') {
				 index = '+';
				 sum ++;
			 } else if (sb.charAt(i) == '-') {
				 index = '-';
				 sub ++;
			 }
		}
		if((div1 + div2 + mult + sum + sub) == 1) {
			return index;
		} else {
			return ' ';
		}
	}
	
	String start (String str) throws Exception {
		ArrayList <Integer> myList = new ArrayList <Integer> ();
		StringBuilder resultStringBuilder = new StringBuilder ();
		StringBuilder resultString = new StringBuilder ();
		String result = null;
		resultStringBuilder.delete(0, resultStringBuilder.length());
		resultStringBuilder = notSpace(str);
		for (int i = 0, j = 0; i < resultStringBuilder.length(); i ++) {
			if ( (resultStringBuilder.charAt(i) == ';') || 
					(resultStringBuilder.charAt(i) == ',') ) {
				myList.add(j, i);
				j ++;
			}
		}
		if (myList.size() == 0) {
			result = resultStringBuilder.toString();
			resultString.append(calc(result));
		} else {
			for (int i = 0, j = 0; i < myList.size(); i ++) {
				result = resultStringBuilder.substring(j, myList.get(i));
				resultString.append(result + " = " + calc(result) + "\n");
				j = myList.get(i) + 1;
				if ( ((i + 1) == myList.size()) && (myList.get(i) != (resultStringBuilder.length() - 1)) ) {
					result = resultStringBuilder.substring(j, resultStringBuilder.length());
					resultString.append(result + " = " + calc(result) + "\n");
				}
			}
		}
		return resultString.toString();
	}

	private String calc (String str) throws Exception {
		if (indexChar (str) != ' ') {
			sb.delete(0, sb.length());
			sb.append(str);
			String string = String.valueOf(indexChar (str));
			strA = sb.substring(0, sb.indexOf(string));
			strB = sb.substring((sb.indexOf(string) + 1), sb.length());

			if ( (myRomanNambers.myRomanNambers(strA)) && 
					(myRomanNambers.myRomanNambers(strB)) ) {
				a = myRomanNambers.setData(strA);
				b = myRomanNambers.setData(strB);
				result = myRomanNambers.convertRez(result (str, a, b));
			} else if (! ( (myRomanNambers.myRomanNambers(strA)) ^ 
					 (myRomanNambers.myRomanNambers(strB)) )) {
				 if ( (Integer.parseInt(strA) != 0) && (Integer.parseInt(strB) != 0) ) {
					 result = String.valueOf(result (str, Integer.parseInt(strA), 
							 Integer.parseInt(strB)));
				 } else {
					 throw new MyException("Невірне введення!");
				 }
			} else { 
				throw new MyException("Невірний формат!");
			}
		}
		return result;
	}
	
	private float result (String str, int aInteger, int bInteger) {
		float result = 0.0f;
		
		if ( (indexChar(str) == '/') || (indexChar(str) == ':') ) {
			result = (float) div(aInteger, bInteger);
		} else if (indexChar(str) == '+') {
			result = (float) sum(aInteger, bInteger);
		} else if (indexChar(str) == '-') {
			result = (float) sub (aInteger, bInteger);
		} else if (indexChar(str) == '*') {
			result = (float) mult(aInteger, bInteger);
		}
		return result;
	}
	
	private float result (String str, float aFloat, float bFloat) {
		float result = 0.0f;
		
		if ( (indexChar(str) == '/') || (indexChar(str) == ':') ) {
			result = div(aFloat, bFloat);
		} else if (indexChar(str) == '+') {
			result = sum(aFloat, bFloat);
		} else if (indexChar(str) == '-') {
			result = sub(aFloat, bFloat);
		} else if (indexChar(str) == '*') {
			result = mult(aFloat, bFloat);
		}
		
		return result;
	}
	
	private float sum (float a, float b) {
		return a + b;
	}
	
	private float div (float a, float b) {
		return a / b;
	}
	
	private float sub (float a, float b) {
		return a - b;
	}
	
	private float mult (float a, float b) {
		return a * b;
	}
	
	private float sum (int a, int b) {
		return (float) a + b;
	}
	
	private float div (int a, int b) {
		return (float) a / b;
	}
	
	private float sub (int a, int b) {
		return (float) a - b;
	}
	
	private float mult (int a, int b) {
		return (float) a * b;
	}
}