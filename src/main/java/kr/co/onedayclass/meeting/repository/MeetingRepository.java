package kr.co.onedayclass.meeting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.onedayclass.meeting.domain.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	Optional<Meeting> findByName(String name);
}
