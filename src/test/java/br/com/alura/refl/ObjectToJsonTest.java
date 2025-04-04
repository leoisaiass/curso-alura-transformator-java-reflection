package br.com.alura.refl;

import br.com.alura.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectToJsonTest {

    Pessoa pessoa = PessoaFixture.buildPessoa();

    @Test
    void shouldTransformObjectToJson() {

        // Arrange
        ObjectToJson objectToJson = new ObjectToJson();

        // Action
        String result = objectToJson.transform(pessoa);

        // Assertion
        Assertions.assertInstanceOf(String.class, result);

    }

    @Test
    void shouldNotTransformObjectToJson() {

        // Arrange
        ObjectToJson objectToJson = new ObjectToJson();
        Object objetoInvalido = null;

        // Assertion + Action
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            objectToJson.transform(objetoInvalido);
        });

    }
}