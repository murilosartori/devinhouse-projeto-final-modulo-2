package br.com.devinhouse.grupo5.domain.exceptions;

public class ProcessoNaoEncontradoException extends RuntimeException {

  public ProcessoNaoEncontradoException(Long id) {
    super(String.format("O processo pelo qual buscavas, id %d, não foi encontrado", id));
  }

  public ProcessoNaoEncontradoException(String chaveProcesso) {
    super(String.format("O processo pelo qual buscavas, chave %s, não foi encontrado", chaveProcesso));
  }

//  public ProcessoNaoEncontradoException(String objetoDeBusca, String valorDeBusca){
//    super(String.format("Sua busca pelo %s do processo com valor %s não localizou nenhum processo.", objetoDeBusca, valorDeBusca));
//  }

  public ProcessoNaoEncontradoException(){super("O processo pelo qual buscavas não foi encontrado");}

}
