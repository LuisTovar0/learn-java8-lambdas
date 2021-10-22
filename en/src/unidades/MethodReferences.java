package unidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;

public class MethodReferences {

	public static void main(String[] args) {
		System.out.println("\n### Method References ###");

        /* when we have a lambda expression that all it does is call a method (meaning the lambda expression is just
        a pass-through), the lambda may be replaced by a method reference. they don't have real advantages asides from
        looking good, but we should know how they work so we're not confused when we find one in someone else's code.
        it's also a good flex. the syntax is:
            <object/class upon which the method is executed>::<method name>
        when the method belongs to an object, the left side of the :: is a reference to that object. if it is a static
        method, the left side is a reference to that class */

		// in this case, we call a method that doesn't have input nor output
		Runnable callPrintSomething = () -> printSomething();
		// this lambda can be replaced by:
		callPrintSomething = MethodReferences::printSomething;
		callPrintSomething.run();

		// in this case we call a method that has input but no output
		// the object upon which the method is called is System.out, a public global variable of the System class
		Consumer<String> printString = (String s) -> System.out.println(s);
		printString = System.out::println;
		printString.accept("A consumer is printing this through a method reference of System.out.println");
		// another example with input but no output
		ObjDoubleConsumer<ArrayList<Double>> multiplyAllElements =
				(ArrayList<Double> arraylist, double dValue) -> multiplyArrayElements(arraylist, dValue);
		// it can be replaced by:
		multiplyAllElements = MethodReferences::multiplyArrayElements;
		// let's test it
		ArrayList<Double> doubleArrayList = new ArrayList<>();
		Collections.addAll(doubleArrayList, 4.6, 5.123, 0.543451, 20123.476123);
		multiplyAllElements.accept(doubleArrayList, 0.8);

		// in this case there is input and output in the called method
		Function<ArrayList<String>, String> getLastElement =
				(ArrayList<String> arrayList) -> lastElement(arrayList);
		getLastElement = MethodReferences::lastElement;
		ArrayList<String> stringArrayList = new ArrayList<>();
		Collections.addAll(stringArrayList, "Manuel", "Luís", "Power");
		System.out.println(getLastElement.apply(stringArrayList));
	}

	public static void printSomething() {
		System.out.println("A Runnable is printing this through a method reference");
	}

	public static void multiplyArrayElements(ArrayList<Double> arr, double d) {
		arr.forEach((Double element) -> System.out.println(element * d));
	}

	private static String lastElement(ArrayList<String> strings) {
		return strings.get(strings.size() - 1);
	}

}
