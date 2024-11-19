package kr.co.onedayclass.domain.meeting.domain;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.onedayclass.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class MeetingParticipant {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private LocalDateTime joinedAt;

	@Enumerated
	private ParticipationStatus participationStatus;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private Long MeetingSeq;
}
