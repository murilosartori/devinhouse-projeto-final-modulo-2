package br.com.devinhouse.grupo5.domain.exceptions;

public class ProcessoNaoEncontradoException extends RuntimeException {

  public ProcessoNaoEncontradoException(String message) {
    super(message);
  }

  public ProcessoNaoEncontradoException(){super("O processo pelo qual buscavas não foi encontrado");}
  
}
