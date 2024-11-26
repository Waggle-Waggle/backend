package kr.co.onedayclass.meeting.service;

import static kr.co.onedayclass.global.exception.ExceptionType.*;
import static kr.co.onedayclass.meeting.domain.Category.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.co.onedayclass.config.WaggleTestBase;
import kr.co.onedayclass.global.exception.WaggleException;
import kr.co.onedayclass.helper.UserHelper;
import kr.co.onedayclass.meeting.dto.CreateMeetingRequest;
import kr.co.onedayclass.user.domain.User;

@Transactional
class MeetingServiceTest extends WaggleTestBase {

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private UserHelper userHelper;

	@Test
	@DisplayName("모임을 생성할 수 있다.")
	void create() {
		// given
		User user = userHelper.create();

		CreateMeetingRequest request = new CreateMeetingRequest(
			"waggle",
			null,
			BOOKS,
			"location",
			"와글와글의 첫번째 모임입니다.",
			300
		);

		// when
		// then
		assertDoesNotThrow(() ->
			meetingService.create(user.getId(), request)
		);
	}

	@Test
	@DisplayName("중복된 이름의 모임을 생성할 수 없다.")
	void cannotCreateMeetingWithDuplicateName() {
		// given
		User user = userHelper.create();

		CreateMeetingRequest request = new CreateMeetingRequest(
			"waggle",
			null,
			BOOKS,
			"location",
			"와글와글의 첫번째 모임입니다.",
			300
		);
		meetingService.create(user.getId(), request);

		// when
		// then
		assertThatThrownBy(() -> meetingService.create(user.getId(), request))
			.isInstanceOf(WaggleException.class)
			.hasMessage(MEETING_NAME_DUPLICATE.getErrorMessage());
	}
}