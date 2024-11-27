package kr.co.onedayclass.meeting.dto;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.onedayclass.image.domain.Image;
import kr.co.onedayclass.meeting.domain.Category;
import kr.co.onedayclass.meeting.domain.Meeting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class CreateMeetingRequest {

	@NotBlank
	@Length(min = 5, max = 50)
	private String name;
	private String imgUrl;

	private Category category;

	@NotBlank
	private String location;

	@NotBlank
	private String description;

	@NotNull
	@Max(500)
	private int participantCount;

	public Meeting toMeeting(Long userId) {
		return Meeting.builder()
			.name(name)
			.category(category)
			.location(location)
			.description(description)
			.hostId(userId)
			.participantCount(participantCount)
			.build();
	}

	public Image toImage(Long meetingSeq) {
		return Image.builder()
			.userId(null)
			.meetingSeq(meetingSeq)
			.boardSeq(null)
			.commentSeq(null)
			.url(imgUrl)
			// TODO
			.uploadedAt(LocalDateTime.now())
			.build();
	}
}
