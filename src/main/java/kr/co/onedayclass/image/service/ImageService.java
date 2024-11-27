package kr.co.onedayclass.image.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.onedayclass.image.domain.Image;
import kr.co.onedayclass.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;

	@Transactional
	public Image save(Image image) {
		return imageRepository.save(image);
	}
}
