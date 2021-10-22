package model;

/**
 * A interface tem apenas um método, porque para atribuirmos uma expressão lambda para uma variável apenas precisamos de
 * saber o tipo do retorno e quais são os parâmetros. Por isso, foram criadas várias interfaces disponibilizadas no SDK
 * do Java 8 que têm apenas um método, e o que varia é apenas o retorno e os parâmetros desse método, para não termos de
 * criar uma interface no nosso programa só para definir o tipo de retorno e os parâmetros de um lambda.
 *
 * <p> <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html">
 * <b>Functional Interface</b> Documentation at Oracle</a> </p>
 * <p>
 * <p>
 * O Java sempre teve como característica precisar de saber tudo sobre o tipo das variáveis, os retornos e parâmetros
 * dos métodos para garantir segurança ao fazer operações com os valores retornados ou chamar métodos sobre objetos.
 * É por isso que foram criadas estas interfaces.
 */
@FunctionalInterface
public interface Greeter {

    void perform();

}
