package unidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class ExceptionHandling {

	public static void main(String[] args) {
		System.out.println("\n### Exception Handling ###");

		ArrayList<Integer> listOfInts = new ArrayList<>();
		Collections.addAll(listOfInts, 1, 2, 3, 4);
		int k = 0; // where do we catch the exception if k is 0?

		BinaryOperator<Integer> action;
		action = (Integer element, Integer key) -> element / key; // unsafe implementation
		// maybe we should catch it inside of the lambda expression, where we can already know what the problem is.

		// but that would make this expression a monster due to the try/catch clause
		// it would become something like:
		action = (Integer element, Integer key) -> {
			try {
				return element / key;
			} catch (ArithmeticException ex) {
				System.out.println("This element caused an arithmetic exception.");
				return null;
			}
		};
		// doesn't look good like lambdas should

		// this is the mostly recommended implementation
		action = wrapperLambda((Integer element, Integer key) -> element / key,
				(ArithmeticException e) -> System.out.println(e.getMessage()));
		// although of course there can be situations where it's not the best solution

		ArrayList<Integer> result = applyKeyToArray(listOfInts, k, action);
		result.forEach(System.out::println);
	}

	/**
	 * For each element of the given list, the {@code action} operation is executed, and the result is saved
	 * in the newly created list, which is then returned.
	 *
	 * @param action action that applies the key to each element of the list
	 * @param <T>    type of the ArrayList list
	 * @param <K>    type of the key
	 * @param <R>    return type
	 * @return a new list with the result of the operation for each element
	 */
	private static <T, K, R> ArrayList<R> applyKeyToArray(ArrayList<T> list, K key, BiFunction<T, K, R> action) {
		ArrayList<R> result = new ArrayList<>();
		list.forEach((T element) -> {
			// we could catch the exception here, because it's the exception's source
			R r = action.apply(element, key);
			// but it wouldn't be possible to predict the exception type, since we don't know the action operation
			result.add(r);
		});
		return result;
	}

	private static BinaryOperator<Integer> wrapperLambda(BinaryOperator<Integer> action,
	                                                     Consumer<ArithmeticException> exceptionAction) {
		return (Integer element, Integer key) -> {
			try {
				return action.apply(element, key);
			} catch (ArithmeticException e) {
				exceptionAction.accept(e);
				return null;
			}
		};
	}

}
