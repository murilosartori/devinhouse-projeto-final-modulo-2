package br.com.devinhouse.grupo5.exceptions;

public class ProcessoNaoEncontradoException extends RuntimeException {

    public ProcessoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
