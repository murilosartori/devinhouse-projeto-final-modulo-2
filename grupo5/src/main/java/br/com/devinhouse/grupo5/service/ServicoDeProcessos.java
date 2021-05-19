package br.com.devinhouse.grupo5.service;

import java.util.Optional;

import br.com.devinhouse.grupo5.dto.ProcessoInputDTO;
import br.com.devinhouse.grupo5.dto.ProcessoOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.grupo5.domain.exceptions.CpfJaExistenteException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import br.com.devinhouse.grupo5.model.Processo;
import br.com.devinhouse.grupo5.repository.RepositorioDeProcessos;

@Service
public class ServicoDeProcessos {

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  RepositorioDeProcessos processoRepository;

  public ProcessoOutputDTO saveProcesso(ProcessoInputDTO processo) {
    if (processoRepository.existsById(processo.getNuProcesso())) {
      throw new CpfJaExistenteException("Numero de processo ja existe");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Numero de processo ja existe");
      // return new ResponseEntity<>(ServiceStatus.JSON(409, "Numero de processo ja
      // existe."), header, HttpStatus.CONFLICT);
    }
    // TODO: retirar setChaveProcesso e repensar a construção da chave
    // processo.setChaveProcesso();

//     processoRepository.save(modelMapper.map(processo, Processo.class));
//    Processo processo1 = new Processo(
//            processo.getNuProcesso(),
//            processo.getSgOrgaoProcesso(),
//            processo.getNuAnoProcesso(),
//            processo.getDescricao(),
//            Integer.parseInt(processo.getCdAssunto()),
//            processo.getDescricaoAssunto(),
//            processo.getCdInteressado(),
//            processo.getNmInteressado()
//    );

    Processo processo1 = modelMapper.map(processo, Processo.class);
    processoRepository.save(processo1);
    System.out.println(processo);
    return modelMapper.map(processo1, ProcessoOutputDTO.class);
  }

  public Iterable<Processo> getProcessoHistory() {
    return processoRepository.findAll();
  }

  public ProcessoOutputDTO buscaUmProcesso(Long id) {
    Optional<Processo> processo = processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Processo nao encontrado");
      // return new ResponseEntity<String>(ServiceStatus.JSON(), header,
      // HttpStatus.NOT_FOUND);
    }
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }

  public ProcessoOutputDTO buscaUmProcessoPorChave(String chaveProcesso) {
    Optional<Processo> processo = processoRepository.findByChaveProcesso(chaveProcesso);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // ANTIGO: HttpHeaders header = new HttpHeaders();
      // header.add("Mensagem:", "Processo nao encontrado");
      // return new ResponseEntity<>(ServiceStatus.JSON(), header,
      // HttpStatus.NOT_FOUND);
    }
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }

  public void atualizaProcesso(Processo processoAtualizado, Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // HttpHeaders header = new HttpHeaders();
      // processoAtualizado.setChaveProcesso();
      // processoRepository.save(processoAtualizado);
      // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /* TODO: Criar um DTO, extrair os dados alteráveis do processoAtualizado por no processo que consta no BD
     */
    processoRepository.save(processoAtualizado);
  }

  public ProcessoOutputDTO deletaProcesso(Long id) {
    Optional<Processo> processo =  processoRepository.findById(id);
    if (processo.isEmpty()) {
      throw new ProcessoNaoEncontradoException("Processo nao encontrado");
      // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      // header.add("Mensagem:", "Processo nao encontrado");
    }
    processoRepository.deleteById(id);
    return modelMapper.map(processo.get(), ProcessoOutputDTO.class);
  }
}
