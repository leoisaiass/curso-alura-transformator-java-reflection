package br.com.alura.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// Classe responsável por transformar objetos de um tipo em objetos DTO correspondentes.
public class Transformator {

    // Metodo que transforma um objeto de entrada num objeto DTO correspondente.
    // @param "input" Objeto de entrada que será transformado.
    // @return "output" Um objeto do tipo DTO correspondente ao objeto de entrada.
    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        // Obtém a classe do objeto de entrada.
        Class<?> source = input.getClass();

        // Aqui, concatenamos "DTO" ao nome da classe de entrada para formar o nome da classe DTO (Define a classe DTO correspondente.).
        // Lança ClassNotFoundException se a classe DTO correspondente não for encontrada.
        Class<?> target = Class.forName(source + "DTO");

        // Instancia a classe de destino usando o construtor padrão.
        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        // Pega todos os atributos disponíveis nas classes
        Field[] sourcesFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        // Retorna a instância da classe DTO, que agora pode ser preenchida com os dados transformados.
        return targetClass;

    }

}
