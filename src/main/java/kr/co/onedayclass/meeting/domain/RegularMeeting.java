package kr.co.onedayclass.meeting.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
public class RegularMeeting extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private String name;
	private LocalDateTime meetingDate;
	private String location;
	private String entryFee;
	private int participantCount;
	private Long meetingSeq;
}
