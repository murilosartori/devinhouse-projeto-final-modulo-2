package br.com.devinhouse.grupo5.domain.exceptionhandler;

import br.com.devinhouse.grupo5.domain.exceptions.CpfJaExistenteException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ValidationHandler.class)
class ValidationHandlerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    MessageSource messageSource;

    @Mock
    ValidationHandler validationHandler;

    @Test
    void handleMethodArgumentNotValid() {

    }

    @Test
    void cpfExistenteHandler() {
    }

    @Test
    void pessoaNaoEncontradaHandler() {
    }

    @Test
    void assuntoNaoEncontradoHandler() {
    }

    @Test
    void nuProcessoJaCadastradoException() {
    }

    @Test
    void interessadoNaoEncontradoHandler() {
    }

    @Test
    void constraintViolationException() {
    }

    @Test
    void testConstraintViolationException() {
    }

    @Test
    void testConstraintViolationException1() {
    }
}