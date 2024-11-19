package kr.co.onedayclass.domain.user.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.onedayclass.domain.meeting.domain.Category;
import kr.co.onedayclass.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String username;
	private String password;
	private String bio;
	private String age;
	private String gender;
	private Category category;
	private int wageulScore;
	@Enumerated
	private SocialType socialType;
	private boolean isAgePublic;
	private boolean isGenderPublic;
}
