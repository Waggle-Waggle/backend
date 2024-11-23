package kr.co.onedayclass.meeting.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.co.onedayclass.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class Meeting extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private String name;
	@Enumerated
	private Category category;
	// TODO: VO로 만들건지 정해야 됨
	private String location;
	private String description;
	private Long hostId;
	private int participantCount;

}
