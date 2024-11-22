package kr.co.onedayclass.global.dto;

import static lombok.AccessLevel.*;

import kr.co.onedayclass.global.success.SuccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class WaggleResponse<T> {

	private String message;
	private T body;

	public static <T> WaggleResponse<T> success(T body, SuccessType message) {
		return WaggleResponse.<T>builder()
			.message(message.getMessage())
			.body(body)
			.build();
	}
}