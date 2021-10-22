package model;

/**
 * The interface only has one method, since in order to assign a lambda expression to a variable we just need the
 * interface to know the return type and the parameters. With that in mind, there are multiple interfaces available
 * in Java 8 SDKs that only have one method, but with different return types and parameters, so we don't have to
 * create a new interface every time just to define our return type and parameters.
 * A interface tem apenas um método, porque para passarmos uma expressão lambda para uma variável apenas precisamos de
 * saber o tipo do retorno e quais são os parâmetros. Por isso, foram criadas várias interfaces disponibilizadas no
 * Java 8 que têm apenas um método, e o que varia é apenas o retorno e os parâmetros desse método.
 *
 * <p> <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">
 * <b>Functional Interface</b> Documentation at Oracle</a> </p>
 * <p>
 * <p>
 * Java's always had the characteristic of needing to know the types of variables, returns and method parameters in
 * order to ensure safety on operations with returned values or method calls upon objects. That's the purpose of these
 * interfaces.
 */
@FunctionalInterface
public interface Greeter {

    void perform();

}
