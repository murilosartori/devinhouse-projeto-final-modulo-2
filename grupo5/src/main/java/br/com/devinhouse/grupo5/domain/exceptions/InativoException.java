package br.com.devinhouse.grupo5.domain.exceptions;

public class InativoException extends RuntimeException {

  public InativoException(String mensagem) {
    super(mensagem);
  }

  public InativoException() {
    super("O interessado informado encontra-se inativo no momento.");
  }
}
