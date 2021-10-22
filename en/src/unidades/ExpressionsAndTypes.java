package unidades;

import model.Greeter;
import model.HelloWorldGreeter;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.DoubleToIntFunction;

public class ExpressionsAndTypes {

	public static void main(String[] args) {
		System.out.println("\n### Expressões e Tipos ###");

/*
		// the idea of lambdas is trying to mimic languages like JavaScript or Python, where it is possible
		// to assign functions to variables, and then pass them as parameters to other functions, or execute them

		// the header of the method we want to assign to the variable is reduced to just the parameters (meaning
		// it can deduce the missing information) due to improvements on the Java 8 compiler
        safeDivideFunction = (int a, int b) -> {
            if (b==0)
                return 0;
            return a / b;
        };

        greetingFunction = () -> System.out.println("Hello");

        doubleNumberFunction = (int a) -> a * 2;

        addFunction = (int a, int b) -> a + b;
*/

		// problem: printing "hello world" using the Greeter interface

		// solving it without lambdas
		HelloWorldGreeter helloWorldGreeter = new HelloWorldGreeter();
		greet(helloWorldGreeter);

		// instance of the interface, instantiated with an anonymous inner class
		Greeter greeter1 = new Greeter() {
			// anonymous inner class
			@Override
			public void perform() {
				System.out.println("Instance of the Greeter interface by an anonymous inner class.");
			}
		};
		greet(greeter1);

		// implementation with a lambda expression
		Greeter greeter2 = () -> System.out.println("Implementation of the Greeter interface with a lambda expression.");
		greet(greeter2);

		/* the functional interfaces that come with the Java 8 SDK make it possible for us not to have to create a new
		custom interface with just one method every time we want to use methods as variables */
		// examples of functional interfaces in Java 8
		BiConsumer<String, Integer> printNTimes = (String s, Integer n) -> {
			for (int i = 0; i < n; i++)
				System.out.println(s);
		};
		printNTimes.accept("I'm getting printed 3 times.", 3);

		BiFunction<String, Integer, Character> getCharAt = (String s, Integer n) -> s.charAt(n);
		System.out.println(getCharAt.apply("Luís Tovar", 2));

		DoubleToIntFunction metersToCentimeters = (double d) -> {
			double cpy = d * 100;
			return (int) cpy;
		};
		System.out.println(metersToCentimeters.applyAsInt(1.85));
	}

	public static void greet(Greeter greeter) {
		/* Since the parameter has the Greeter type, it is certain that the object has the perform() method. Besides,
		the return type and the needed parameters are known. This allows safety about types of variables and functions,
		something Java has always displayed. */
		greeter.perform();
	}

}
