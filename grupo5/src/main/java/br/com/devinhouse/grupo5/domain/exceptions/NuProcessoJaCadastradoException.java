package br.com.devinhouse.grupo5.domain.exceptions;

public class NuProcessoJaCadastradoException extends RuntimeException{
    public NuProcessoJaCadastradoException(String chaveProcesso){
    super(String.format("O processo informado, chave %s, já se encontra cadastrado.", chaveProcesso));
  }
}
