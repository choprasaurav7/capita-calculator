package com.capita.calculator;

import com.capita.exception.CaculatorCustomException;

/**
 * @author SauravChopra
 *
 */
public class Calculator {
	private ElementStack operatorStack;
	private ElementStack valueStack;
	private boolean error;

	public Calculator() {
        operatorStack = new ElementStack();
        valueStack = new ElementStack();
        error = false;
    }

	private void processOperator(Element t) throws CaculatorCustomException {
		Element A = null, B = null;
		if (valueStack.isEmpty()) {
			System.out.println("Expression error.");
			error = true;
			return;
		} else {
			B = valueStack.top();
			valueStack.pop();
		}
		if (valueStack.isEmpty()) {
			System.out.println("Expression error.");
			error = true;
			return;
		} else {
			A = valueStack.top();
			valueStack.pop();
		}
		Element R = t.operate(A.getValue(), B.getValue());
		valueStack.push(R);
	}

	public String processInput(String input) throws CaculatorCustomException {
		// The Elements that make up the input
		String[] parts = input.split("");
		Element[] Elements = new Element[parts.length];
		for (int n = 0; n < parts.length; n++) {
			Elements[n] = new Element(parts[n]);
		}

		// Main loop - process all input Elements
		for (int n = 0; n < Elements.length; n++) {
			Element nextElement = Elements[n];
			if (nextElement.getType() == Element.NUMBER) {
				valueStack.push(nextElement);
			} else if (nextElement.getType() == Element.OPERATOR) {
				if (operatorStack.isEmpty() || nextElement.getPrecedence() > operatorStack.top().getPrecedence()) {
					operatorStack.push(nextElement);
				} else {
					while (!operatorStack.isEmpty()
							&& nextElement.getPrecedence() <= operatorStack.top().getPrecedence()) {
						Element toProcess = operatorStack.top();
						operatorStack.pop();
						processOperator(toProcess);
					}
					operatorStack.push(nextElement);
				}
			} else if (nextElement.getType() == Element.LEFT_PARENTHESIS) {
				operatorStack.push(nextElement);
			} else if (nextElement.getType() == Element.RIGHT_PARENTHESIS) {
				while (!operatorStack.isEmpty() && operatorStack.top().getType() == Element.OPERATOR) {
					Element toProcess = operatorStack.top();
					operatorStack.pop();
					processOperator(toProcess);
				}
				if (!operatorStack.isEmpty() && operatorStack.top().getType() == Element.LEFT_PARENTHESIS) {
					operatorStack.pop();
				} else {
					return "INVALID EXPRESSION";
				}
			}

		}
		// Empty out the operator stack at the end of the input
		while (!operatorStack.isEmpty() && operatorStack.top().getType() == Element.OPERATOR) {
			Element toProcess = operatorStack.top();
			operatorStack.pop();
			processOperator(toProcess);
		}
		// Print the result if no error has been seen.
		if (error == false) {
			Element result = valueStack.top();
			valueStack.pop();
			if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
				return "INVALID EXPRESSION";
			} else {
				return ""+result.getValue();
			}
		}
		return "INVALID EXPRESSION";
	}

}
