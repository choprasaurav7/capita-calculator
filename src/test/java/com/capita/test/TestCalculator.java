package com.capita.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capita.calculator.Calculator;
import com.capita.exception.CaculatorCustomException;

public class TestCalculator {
	private Calculator processCalculator;

	@Before
	public void setUp() {
		processCalculator = new Calculator();
	}

	@After
	public void afterEachTest() {
		System.out.println("----Completed Test---");
	}
	
	@Test
    public void testBracket() {
		try {
		String s =	processCalculator.processInput("1+2+ (4*5) + 4/3 )");
		assertEquals("INVALID EXPRESSION", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	
	@Test
    public void testEmptyBracket() {
		try {
		String s =	processCalculator.processInput("1+2+ (4*5) + 4/3 -()");
		assertEquals("INVALID EXPRESSION", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	
	@Test(expected = CaculatorCustomException.class)
    public void divideByZero() {
		try {
		processCalculator.processInput("2/0");
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	
	@Test
    public void testExpression1() {
		try {
		String s =	processCalculator.processInput("7+(6*5^2+3-4/2)");
		assertEquals("158.0", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	@Test
    public void testExpression2() {
		try {
		String s =	processCalculator.processInput("7+(67(56*2))");
		assertEquals("INVALID EXPRESSION", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	@Test
    public void testExpression3() {
		try {
		String s =	processCalculator.processInput("8*+7");
		assertEquals("INVALID EXPRESSION", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
	@Test
    public void testExpression4() {
		try {
		String s =	processCalculator.processInput("(8*5/8)-(3/1)-5");
		assertEquals("-3.0", s);
		} catch (CaculatorCustomException e) {
			e.printStackTrace();
		}
    }
}
