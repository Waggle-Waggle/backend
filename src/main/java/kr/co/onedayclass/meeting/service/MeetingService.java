package kr.co.onedayclass.meeting.service;

import static kr.co.onedayclass.global.exception.ExceptionType.*;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.onedayclass.global.exception.WaggleException;
import kr.co.onedayclass.image.domain.Image;
import kr.co.onedayclass.image.service.ImageService;
import kr.co.onedayclass.meeting.domain.Meeting;
import kr.co.onedayclass.meeting.dto.CreateMeetingRequest;
import kr.co.onedayclass.meeting.dto.CreateMeetingResponse;
import kr.co.onedayclass.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingService {

	private final MeetingRepository meetingRepository;

	private final ImageService imageService;

	@Transactional
	public CreateMeetingResponse create(Long userId, CreateMeetingRequest request) {
		validateUniqueName(request.getName());
		Meeting meeting = meetingRepository.save(request.toMeeting(userId));
		if (request.getImgUrl() != null) {
			Image image = imageService.save(request.toImage(meeting.getSeq()));
			return CreateMeetingResponse.from(meeting, image.getUrl());
		}

		return CreateMeetingResponse.from(meeting);
	}

	private void validateUniqueName(String name) {
		if (meetingRepository.findByName(name).isPresent()) {
			throw new WaggleException(MEETING_NAME_DUPLICATE);
		}
	}

	private Meeting getBy(String name) {
		return meetingRepository.findByName(name)
			.orElseThrow(() -> new WaggleException(MEETING_NOT_FOUND));
	}
}
