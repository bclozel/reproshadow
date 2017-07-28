package org.example.core;

import org.library.GreetingService;

public class MyGreetingService implements GreetingService {

	@Override
	public String greet(String name) {
		return "Hello";
	}
}
