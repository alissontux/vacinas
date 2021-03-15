package br.com.alissonPrado.vacinas.config.validacao;

/*
 @ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions 
 across the whole application in one global handling component. It can be viewed as an interceptor of 
 exceptions thrown by methods annotated with @RequestMapping and similar.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		return "Campo: " + ex.getBindingResult().getFieldError().getField() + " - Descrição erro: "
				+ ex.getBindingResult().getFieldError().getDefaultMessage();
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String invalidFormatException(InvalidFormatException ex) {

		return "Dados enviados em formato incorreto. " + ex.getLocalizedMessage();
	}
}
