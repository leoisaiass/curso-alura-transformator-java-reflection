package br.com.alura.refl;

import br.com.alura.Endereco;
import br.com.alura.Pessoa;
import br.com.alura.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = PessoaFixture.buildPessoa();
    Endereco endereco = new Endereco("Rua Aurora", 123);

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        // Arrange
        Transformator transformator = new Transformator();

        // Action
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        // Assertion
        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());

    }

    @Test
    public void shouldNotTransform() {

        // Assertion
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            // Arrange
            Transformator transformator = new Transformator();

            // Action
            transformator.transform(endereco);
        });

    }

    @Test
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        // Arrange
        Pessoa pessoaSemCPF = new Pessoa("Leonardo");
        Transformator transformator = new Transformator();

        // Action
        PessoaDTO pessoaDTOSemCPF = transformator.transform(pessoaSemCPF);

        // Assertion
        Assertions.assertEquals(pessoaSemCPF.getNome(), pessoaDTOSemCPF.getNome());
        Assertions.assertNull(pessoaDTOSemCPF.getCpf());

    }


}
