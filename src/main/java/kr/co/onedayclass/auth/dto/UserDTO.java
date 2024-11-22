package kr.co.onedayclass.auth.dto;

import static lombok.AccessLevel.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class UserDTO {

	private String username;
	private String name;
	private String role;
}