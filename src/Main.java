import java.util.ArrayList;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
/*
        // o conceito é isto ser parecido com o JavScript ou o Python, onde podemos
        // atribuir funções a variáveis e passá-las por parâmetro a outras funções, ou executá-las

        // o cabeçalho do método é reduzido por o compilador do Java 8 ser melhor
        safeDivideFunction = (int a, int b) -> {
            if (b==0)
                return 0;
            return a / b;
        };

        greetingFunction = () -> System.out.println("Hello");

        doubleNumberFunction = (int a) -> a * 2;

        addFunction = (int a, int b) -> a + b;
*/
        // maneira sem lambdas
        HelloWorldGreeter helloWorldGreeter = new HelloWorldGreeter();
        greet(helloWorldGreeter);

        // instância da interface com uma classe interna anónima
        Greeter greeter1 = new Greeter() {
            // anonymous inner class
            @Override
            public void perform() {
                System.out.println("Instância da interface Greeter por uma classe interna anónima");
            }
        };
        greet(greeter1);

        // implementação com uma expressão lambda
        Greeter greeter2 = () -> System.out.println("Implementação da interface Greeter com uma expressão lambda");
        greet(greeter2);

        /* As interfaces funcionais que já vêm com o Java 8 permitem que não precisemos de criar uma interface só com um
        método sempre que queremos utilizar métodos como variáveis. */
        // exemplos de interfaces funcionais do Java 8
        BiConsumer<String, Integer> printNTimes = (String s, Integer n) -> {
            for (int i = 0; i < n; i++)
                System.out.println(s);
        };
        printNTimes.accept("haha", 3);

        BiFunction<String, Integer, Character> getCharAt = (String s, Integer n) -> s.charAt(n);
        System.out.println(getCharAt.apply("Luís Tovar", 2));

        DoubleToIntFunction metersToCentimeters = (double d) -> {
            double cpy = d * 100;
            return (int) cpy;
        };
        System.out.println(metersToCentimeters.applyAsInt(1.85));

        /* quando temos uma expressão lambda sem argumentos de input e que apenas chama um método sem nenhum argumento
        (ou seja, a expressão lambda é apenas um pass-through), a expressão pode ser substituída por uma method reference
        (Runnable é uma interface funcional para métodos sem input nem output) */
        Runnable callPrintSomething = () -> printSomething();
        // esta expressão lambda pode ser substituída por:
        callPrintSomething = Main::printSomething;
        callPrintSomething.run();
        // a sintaxe consiste em: <objeto ou classe sobre o qual se executa o método> :: <nome do método>
        // method references não têm vantagens significativas, mas é bom sabermos que existem. só fazem o código mais bonito

        /* também é válido usar method references sempre que se tem uma expressão lambda que recebe argumentos, mas o
        conteúdo do lambda apenas envia esses parâmetros como parâmetro de uma função chamada. novamente, quando a
        expressão lambda é apenas um pass-through */
        ObjDoubleConsumer<ArrayList<Double>> multiplyAllElements = (ArrayList<Double> arraylist, double dValue) -> multiplyArrayElements(arraylist, dValue);
        // pode ser substituído por
        multiplyAllElements = Main::multiplyArrayElements;
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        doubleArrayList.add(4.6);
        doubleArrayList.add(5.123);
        doubleArrayList.add(0.543451);
        doubleArrayList.add(20123.476123);
        multiplyAllElements.accept(doubleArrayList, 0.8);
        // outro exemplo
        // o objeto sobre o qual é chamado o método desejado é System.out, uma variável global pública da classe System
        Consumer<String> printString = System.out::println;
        printString.accept("A consumer is printing this");

        // Essencialmente, podem ser usadas method references sempre que não há output na expressão lambda que escrevemos

    }

    public static void greet(Greeter greeter) {
        /* Por o parâmetro ter o tipo Greeter, é garantido que o objeto tem o método perform(). Além disso, são
        conhecidos o tipo de retorno e quais os parâmetros necessários. Isto permite manter a segurança sobre tipos de
        variáveis e de funções que o Java tem sempre. */
        greeter.perform();
    }

    public static void printSomething() {
        System.out.println("Good afternoon, ma'am");
    }

    public static void multiplyArrayElements(ArrayList<Double> arr, double d) {
        arr.forEach((Double element) -> System.out.println(element * d));
    }

}
