package br.com.alura.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

// Classe responsável por transformar objetos de um tipo em objetos DTO correspondentes.
public class Transformator {

    // Method que transforma um objeto de entrada num objeto DTO correspondente.
    // @param "input" Objeto de entrada que será transformado.
    // @return "output" Um objeto do tipo DTO correspondente ao objeto de entrada.
    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        // Obtém a classe do objeto de entrada.
        Class<?> source = input.getClass(); // Neste exemplo, receberá a classe Pessoa

        // Aqui, concatenamos "DTO" ao nome da classe de entrada para formar o nome da classe DTO (Define a classe DTO correspondente).
        // Lança ClassNotFoundException se a classe DTO correspondente não for encontrada.
        Class<?> target = Class.forName(source.getName() + "DTO"); // Neste exemplo, receberá a classe PessoaDTO

        // Instancia a classe de destino usando o construtor padrão.
        O targetClass = (O) target.getDeclaredConstructor().newInstance(); // Neste caso será então PessoaDTO(String nome, String cpf)

        // Pega todos os atributos disponíveis nas classes
        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        // Converte o array de campos de origem num fluxo para iteração
        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {
                            // Realiza a validação se o campo de origem é o mesmo do destino
                            validate(sourceField, targetField);
                            try {
                                // Define o valor do campo de origem no campo de destino
                                targetField.set(targetClass, sourceField.get(input));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );

        // Retorna a instância da classe DTO, que agora pode ser preenchida com os dados transformados.
        return targetClass;

    }

    private void validate(Field sourceField, Field targetField) {
        // Verifica se o nome dos campos e os tipos são os mesmos
        if (sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())) {

            // Alterando os modificadores de acesso de privado para acessíveis (não é tão recomendado numa aplicação real)
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
        }
    }

}
