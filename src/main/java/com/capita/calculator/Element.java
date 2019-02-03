/**
 * 
 */
package com.capita.calculator;

import com.capita.exception.CaculatorCustomException;

/**
 * @author SauravChopra
 *
 */
public class Element {
	public static final int UNKNOWN = -1;
	   public static final int NUMBER = 0;
	   public static final int OPERATOR = 1;
	   public static final int LEFT_PARENTHESIS = 2;
	   public static final int RIGHT_PARENTHESIS = 3;
	   
	   private int type;
	   private double value;
	   private char operator;
	   private int precedence;
	   
	   public Element() {
	       type = UNKNOWN;
	   }
	   
	   public Element(String contents) {
	       switch(contents) {
	           case "+":
	               type = OPERATOR;
	               operator = contents.charAt(0);
	               precedence = 1;
	               break;
	           case "-":
	               type = OPERATOR;
	               operator = contents.charAt(0);
	               precedence = 1;
	               break;
	           case "*":
	               type = OPERATOR;
	               operator = contents.charAt(0);
	               precedence = 2;
	               break;
	           case "/":
	               type = OPERATOR;
	               operator = contents.charAt(0);
	               precedence = 2;
	               break;
	           case "^":
	               type = OPERATOR;
	               operator = contents.charAt(0);
	               precedence = 3;
	               break;
	           case "(":
	               type = LEFT_PARENTHESIS;
	               break;
	           case ")":
	               type = RIGHT_PARENTHESIS;
	               break;
	           default:
	               type = NUMBER;
	               try {
	                   value = Double.parseDouble(contents);
	               } catch (Exception ex) {
	                   type = UNKNOWN;
	               }
	       }
	   }
	   
	   public Element(double x) {
	       type = NUMBER;
	       value = x;
	   }
	   
	   int getType() { return type; }
	   double getValue() { return value; }
	   int getPrecedence() { return precedence; }
	   
	   Element operate(double a,double b) throws CaculatorCustomException {
	       double result = 0;
	       switch(operator) {
	           case '+':
	               result = a + b;
	               break;
	           case '-':
	               result = a - b;
	               break;
	           case '*':
	               result = a * b;
	               break;
	           case '/':
	        	   if(b==0){
	        		   throw new CaculatorCustomException("Divide by 0 is not a valid operation !!");
	        	   }else{
	        		   result = a / b;   
	        	   }
	               
	               break;
	           case '^':
	               result = Math.pow(a,b);
	               break;
	       }
	       return new Element(result);
	   }
}
