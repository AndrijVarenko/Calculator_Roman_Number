package myCalc;

@SuppressWarnings("serial")
public class MyException extends Exception {

	private String string;
	
	MyException (String str) {
		string = str;
	}
	
	MyException () {
		string = "";
	}
	
	public String toString() {
		return "" + string;
	}
}
