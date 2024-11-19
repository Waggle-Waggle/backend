package kr.co.onedayclass.domain.board.domain;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.onedayclass.domain.user.domain.User;
import kr.co.onedayclass.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private String content;
	private Long parentSeq;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User author;
	private Long boardSeq;
}
