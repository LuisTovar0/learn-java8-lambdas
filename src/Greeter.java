/**
 * A interface tem apenas um método, porque para passarmos uma expressão lambda para uma variável apenas precisamos de
 * saber o tipo do retorno e quais são os parâmetros. Por isso, foram criadas várias interfaces disponibilizadas no
 * Java 8 que têm apenas um método, e o que varia é apenas o retorno e os parâmetros desse método.
 *
 * <p>Documentação: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html</p>
 * <p>
 * O Java sempre teve como característica precisar de informações específicas sobre o tipo das variáveis, os retornos
 * e parâmetros dos métodos, para garantir segurança ao fazer operações com os valores retornados ou chamar métodos
 * sobre instâncias. É por isso que foram criadas estas interfaces.
 */
@FunctionalInterface
public interface Greeter {

    void perform();

}
