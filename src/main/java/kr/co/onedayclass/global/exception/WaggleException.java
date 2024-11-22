package kr.co.onedayclass.global.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WaggleException extends RuntimeException {

	private final ExceptionType exceptionType;

	@Override
	public String getMessage() {
		return exceptionType.getErrorMessage();
	}

	public HttpStatus getExceptionType() {
		return exceptionType.getErrorStatus();

	}
}
