package units;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class ExceptionHandling {

    public static void main(String[] args) {
        System.out.println("\n### Tratamento de exceções ###");

        ArrayList<Integer> listOfInts = new ArrayList<>();
        Collections.addAll(listOfInts, 1, 2, 3, 4);
        int k = 0; // onde é que apanhamos a exceção, se k for 0?

        BinaryOperator<Integer> action;
        // talvez devêssemos apanhá-la na expressão lambda, onde já conseguimos saber qual é o problema
        action = (Integer element, Integer key) -> element / key;
        // mas isso ia transformar esta expressão lambda bem bonita num monstro, por causa do try/catch

        // ficava qualquer coisa como:
        action = (Integer element, Integer key) -> {
            try {
                return element / key;
            } catch (ArithmeticException ex) {
                System.out.println("Este elemento causou uma exceção aritmética");
                return null;
            }
        };
        // portanto, nada bonito
        action = wrapperLambda((Integer element, Integer key) -> element / key,
                (Exception e) -> System.out.println(e.getMessage()));

        ArrayList<Integer> result = applyKeyToArray(listOfInts, k, action);
        result.forEach(System.out::println);

    }

    /**
     * Para cada elemento da lista dada, é efetuada a operação {@code action},
     * e guardado o resultado na nova lista criada, que depois é retornada
     *
     * @param list   a lista
     * @param key    a chave
     * @param action ação executada utilizando a chave em cada elemento da lista
     * @param <T>    tipo da lista ArrayList
     * @param <K>    tipo da chave
     * @param <R>    tipo de retorno
     * @return uma nova lista com os resultados das operações para cada elemento
     */
    private static <T, K, R> ArrayList<R> applyKeyToArray(ArrayList<T> list, K key, BiFunction<T, K, R> action) {
        ArrayList<R> result = new ArrayList<>();
        list.forEach((T element) -> {
            // podíamos apanhar a exceção aqui, pois é de onde vem a exceção
            R r = action.apply(element, key);
            // mas não seria possível prever o tipo de exceção, porque não conhecemos a operação em action
            result.add(r);
        });
        return result;
    }

    private static BinaryOperator<Integer> wrapperLambda(BinaryOperator<Integer> action, Consumer<Exception> exceptionAction) {
        return (Integer element, Integer key) -> {
            try {
                return action.apply(element, key);
            } catch (Exception e) {
                exceptionAction.accept(e);
                return null;
            }
        };
    }

}
