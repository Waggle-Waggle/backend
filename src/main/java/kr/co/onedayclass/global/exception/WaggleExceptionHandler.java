package kr.co.onedayclass.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WaggleExceptionHandler {

	@ExceptionHandler(WaggleException.class)
	public ResponseEntity<WaggleExceptionResponse> handleException(WaggleException exception) {
		WaggleExceptionResponse response = WaggleExceptionResponse.builder()
			.message(exception.getMessage())
			.build();

		return ResponseEntity.status(exception.getExceptionType()).body(response);
	}
}
