package myCalc;

public class MyRomanNambers {

	boolean myRomanNambers (String str) {
		int charRoman = 0;
		for (int j = 0; j < str.length(); j ++) {
			if ( 	(str.charAt(j) == 'i') || (str.charAt(j) == 'I') ||
					(str.charAt(j) == 'v') || (str.charAt(j) == 'V') ||
					(str.charAt(j) == 'x') || (str.charAt(j) == 'X') ||
					(str.charAt(j) == 'l') || (str.charAt(j) == 'L') ||
					(str.charAt(j) == 'c') || (str.charAt(j) == 'C') ||
					(str.charAt(j) == 'd') || (str.charAt(j) == 'D') ||
					(str.charAt(j) == 'm') || (str.charAt(j) == 'M')) {
				charRoman ++;
			}
		}
		return (charRoman != 0);
	}
	
	private int convertCharToInt (char ch) {
		int result = 0;
		if ( 		(ch == 'm') || (ch == 'M') ) {
			result = 1000;
		} else if ( (ch == 'd') || (ch == 'D') ) {
			result = 500;
		} else if ( (ch == 'c') || (ch == 'C') ) {
			result = 100;
		} else if ( (ch == 'l') || (ch == 'L') ) {
			result = 50;
		} else if ( (ch == 'x') || (ch == 'X') ) {
			result = 10;
		} else if ( (ch == 'v') || (ch == 'V') ) {
			result = 5;
		} else if ( (ch == 'i') || (ch == 'I') ) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}
	
	float setData(String str) throws MyException {
		float result = 0;
		myException (str);
		for (int i = 0; i < str.length(); i ++) {
			if ( (i + 1) < str.length() ) {
				if (convertCharToInt (str.charAt(i)) < convertCharToInt (str.charAt(i + 1))) {
					result = result - convertCharToInt (str.charAt(i));
				} else {
					result = result + convertCharToInt (str.charAt(i));
				}
			} else {
				result = result + convertCharToInt (str.charAt(i));
			}
		}
		myException (result);
		return result;
	}
	
	String convertRez (float inFloat) throws MyException {
		StringBuilder myStringBuilder = new StringBuilder();
		myStringBuilder.delete(0, myStringBuilder.length());
		float myFloat = inFloat;
		myException (myFloat);
		while (myFloat != 0) {
			if ( (myFloat/1000) >= 1.0) {
				myStringBuilder.append("M");
				myFloat = myFloat - 1000;
			} else if ( ((myFloat + 100) / 1000) >= 1.0) {
				myStringBuilder.append("CM");
				myFloat = myFloat - 900;
			} else if ( (myFloat/500) >= 1.0) {
				myStringBuilder.append("D");
				myFloat = myFloat - 500;
			} else if ( ((myFloat + 100) / 500) >= 1.0) {
				myStringBuilder.append("CD");
				myFloat = myFloat - 400;
			} else if ( (myFloat/100) >= 1.0) {
				myStringBuilder.append("C");
				myFloat = myFloat - 100;
			} else if ( ((myFloat + 10) / 100) >= 1.0) {
				myStringBuilder.append("XC");
				myFloat = myFloat - 90;
			} else if ( (myFloat/50) >= 1.0) {
				myStringBuilder.append("L");
				myFloat = myFloat - 50;
			} else if ( ((myFloat + 10) / 50) >= 1.0) {
				myStringBuilder.append("XL");
				myFloat = myFloat - 40;
			} else if ( (myFloat/10) >= 1.0) {
				myStringBuilder.append("X");
				myFloat = myFloat - 10;
			} else if ( ((myFloat + 1) / 10) >= 1.0) {
				myStringBuilder.append("IX");
				myFloat = myFloat - 9;
			} else if ( (myFloat/5) >= 1.0) {
				myStringBuilder.append("V");
				myFloat = myFloat - 5;
			} else if ( ((myFloat + 1) / 5) >= 1.0) {
				myStringBuilder.append("IV");
				myFloat = myFloat - 4;
			} else if ( (myFloat/1) >= 1.0) {
				myStringBuilder.append("I");
				myFloat = myFloat - 1;
			} else if ((myFloat < 1) && (myFloat > 0)) {
				myFloat = 0;
			}
		}
		return myStringBuilder.toString();
	}
	
	private void myException (float fl) throws MyException {
		if (fl > 3999) {
			throw new MyException("Занадто велике число!");
		} else if (fl <= 0) {
			throw new MyException("Неіснуюче число!");
		}
	}
	
	private void myException (String str) throws MyException {
		int booleanInt = 0, u;
		for (int i = 0; i < str.length(); i ++) {
			for (int j = 0; j < 4; j ++) {
				if (i != 0 ) {
					u = i - 1;
				} else {
					u = i;
				}
				if (str.length() == 1) {
					booleanInt ++;
				} else if (str.length() > 0) {
					if ( ( convertCharToInt (str.charAt(u)) < convertCharToInt (str.charAt(u + 1)) ) && 
							(convertCharToInt (str.charAt(u)) == Math.pow(10, j) ) && 
							(convertCharToInt (str.charAt(u + 1)) == (convertCharToInt (str.charAt(u)) * 5) ||
							(convertCharToInt (str.charAt(u + 1)) == (convertCharToInt (str.charAt(u)) * 10)) ) ) {
						booleanInt ++;
					} else if (convertCharToInt (str.charAt(u)) >= convertCharToInt (str.charAt(u + 1))) {
						booleanInt ++;
					}
				}
			}
		}
		if (booleanInt == 0) {
			throw new MyException("Неіснуюче число!");
		}
	}
}
