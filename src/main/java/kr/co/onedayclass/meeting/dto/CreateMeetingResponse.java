package kr.co.onedayclass.meeting.dto;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import kr.co.onedayclass.meeting.domain.Category;
import kr.co.onedayclass.meeting.domain.Meeting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CreateMeetingResponse {

	private Long seq;
	private String name;
	private String imgUrl;
	private Category category;
	private String location;
	private String description;
	private int participantCount;
	private LocalDateTime createdAt;

	public static CreateMeetingResponse from(Meeting meeting) {
		return new CreateMeetingResponse(
			meeting.getSeq(),
			meeting.getName(),
			null,
			meeting.getCategory(),
			meeting.getLocation(),
			meeting.getDescription(),
			meeting.getParticipantCount(),
			meeting.getCreatedAt()
		);
	}

	public static CreateMeetingResponse from(Meeting meeting, String imageUrl) {
		return new CreateMeetingResponse(
			meeting.getSeq(),
			meeting.getName(),
			imageUrl,
			meeting.getCategory(),
			meeting.getLocation(),
			meeting.getDescription(),
			meeting.getParticipantCount(),
			meeting.getCreatedAt()
		);
	}
}
