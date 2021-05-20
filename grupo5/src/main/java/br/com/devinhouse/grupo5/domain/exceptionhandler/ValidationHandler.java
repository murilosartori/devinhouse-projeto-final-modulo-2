package br.com.devinhouse.grupo5.domain.exceptionhandler;

import br.com.devinhouse.grupo5.domain.exceptions.CpfJaExistenteException;
import br.com.devinhouse.grupo5.domain.exceptions.ProcessoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
// import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Validation validation = newValidation("Um ou mais campos estão incorretos. Corrija e tente novamente", status);

    new Locale("pt-BR");
    List<Validation.Campo> campos = ex.getBindingResult().getAllErrors().stream()
        .map(e -> new Validation.Campo(((FieldError) e).getField(),
            messageSource.getMessage(e, LocaleContextHolder.getLocale()))
        // messageSource.getMessage(e, new Locale("pt-BR")))
        ).collect(Collectors.toList());

    validation.setCampos(campos);

    return super.handleExceptionInternal(ex, validation, headers, status, request);
  }

  @ExceptionHandler(CpfJaExistenteException.class)
  public ResponseEntity<Object> cpfExistenteHandler(CpfJaExistenteException ex, WebRequest webRequest) {

    var status = HttpStatus.BAD_REQUEST;

    var validation = newValidation(ex.getMessage(), status);
    return super.handleExceptionInternal(ex, validation, new HttpHeaders(), status, webRequest);
  }

  @ExceptionHandler(ProcessoNaoEncontradoException.class)
  public ResponseEntity<Object> pessoaNaoEncontradaHandler(ProcessoNaoEncontradoException ex, WebRequest webRequest) {

    var status = HttpStatus.NOT_FOUND;

    var validation = newValidation(ex.getMessage(), status);
    return super.handleExceptionInternal(ex, validation, new HttpHeaders(), status, webRequest);
  }

    //TODO:Exception e ExceptionHandler 2 - Não poderá ser cadastrado um novo processo com uma chave de processo já existente;
    //TODO:Exception e ExceptionHandler 3 - Não poderá ser cadastrado um novo processo com interessados inativos;
    //TODO:Exception e ExceptionHandler 4 - Não poderá ser cadastrado um novo processo com assuntos inativos;
    //TODO:Exception e ExceptionHandler 5 - Não poderá ser cadastrado um novo processo com interessados inesistentes no sistema;
    //TODO:Exception e ExceptionHandler 6 - Não poderá ser cadastrado um novo processo com assuntos inesistentes no sistema;
    //TODO:Exception e ExceptionHandler 7 - Não poderá ser cadastrado um novo interessado com um id já existente;
    //TODO:Exception e ExceptionHandler 8 - Não poderá ser cadastrado um novo interessado com um mesmo documento de indentificação;
    //TODO:Exception e ExceptionHandler 9 - Não poderá ser cadastrado um novo interessado com um documento de identificação inválido;

  private Validation newValidation(String titulo, HttpStatus status) {
    var validation = new Validation();
    validation.setStatus(status.value());
    validation.setDataHora(OffsetDateTime.now());
    validation.setTitulo(titulo);

    return validation;
  }
}
