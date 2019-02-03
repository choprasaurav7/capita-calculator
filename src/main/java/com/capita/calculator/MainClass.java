/**
 * 
 */
package com.capita.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.capita.exception.CaculatorCustomException;

/**
 * @author SauravChopra
 *
 */
public class MainClass {
	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		try {
			int input = Integer.parseInt(in.readLine());
			String cases[] = new String[input];
			int i = 0;
			while ((i<input) && (s = in.readLine()) != null) {
				cases[i] = s;
				i++;
			}
			Calculator calculator;
			for (int j = 0; j < cases.length; j++) {
				String string = cases[j];
				calculator = new Calculator();
				String processInput = calculator.processInput(string);
				System.out.println("CASE #"+(j+1)+": "+processInput);
			}
		} catch (IOException e) {
			System.out.println("Error occurred while reading input");
		} catch (CaculatorCustomException e) {
			System.out.println("Error occurred while calculating" + e.getMessage());
		}
	}

}