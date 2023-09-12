package com.emotionbank.business.global.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.emotionbank.business.global.error.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	protected ResponseEntity<ErrorResponse> handleBindException(BindException bindException) {
		log.error("handleBindException", bindException);
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST.toString(),
			bindException.getBindingResult());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
		log.error("handleMethodArgumentTypeMismatchException", methodArgumentTypeMismatchException);
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST.toString(),
			methodArgumentTypeMismatchException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
		HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		log.error("handleHttpRequestMethodNotSupportedException", httpRequestMethodNotSupportedException);
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED.toString(),
			httpRequestMethodNotSupportedException.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
	}

	@ExceptionHandler(value = {BusinessException.class})
	protected ResponseEntity<ErrorResponse> handleConflict(BusinessException businessException) {
		log.error("BusinessException", businessException);
		ErrorResponse errorResponse = ErrorResponse.of(businessException.getErrorCode().getErrorCode(),
			businessException.getMessage());
		return ResponseEntity.status(businessException.getErrorCode().getHttpStatus())
			.body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception exception) {
		log.error("Exception", exception);
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
			exception.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

}
