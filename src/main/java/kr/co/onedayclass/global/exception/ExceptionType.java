package kr.co.onedayclass.global.exception;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionType {

	MEETING_NOT_FOUND(NOT_FOUND, "모임이 존재하지 않습니다."),
	MEETING_NAME_DUPLICATE(BAD_REQUEST, "이미 존재하는 모임명 입니다.");

	ExceptionType(HttpStatus errorStatus, String errorMessage) {
		this.errorStatus = errorStatus;
		this.errorMessage = errorMessage;
	}

	private final HttpStatus errorStatus;
	private final String errorMessage;
}
