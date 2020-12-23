package JUnitPackage;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;

public class JUnitTest {
	
	String textToSet = "";
		
	@Test
	public void test_main() {
		
		try {
			String text1 = "2^3+";
			test_whole(text1);
			assertEquals("5.00", textToSet);
			
			String text2 = "2^3-";
			test_whole(text2);
			assertEquals("-1.00", textToSet);
			
			String text3 = "2^3^4++";
			test_whole(text3);
			assertEquals("9.00", textToSet);
			
			String text4 = "2^3^4+-";
			test_whole(text4);
			assertEquals("-5.00", textToSet);
			
			String text5 = "2^3^5+*";
			test_whole(text5);
			assertEquals("16.00", textToSet);
			
			String text6 = "2^3^5*+";
			test_whole(text6);
			assertEquals("17.00", textToSet);
			
			String text7 = "1^2+3+4+";
			test_whole(text7);
			assertEquals("10.00", textToSet);
			
			String text8 = "2c^3c-";
			test_whole(text8);
			assertEquals("1.00", textToSet);
			
			String text9 = "+=";
			test_whole(text9);
			assertEquals("", textToSet);
			
			String text10 = "+2=";
			test_whole(text10);
			assertEquals("", textToSet);
			
			String text11 = "1-2+=";
			test_whole(text11);
			assertEquals("", textToSet);
			
			String text12 = "1.2c";
			test_whole(text12);
			assertEquals("-1.2", textToSet);
						
			String text13 = "1,111^1+";
			test_whole(text13);
			assertEquals("1,112.00", textToSet);
			
			String text14 = "aaa";
			test_whole(text14);
			assertEquals("", textToSet);
			
			String text15 = "1,345^1^1c+-";
			test_whole(text15);
			assertEquals("1,345.00", textToSet);
			
			String text16 = "10.60^4*";
			test_whole(text16);
			assertEquals("42.40", textToSet);
			
			String text17 = "111111^111111-";
			test_whole(text17);
			assertEquals("0.00", textToSet);
			
			String text18 = "111111^111111c-";
			test_whole(text18);
			assertEquals("222,222.00", textToSet);
			
			String text19 = "1^1^10^3^0^1.2";
			test_whole(text19);
			assertEquals("1.2", textToSet);
			
			String text20 = "1^0/";
			test_whole(text20);
			assertEquals("Error. Trying to divide 0.", textToSet);		
			
		}
		catch (Throwable err) {
            System.out.println(err.getMessage());
        }		
	}
	
	public void test_whole(String text) throws IOException {
		
		try {	
			/*
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please write the input that you want to test: ");
	        String text = reader.readLine();    
	        */
			
			textToSet = "";
			
	        char[] textChar = text.toCharArray();
	        
	        for (char charAppend : textChar) { 
	        	
	        	if(charAppend != '^' && charAppend != '+' && charAppend != '-' && charAppend != '*' && charAppend != '/' && charAppend != '.' && charAppend != 'c' && charAppend != ',') {
	        		if(charAppend == '0' || charAppend == '1' || charAppend == '2' || charAppend == '3' || charAppend == '4' || charAppend == '5' || charAppend == '6' || charAppend == '7' || charAppend == '8' || charAppend == '9') {
	        			Calculator.RPN_Engine.numberButtons (Integer.parseInt(Character.toString(charAppend)), textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        		else {
	        			System.out.println("Invalid input. Only numbers can be calculated with operators.");
	        			textToSet = "";
	        			break;
	        		}
	        	}   
	        	else if(charAppend == '.') {
	        		Calculator.RPN_Engine.decimalButton (textToSet);
	        		textToSet = Calculator.RPN_Engine.textFieldSetter;
	        	}
	        	else if(charAppend == 'c') {        		
	        		Calculator.RPN_Engine.changeSignButton (textToSet);
        			textToSet = Calculator.RPN_Engine.textFieldSetter;       		       		
	        	}
	        	else if(charAppend == ',') {        		
	        		
	        	}
	        	else if(charAppend == '^' || charAppend == '+' || charAppend == '-' || charAppend == '*' || charAppend == '/') {
	        		if(charAppend == '^') {
	        			Calculator.RPN_Engine.headButton (textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        		else if(charAppend == '+') {
	        			Calculator.RPN_Engine.additionButton (textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        		else if(charAppend == '-') {
	        			Calculator.RPN_Engine.subtractionButton (textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        		else if(charAppend == '*') {
	        			Calculator.RPN_Engine.multiplicationButton (textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        		else if(charAppend == '/') {
	        			Calculator.RPN_Engine.divisionButton (textToSet);
	        			textToSet = Calculator.RPN_Engine.textFieldSetter;
	        		}
	        	}       	
	        	else {
	        		System.out.println("Invalid input.");
	        		textToSet = "Invalid input.";
	        	}      
	        }  
	        System.out.println("Input tested is: " + text);
	        System.out.println("Result is: " + textToSet);	        	        
        } 
		catch (Throwable err) {
            System.out.println(err.getMessage());
        }		
	}
		
}