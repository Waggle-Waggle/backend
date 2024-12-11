package kr.co.onedayclass.global.success;

import lombok.Getter;

@Getter
public enum SuccessType {

	MEETING_CREATION_SUCCESS("모임이 성공적으로 개설되었습니다.");

	SuccessType(String message) {
		this.message = message;
	}

	private final String message;
}
