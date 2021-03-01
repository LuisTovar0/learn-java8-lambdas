package units;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.ObjDoubleConsumer;

public class MethodReferences {

    public static void main(String[] args) {
        System.out.println("\n### Exemplos Method References ###");

        /* quando temos uma expressão lambda sem argumentos de input e que apenas chama um método sem nenhum argumento
        (ou seja, a expressão lambda é apenas um pass-through), a expressão pode ser substituída por uma method reference
        (Runnable é uma interface funcional para métodos sem input nem output) */
        Runnable callPrintSomething = () -> printSomething();
        // esta expressão lambda pode ser substituída por:
        callPrintSomething = MethodReferences::printSomething;
        callPrintSomething.run();
        // a sintaxe consiste em: <objeto ou classe sobre o qual se executa o método> :: <nome do método>
        // method references não têm vantagens significativas, mas é bom sabermos que existem. só fazem o código mais bonito

        /* também é válido usar method references sempre que se tem uma expressão lambda que recebe argumentos, mas o
        conteúdo do lambda apenas envia esses parâmetros como parâmetro de uma função chamada. novamente, quando a
        expressão lambda é apenas um pass-through */
        ObjDoubleConsumer<ArrayList<Double>> multiplyAllElements = (ArrayList<Double> arraylist, double dValue) -> multiplyArrayElements(arraylist, dValue);
        // pode ser substituído por
        multiplyAllElements = MethodReferences::multiplyArrayElements;
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        doubleArrayList.add(4.6);
        doubleArrayList.add(5.123);
        doubleArrayList.add(0.543451);
        doubleArrayList.add(20123.476123);
        multiplyAllElements.accept(doubleArrayList, 0.8);
        // outro exemplo
        // o objeto sobre o qual é chamado o método desejado é System.out, uma variável global pública da classe System
        Consumer<String> printString = System.out::println;
        printString.accept("A consumer is printing this through a method reference of System.out.println");

        // Essencialmente, podem ser usadas method references sempre que não há output na expressão lambda que escrevemos

    }

    public static void printSomething() {
        System.out.println("A Runnable is printing this through a method reference");
    }

    public static void multiplyArrayElements(ArrayList<Double> arr, double d) {
        arr.forEach((Double element) -> System.out.println(element * d));
    }

}
