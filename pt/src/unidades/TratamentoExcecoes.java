package unidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class TratamentoExcecoes {

	public static void main(String[] args) {
		System.out.println("\n### Tratamento de exceções ###");

		ArrayList<Integer> listaInt = new ArrayList<>();
		Collections.addAll(listaInt, 1, 2, 3, 4);
		int k = 0; // onde é que apanhamos a exceção, se k for 0?

		BinaryOperator<Integer> acao;
		acao = (Integer elemento, Integer chave) -> elemento / chave;
		// talvez devêssemos apanhá-la na expressão lambda, onde já conseguimos saber qual é o problema

		// mas isso ia transformar esta bela expressão lambda num monstro, por causa do try/catch
		// ficava qualquer coisa como:
		acao = (Integer elemento, Integer chave) -> {
			try {
				return elemento / chave;
			} catch (ArithmeticException ex) {
				System.out.println("Este elemento causou uma exceção aritmética");
				return null;
			}
		};
		// portanto, nada bonito

		// a solução sugerida é
		acao = lambdaEnvolvente(
				(Integer elemento, Integer chave) -> elemento / chave,
				(ArithmeticException excecao) -> System.out.println(excecao.getMessage())
		);
		// apesar de haver, como sempre, situações em que não será a melhor solução

		ArrayList<Integer> resultado = aplicarChaveAoArray(listaInt, k, acao);
		resultado.forEach(System.out::println);
	}

	/**
	 * Para cada elemento da lista dada, é efetuada a operação {@code acao},
	 * e guardado o resultado na nova lista criada, que é depois retornada.
	 *
	 * @param acao ação executada utilizando a chave em cada elemento da lista
	 * @param <T>  tipo da lista ArrayList
	 * @param <K>  tipo da chave
	 * @param <R>  tipo de retorno
	 * @return uma nova lista com os resultados das operações para cada elemento
	 */
	private static <T, K, R> ArrayList<R> aplicarChaveAoArray(ArrayList<T> lista, K chave, BiFunction<T, K, R> acao) {
		ArrayList<R> resultado = new ArrayList<>();
		lista.forEach((T elemento) -> {
			// podíamos apanhar a exceção aqui, pois é de onde vem a exceção
			R r = acao.apply(elemento, chave);
			// mas não seria possível prever o tipo de exceção, porque não conhecemos a operação em acao
			resultado.add(r);
		});
		return resultado;
	}

	private static BinaryOperator<Integer> lambdaEnvolvente(BinaryOperator<Integer> acao,
	                                                        Consumer<ArithmeticException> acaoExcecao) {
		return (Integer elemento, Integer chave) -> {
			try {
				return acao.apply(elemento, chave);
			} catch (ArithmeticException e) {
				acaoExcecao.accept(e);
				return null;
			}
		};
	}
}
