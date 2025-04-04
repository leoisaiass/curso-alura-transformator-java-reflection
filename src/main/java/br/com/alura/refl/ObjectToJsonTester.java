package br.com.alura.refl;

import br.com.alura.Pessoa;

public class ObjectToJsonTester {

    public static void main(String[] args) {
        var pessoa = new Pessoa(1, "Maria", "987.765.432-10");
        var objectToJson = new ObjectToJson();
        System.out.println(objectToJson.transform(pessoa));
    }

}
