package Calculator;
import java.awt.EventQueue;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Stack;

/**
*
* @author DenizDikbiyik
*/

public class RPN_Engine {
	
	/**
	* Numbers are specified here to make calculations later.
	*/
	public static Double num1 = 0.0;
	public static Double num2 = 0.0;
	public static Double result = 0.0;
	static Stack<Double> stack = new Stack<>();
	
	/**
	* Variables below are given to check some conditions later.
	*/
	static int checker = 0;
	static int isEntered = 0;
	static int errorCheck = 0;
	static int divZeroCheck = 0;	
	public static String textFieldSetter = "";
	
	/**
	* Run the program from main method.
	* @param args
	*/
	public static void main(String[] args) throws EmptyStackException {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPN_GUI GUI = new RPN_GUI();					
					GUI.setVisible(true);		
					System.out.println("Please use GUI.");
				} 
				catch (Throwable err) {
		            System.out.println(err.getMessage());
		        } 
			}
		});	     
	}
       
	/**
	* numberButtons method writes numbers to the text field if the conditions are okay.
	* If 0 is seen on the screen, number will not be added to the right, it will be replaced with 0.
	* If there is an error on the text field, number will not be added to field.
	* While adding numbers, thousand separators will be seen.
	* @param i and textFieldGetter because i brings the number of the button, and text field reaches here from GUI via getter.
	*/
    public static void numberButtons (int i, String textFieldGetter) {	    	
    	if(textFieldGetter.equals("0")) {
			textFieldGetter = "";
		}
    	else if(textFieldGetter.equals("-0")) {
			textFieldGetter = "-";
		}
    	    	
    	if(divZeroCheck == 1) {
			stack.pop();
		}			
		if(checker == 0 && !textFieldGetter.equals("Enter number.") && !textFieldGetter.equals("Enter second number.") && !textFieldGetter.equals("Error, second decimal.") && !textFieldGetter.equals("Stack is full.")) {													
			if(!textFieldGetter.isEmpty() && errorCheck == 0) {				
				for(int j = 0; j < textFieldGetter.length(); j++) {
					if(textFieldGetter.charAt(j) == '.') {
						textFieldSetter = textFieldGetter.concat(String.valueOf(i));
						break;
					}
					else {
						String s = textFieldGetter.replace(",", "");
						double a = Double.parseDouble(s.concat(String.valueOf(i)));					
						if(a > 999.9) {
							textFieldSetter = String.format(Locale.US, "%,.0f", a);
						}
						else {
							textFieldSetter = textFieldGetter.concat(String.valueOf(i));
						}
					}
				}				
			}																
			else {
				errorCheck = 0;
				textFieldSetter = String.valueOf(i);
			}																		
		}	
		else if(checker == 1 || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.") || textFieldGetter.equals("Stack is full.")) {
			checker = 0;
			textFieldSetter = String.valueOf(i);																				
		}
		isEntered = 0;
		divZeroCheck = 0;				
	}
      
    /**
	* Decimal button also checks some conditions as number buttons and adds decimal point according to result.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void decimalButton (String textFieldGetter) {
    	if(textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.") || textFieldGetter.equals("Stack is full.")) {
			textFieldSetter = "0.";
			errorCheck = 0;
			checker = 0;
		}
		else if(textFieldGetter.equals("-0")) {
			textFieldSetter = "-0.";
		}
		else {			
			for(int j = 0; j < textFieldGetter.length(); j++) {
				if(textFieldGetter.charAt(j) == '.') {
					textFieldSetter = "Error, second decimal.";
					errorCheck = 1;
					break;
				}
				else {
					if(checker == 1) {
						textFieldSetter = "0.";
						checker = 0;
					}
					else {
						textFieldSetter = textFieldGetter.concat(".");
					}
				}
			}					
		}	    	
	}   
    
    /**
	* Change sign button also checks some conditions as number buttons and changes the sign of numbers according to result.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void changeSignButton (String textFieldGetter) {
    	if(stack.empty() && textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.") || textFieldGetter.equals("Stack is full.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}
		else {
			if(!textFieldGetter.equals("0.") 
					&& !textFieldGetter.equals("0")
					&& !textFieldGetter.equals("1")
					&& !textFieldGetter.equals("1.")
					&& !textFieldGetter.equals("2")
					&& !textFieldGetter.equals("2.")
					&& !textFieldGetter.equals("3")
					&& !textFieldGetter.equals("3.")
					&& !textFieldGetter.equals("4")
					&& !textFieldGetter.equals("4.")
					&& !textFieldGetter.equals("5")
					&& !textFieldGetter.equals("5.")
					&& !textFieldGetter.equals("6")
					&& !textFieldGetter.equals("6.")
					&& !textFieldGetter.equals("7")
					&& !textFieldGetter.equals("7.")
					&& !textFieldGetter.equals("8")
					&& !textFieldGetter.equals("8.")
					&& !textFieldGetter.equals("9")
					&& !textFieldGetter.equals("9.")) {				
				if(divZeroCheck != 1) {
					double temp = Double.parseDouble(textFieldGetter.replace(",", ""));
					temp *= -1;
					String tempToStr = Double.toString(temp);
					textFieldSetter = tempToStr;
					errorCheck = 0;
				}
				else {
					stack.pop();
					textFieldSetter = "-0";
					divZeroCheck = 0;
					checker = 0;
					errorCheck = 0;
				}			
			}
			else if (textFieldGetter.equals("0.")){
				textFieldSetter = "-0.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("0")) {
				textFieldSetter = "-0";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("1.")){
				textFieldSetter = "-1.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("1")) {
				textFieldSetter = "-1";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("2.")){
				textFieldSetter = "-2.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("2")) {
				textFieldSetter = "-2";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("3.")){
				textFieldSetter = "-3.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("3")) {
				textFieldSetter = "-3";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("4.")){
				textFieldSetter = "-4.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("4")) {
				textFieldSetter = "-4";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("5.")){
				textFieldSetter = "-5.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("5")) {
				textFieldSetter = "-5";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("6.")){
				textFieldSetter = "-6.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("6")) {
				textFieldSetter = "-6";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("7.")){
				textFieldSetter = "-7.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("7")) {
				textFieldSetter = "-7";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("8.")){
				textFieldSetter = "-8.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("8")) {
				textFieldSetter = "-8";
				errorCheck = 0;
			}
			else if (textFieldGetter.equals("9.")){
				textFieldSetter = "-9.";
				errorCheck = 0;
			}
			else if(textFieldGetter.equals("9")) {
				textFieldSetter = "-9";
				errorCheck = 0;
			}			
		}   	
	}
           
    /**
	* Clear button clears the text and the stack.
	*/
    public static void clearButton () {
    	stack.clear();
		textFieldSetter = "";
	}
    
    /**
	* Delete button also checks some conditions as number buttons and deletes the last character according to result.
	* If there is an error on the screen, it does not delete the last screen.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void deleteButton (String textFieldGetter) {
    	if(stack.empty() || textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.") || textFieldGetter.equals("Stack is full.")) {
    		textFieldSetter = "";
		}
		else {		
			if(!stack.isEmpty()) {
				stack.pop();
			}	
			String string = textFieldGetter.replace(",", "");
			textFieldSetter = "";
			for(int i = 0; i < string.length()-1; i++) {
				textFieldSetter = textFieldSetter + string.charAt(i);				
			}
		}   	
	}
     
    /**
	* Head button pushes the number to the stack if it is efficient. While pushing to stack, it deletes the thousand separators.
	* After calculations, thousand separators are again added before showing on the screen.
	* @param textFieldGetter which brings text from screen of calculator.
	* Stack size is limited as 100, so numbers more than that cannot be pushed.
	*/
    public static void headButton (String textFieldGetter) {
    	if(stack.empty() && textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.") || textFieldGetter.equals("Stack is full.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}
    	else if(textFieldGetter.equals("Enter second number.")) {
			textFieldSetter = "Enter second number.";
			errorCheck = 1;
			checker = 1;
		}
    	else if(textFieldGetter.equals("Error, second decimal.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}
		else {
			if(stack.size() == 100) {
				textFieldSetter = "Stack is full.";
				errorCheck = 1;
				checker = 1;
			}
			else {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));			
				String s = textFieldGetter.replace(",", "");
				double a = Double.parseDouble(s);						
				if(a > 999.0) {
					textFieldSetter = String.format(Locale.US, "%,.2f", a);
				}
				else {
					textFieldSetter = String.format(Locale.US, "%,.2f", Double.parseDouble(textFieldGetter));
				}			
				checker = 1;
				isEntered = 1;
			}			
		}   	
	}
    
    /**
	* Addition button sums the numbers after checking conditions.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void additionButton (String textFieldGetter) {
    	if(stack.empty() || textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}	    	
    	else if(textFieldGetter.equals("Stack is full.")) {
    		num2 = stack.pop();
    		num1 = stack.pop();
			result = num1 + num2;
			stack.push(result);
			textFieldSetter = String.valueOf(result);
			checker = 1;
			errorCheck = 0;		
			String textThousandSeparator = textFieldSetter.replace(",", "");
			double textToDouble = Double.parseDouble(textThousandSeparator);
			String.valueOf(textToDouble);
			textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
    	}    	
		else {
			if(checker == 0 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));				
			}	
			if(isEntered == 1 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}
			num2 = stack.pop();			
			if(stack.isEmpty()) {
				textFieldSetter = "Enter second number.";
				errorCheck = 1;
				stack.push(num2);
				checker = 1;
			}			
			else {
				num1 = stack.pop();
				result = num1 + num2;
				stack.push(result);
				textFieldSetter = String.valueOf(result);
				checker = 1;				
				String textThousandSeparator = textFieldSetter.replace(",", "");
				double textToDouble = Double.parseDouble(textThousandSeparator);
				String.valueOf(textToDouble);
				textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
			}									
			isEntered = 0;
			divZeroCheck = 0;
		}    	
	}
  
    /**
	* Subtraction button subtracts the numbers after checking conditions.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void subtractionButton (String textFieldGetter) {
    	if(stack.empty() || textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}    	
    	else if(textFieldGetter.equals("Stack is full.")) {
    		num2 = stack.pop();
    		num1 = stack.pop();
			result = num1 + num2;
			stack.push(result);
			textFieldSetter = String.valueOf(result);
			checker = 1;
			errorCheck = 0;			
			String textThousandSeparator = textFieldSetter.replace(",", "");
			double textToDouble = Double.parseDouble(textThousandSeparator);
			String.valueOf(textToDouble);
			textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
    	}    	
		else {
			if(checker == 0 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}
			if(isEntered == 1 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}
			num2 = stack.pop();			
			if(stack.isEmpty()) {
				textFieldSetter = "Enter second number.";
				errorCheck = 1;
				stack.push(num2);
				checker = 1;
			}
			else {
				num1 = stack.pop();
				result = num1 - num2;
				stack.push(result);
				textFieldSetter = String.valueOf(result);
				checker = 1;
				
				String textThousandSeparator = textFieldSetter.replace(",", "");
				double textToDouble = Double.parseDouble(textThousandSeparator);
				String.valueOf(textToDouble);
				textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
			}												
			isEntered = 0;
			divZeroCheck = 0;
		}    	
	}   
    
    /**
	* Multiplication button multiplies the numbers after checking conditions.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void multiplicationButton (String textFieldGetter) {
    	if(stack.empty() || textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}   	
    	else if(textFieldGetter.equals("Stack is full.")) {
    		num2 = stack.pop();
    		num1 = stack.pop();
			result = num1 + num2;
			stack.push(result);
			textFieldSetter = String.valueOf(result);
			checker = 1;
			errorCheck = 0;			
			String textThousandSeparator = textFieldSetter.replace(",", "");
			double textToDouble = Double.parseDouble(textThousandSeparator);
			String.valueOf(textToDouble);
			textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
    	}    	
		else {
			if(checker == 0 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}	
			if(isEntered == 1 && divZeroCheck == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}
			num2 = stack.pop();			
			if(stack.isEmpty()) {
				textFieldSetter = "Enter second number.";
				errorCheck = 1;
				stack.push(num2);
				checker = 1;
			}
			else {
				num1 = stack.pop();
				result = num1 * num2;
				stack.push(result);
				textFieldSetter = String.valueOf(result);
				checker = 1;				
				String textThousandSeparator = textFieldSetter.replace(",", "");
				double textToDouble = Double.parseDouble(textThousandSeparator);
				String.valueOf(textToDouble);
				textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
			}									
			isEntered = 0;
			divZeroCheck = 0;
		}   	
	}
   
    /**
	* Division button divides the numbers after checking conditions.
	* It especially checks the dividing to 0.
	* @param textFieldGetter which brings text from screen of calculator.
	*/
    public static void divisionButton (String textFieldGetter) {
    	if(stack.empty() || textFieldGetter.equals("") || textFieldGetter.equals("Enter number.") || textFieldGetter.equals("Enter second number.") || textFieldGetter.equals("Error, second decimal.")) {
			textFieldSetter = "Enter number.";
			errorCheck = 1;
			checker = 1;
		}    	
    	else if(textFieldGetter.equals("Stack is full.")) {
    		num2 = stack.pop();
    		num1 = stack.pop();
			result = num1 + num2;
			stack.push(result);
			textFieldSetter = String.valueOf(result);
			checker = 1;
			errorCheck = 0;			
			String textThousandSeparator = textFieldSetter.replace(",", "");
			double textToDouble = Double.parseDouble(textThousandSeparator);
			String.valueOf(textToDouble);
			textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
    	}    	
		else {
			if(checker == 0) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}	
			if(isEntered == 1) {
				stack.push(Double.parseDouble(textFieldGetter.replace(",", "")));
			}
			num2 = stack.pop();			
			if(stack.isEmpty()) {
				textFieldSetter = "Enter second number.";
				errorCheck = 1;
				stack.push(num2);
				checker = 1;
			}
			else {
				num1 = stack.pop();				
				if(num2 == 0.0) {
					textFieldSetter = "Error. Trying to divide 0.";
					errorCheck = 1;
					stack.push(num1);
					stack.push(num2);
					divZeroCheck = 1;
				}
				else {
					result = num1 / num2;
					stack.push(result);
					textFieldSetter = String.valueOf(result);
					checker = 1;					
					String textThousandSeparator = textFieldSetter.replace(",", "");
					double textToDouble = Double.parseDouble(textThousandSeparator);
					String.valueOf(textToDouble);
					textFieldSetter = String.format(Locale.US, "%,.2f", textToDouble);
				}								
			}						
			isEntered = 0;
		}   	
	} 
     
}