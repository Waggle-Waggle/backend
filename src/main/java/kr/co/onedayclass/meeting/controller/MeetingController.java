package kr.co.onedayclass.meeting.controller;

import static kr.co.onedayclass.global.success.SuccessType.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.onedayclass.global.dto.WaggleResponse;
import kr.co.onedayclass.meeting.dto.CreateMeetingRequest;
import kr.co.onedayclass.meeting.dto.CreateMeetingResponse;
import kr.co.onedayclass.meeting.service.MeetingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {

	private final MeetingService meetingService;

	@PostMapping
	public WaggleResponse<CreateMeetingResponse> create(
		@Valid @RequestBody CreateMeetingRequest request
		// @AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateMeetingRequest request
	) {
		// Long userId = Long.parseLong(userDetails.getUsername());
		CreateMeetingResponse response = meetingService.create(1L, request);

		return WaggleResponse.success(response, MEETING_CREATION_SUCCESS);
	}

}
