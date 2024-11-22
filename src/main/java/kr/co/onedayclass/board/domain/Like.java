package kr.co.onedayclass.board.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class Like {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private Long userId;
	private Long boardSeq;
	private Long commentSeq;
	private LocalDateTime createdAt;
}
