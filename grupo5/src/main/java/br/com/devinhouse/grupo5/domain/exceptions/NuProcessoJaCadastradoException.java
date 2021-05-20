package br.com.devinhouse.grupo5.domain.exceptions;

public class NuProcessoJaCadastradoException extends RuntimeException{

    public NuProcessoJaCadastradoException(String mensagem){
        super(mensagem);
    }

    public NuProcessoJaCadastradoException(){super("O número de processo informado já encontra-se cadastrado.");    }

}
