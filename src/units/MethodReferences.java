package units;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ObjDoubleConsumer;

public class MethodReferences {

    public static void main(String[] args) {
        System.out.println("\n### Method References ###");

        /* quando temos uma expressão lambda que apenas chama um método (ou seja, a expressão lambda é apenas um
        pass-through), a expressão pode ser substituída por uma method reference. method references não têm vantagens
        significativas, mas é bom sabermos que existem. basicamente fazem o código mais bonito
        a sintaxe consiste em: <objeto ou classe sobre o qual se executa o método> :: <nome do método> */

        // neste caso, chama-se um método que não recebe nenhum input nem retorna nenhum output
        Runnable callPrintSomething = () -> printSomething();
        // esta expressão lambda pode ser substituída por:
        callPrintSomething = MethodReferences::printSomething;
        callPrintSomething.run();

        // neste caso, chama-se um método que recebe input mas não retorna output
        // o objeto sobre o qual é chamado o método desejado é System.out, uma variável global pública da classe System
        Consumer<String> printString = (String s) -> System.out.println(s);
        printString = System.out::println;
        printString.accept("A consumer is printing this through a method reference of System.out.println");
        // outro exemplo com input sem output
        ObjDoubleConsumer<ArrayList<Double>> multiplyAllElements = (ArrayList<Double> arraylist, double dValue) -> multiplyArrayElements(arraylist, dValue);
        // pode ser substituído por
        multiplyAllElements = MethodReferences::multiplyArrayElements;
        // vamos testá-lo
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        doubleArrayList.add(4.6);
        doubleArrayList.add(5.123);
        doubleArrayList.add(0.543451);
        doubleArrayList.add(20123.476123);
        multiplyAllElements.accept(doubleArrayList, 0.8);

        // neste caso há input e output no método chamado
        Function<ArrayList<String>, String> getLastElement = (ArrayList<String> arrayList) -> lastElement(arrayList);
        getLastElement = MethodReferences::lastElement;

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
