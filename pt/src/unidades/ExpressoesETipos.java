package unidades;

import model.Greeter;
import model.GreeterOlaMundo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.DoubleToIntFunction;

public class ExpressoesETipos {

    public static void main(String[] args) {
        System.out.println("\n### Expressões e Tipos ###");

/*
        // a ideia principal é os lambdas tentarem imitar o JavScript ou o Python, onde podemos atribuir funções
        // a variáveis, e depois passá-las por parâmetro a outras funções, ou executá-las

        // o cabeçalho do método que queremos atribuir à variável é reduzido apenas aos parâmetros (o que significa
        // que consegue deduzir informação ausente) por o compilador do Java 8 ser melhor
        safeDivideFunction = (int a, int b) -> {
            if (b==0)
                return 0;
            return a / b;
        };

        greetingFunction = () -> System.out.println("Hello");

        doubleNumberFunction = (int a) -> a * 2;

        addFunction = (int a, int b) -> a + b;
*/
        // Problema: imprimir "olá mundo" utilizando a interface Greeter

        // maneira sem lambdas
        GreeterOlaMundo greeterOlaMundo = new GreeterOlaMundo();
        cumprimenta(greeterOlaMundo);

        // instância da interface, criada com uma classe interna anónima
        Greeter greeter1 = new Greeter() {
            // anonymous inner class
            @Override
            public void perform() {
                System.out.println("Instância da interface Greeter por uma classe interna anónima");
            }
        };
        cumprimenta(greeter1);

        // implementação com uma expressão lambda
        Greeter greeter2 = () -> System.out.println("Implementação da interface Greeter com uma expressão lambda");
        cumprimenta(greeter2);

        /* As interfaces funcionais que já vêm com o SDK Java 8 permitem que não precisemos de criar uma interface só com
        um método sempre que queremos utilizar métodos como variáveis. */
        // exemplos de interfaces funcionais do Java 8
        BiConsumer<String, Integer> imprimeNVezes = (String s, Integer n) -> {
            for (int i = 0; i < n; i++)
                System.out.println(s);
        };
        imprimeNVezes.accept("Sou imprimido 3 vezes.", 3);

        BiFunction<String, Integer, Character> getCharAt = (String s, Integer n) -> s.charAt(n);
        System.out.println(getCharAt.apply("Luís Tovar", 2));

        DoubleToIntFunction metrosParaCentimetros = (double d) -> {
            double cpy = d * 100;
            return (int) cpy;
        };
        System.out.println(metrosParaCentimetros.applyAsInt(1.85));

    }

    public static void cumprimenta(Greeter greeter) {
        /* Por o parâmetro ter o tipo Greeter, é garantido que o objeto tem o método perform(). Além disso, são
        conhecidos o tipo de retorno e quais os parâmetros necessários. Isto permite manter a segurança sobre tipos de
        variáveis e de funções que o Java tem sempre. */
        greeter.perform();
    }

}
