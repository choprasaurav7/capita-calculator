package com.capita.calculator;
import java.util.ArrayList;

/**
 * @author SauravChopra
 *
 */
public class ElementStack {
	
	private ArrayList<Element> Elements;

	public ElementStack() {
		Elements = new ArrayList<Element>();
	}

	public boolean isEmpty() {
		return Elements.size() == 0;
	}

	public Element top() {
		return Elements.get(Elements.size() - 1);
	}

	public void push(Element t) {
		Elements.add(t);
	}

	public void pop() {
		Elements.remove(Elements.size() - 1);
	}
}
