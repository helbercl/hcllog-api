package com.hcllog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcllog.api.domain.exception.EntidadeNaoEncontradaException;
import com.hcllog.api.domain.exception.NegocioException;
import com.hcllog.api.domain.model.Campo;

import lombok.AllArgsConstructor;

/*@ControllerAdvice -Indica que a classe é um componente do spring com  proposito de trata exceções.
 * Qualquer controllador pode usa-la.
 * */

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	// As mensagens indicadas no arquivo properties so funcionam se injetar a classe
	// MessageSource
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = new ArrayList<>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Campo(nome, mensagem));
		}

		Erro erro = new Erro();
		erro.setCd_status(status.value());
		erro.setDataHoraErro(OffsetDateTime.now());
		erro.setDescricaoErro("Um ou mais campos estão invalidos");
		erro.setListaCampos(campos);
		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ne, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Erro erro = new Erro();
		erro.setCd_status(status.value());
		erro.setDataHoraErro(OffsetDateTime.now());
		erro.setDescricaoErro(ne.getMessage());
		return handleExceptionInternal(ne, erro, new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleNegocio(EntidadeNaoEncontradaException ne, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		Erro erro = new Erro();
		erro.setCd_status(status.value());
		erro.setDataHoraErro(OffsetDateTime.now());
		erro.setDescricaoErro(ne.getMessage());
		return handleExceptionInternal(ne, erro, new HttpHeaders(), status, request);

	}
}
