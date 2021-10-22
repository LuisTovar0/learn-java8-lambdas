package unidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;

public class ReferenciasDeMetodo {

	public static void main(String[] args) {
		System.out.println("\n### Referências de método ###");

        /* quando temos uma expressão lambda que apenas chama um método (ou seja, a expressão lambda é apenas um
        pass-through), a expressão pode ser substituída por uma referência de método. referências de método não têm
        vantagens significativas além de ficarem bonitas no código, mas devemos saber como funcionam para não ficarmos
        confusos quando encontrarmos uma no código dos outros. por falar nisso, também são um flex fixe. a sintaxe é:
            <objeto ou classe sobre o qual se executa o método>::<nome do método>
        quando o método é de um objeto, o lado esquerdo do :: é uma referência a esse objeto. se ele for um método
        estático, o lado esaquerdo é uma referência à classe */

		// neste caso, chama-se um método que não recebe nenhum input nem retorna nenhum output
		Runnable chamaImprimeAlgo = () -> imprimeAlgo();
		// este lambda pode ser substituído por:
		chamaImprimeAlgo = ReferenciasDeMetodo::imprimeAlgo;
		chamaImprimeAlgo.run();

		// neste caso, chama-se um método que recebe input mas não retorna output
		// o objeto sobre o qual é chamado o método é System.out, uma variável global pública da classe System
		Consumer<String> imprimeString = (String s) -> System.out.println(s);
		imprimeString = System.out::println;
		imprimeString.accept("Um Consumer está a imprimir isto através de uma referência ao método System.out.println");
		// outro exemplo com input sem output
		ObjDoubleConsumer<ArrayList<Double>> multiplicaTodosOsElementos =
				(ArrayList<Double> lista, double dNum) -> multiplicaElementosLista(lista, dNum);
		// pode ser substituído por
		multiplicaTodosOsElementos = ReferenciasDeMetodo::multiplicaElementosLista;
		// vamos testá-lo
		ArrayList<Double> listaDouble = new ArrayList<>();
		Collections.addAll(listaDouble, 4.6, 5.123, 0.543451, 20123.476123);
		multiplicaTodosOsElementos.accept(listaDouble, 0.8);

		// neste caso há input e output no método chamado
		Function<ArrayList<String>, String> getUltimoElemento =
				(ArrayList<String> lista) -> ultimoElemento(lista);
		getUltimoElemento = ReferenciasDeMetodo::ultimoElemento;
		ArrayList<String> listaString = new ArrayList<>();
		Collections.addAll(listaString, "Manuel", "Luís", "Power");
		System.out.println(getUltimoElemento.apply(listaString));

	}

	public static void imprimeAlgo() {
		System.out.println("Um Runnable está a imprimir isto através de uma referência de método.");
	}

	public static void multiplicaElementosLista(ArrayList<Double> lista, double d) {
		lista.forEach((Double elemento) -> System.out.println(elemento * d));
	}

	private static String ultimoElemento(ArrayList<String> strings) {
		return strings.get(strings.size() - 1);
	}

}
