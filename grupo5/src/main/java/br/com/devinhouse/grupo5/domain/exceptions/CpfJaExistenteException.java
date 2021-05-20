package br.com.devinhouse.grupo5.domain.exceptions;

public class CpfJaExistenteException extends RuntimeException {

  public CpfJaExistenteException(String message) {
    //TODO: ao invés de receber uma mensagem, só receber o CPF e deixar a mensagem padrão
      super(message);
  }
}
