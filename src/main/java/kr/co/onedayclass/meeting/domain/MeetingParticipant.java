package kr.co.onedayclass.meeting.domain;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static kr.co.onedayclass.meeting.domain.ParticipationStatus.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kr.co.onedayclass.global.entity.BaseEntity;
import kr.co.onedayclass.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class MeetingParticipant extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private LocalDateTime joinedAt;

	@Enumerated(STRING)
	private ParticipationStatus participationStatus;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	private Long meetingSeq;

	@PrePersist
	@PreUpdate
	private void updateJoinedAt() {
		if (participationStatus == PARTICIPATING) {
			this.joinedAt = getUpdatedAt();
		}
	}
}
