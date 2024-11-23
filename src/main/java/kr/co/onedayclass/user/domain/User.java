package kr.co.onedayclass.user.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.onedayclass.global.entity.BaseEntity;
import kr.co.onedayclass.meeting.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String username;
	private String email;
	private String bio;
	private String role;
	private String age;
	private String gender;
	private Category category;
	private int waggleScore;
	@Enumerated
	private SocialType socialType;
	private boolean isAgePublic;
	private boolean isGenderPublic;

	public void changeEmail(String email) {
		this.email = email;
	}
}
