package kr.co.onedayclass.board.domain;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Board {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	@Enumerated(STRING)
	private BoardType boardType;
	private String title;
	private String content;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User author;
	private Long meetingSeq;
}
