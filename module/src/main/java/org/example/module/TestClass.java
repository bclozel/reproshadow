package org.example.module;

import org.example.core.MyGreetingService;
import org.example.shadow.GreetingService;

public class TestClass {

	public void test() {
		MyGreetingService service = new MyGreetingService();
		shouldAcceptGreetingService(service);
	}

	private void shouldAcceptGreetingService(GreetingService greetingService) {

	}
}
