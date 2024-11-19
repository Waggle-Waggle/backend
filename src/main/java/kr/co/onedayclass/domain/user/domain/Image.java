package kr.co.onedayclass.domain.user.domain;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class Image {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long seq;

	private Long userId;
	private Long boardSeq;
	private Long commentSeq;
	private String url;
	private LocalDateTime uploadedAt;
}
