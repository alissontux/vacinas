package br.com.alissonPrado.vacinas.config.validacao;

/*
 @ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions 
 across the whole application in one global handling component. It can be viewed as an interceptor of 
 exceptions thrown by methods annotated with @RequestMapping and similar.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	// @ResponseBody
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		return "Campo: " + ex.getBindingResult().getFieldError().getField() + " - Descrição erro: "
				+ ex.getBindingResult().getFieldError().getDefaultMessage();
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleInvalidFormatException(InvalidFormatException ex) {

		String nomeCampo = "campo";

		if (ex.getPath() != null && !ex.getPath().isEmpty()) {
			JsonMappingException.Reference path = ex.getPath().get(ex.getPath().size() - 1);
			if (path != null) {
				nomeCampo = path.getFieldName();
			}
		}

		String mensagemErro = "O campo fornecido " + nomeCampo + " valor '" + ex.getValue().toString()
				+ "' não é do tipo requerido " + ex.getTargetType();

		return mensagemErro;
	}
}
