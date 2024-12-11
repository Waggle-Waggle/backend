package kr.co.onedayclass.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.onedayclass.image.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
