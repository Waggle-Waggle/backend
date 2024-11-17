package kr.co.onedayclass.service;

import kr.co.onedayclass.dto.UserProfileDto;
import kr.co.onedayclass.entity.UserEntity;
import kr.co.onedayclass.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 현재 사용자 프로필 가져오기 (GET /api/users/me)
    public UserProfileDto getCurrentUserProfile() {
        Long userId = getAuthenticatedUserId();
        UserEntity user = getUserById(userId);
        return toDto(user);
    }

    // 현재 사용자 프로필 업데이트 (PUT /api/users/me)
    public void updateCurrentUserProfile(UserProfileDto profileData) {
        Long userId = getAuthenticatedUserId();
        UserEntity user = getUserById(userId);

        Optional.ofNullable(profileData.getBio()).ifPresent(user::setBio);
        Optional.ofNullable(profileData.getAge()).ifPresent(user::setAge);
        Optional.ofNullable(profileData.getGender()).ifPresent(user::setGender);
        Optional.ofNullable(profileData.getCategory()).ifPresent(user::setCategory);

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    // 현재 사용자 프로필 데이터 추가 (POST /api/users/me)
    public void addProfileField(UserProfileDto profileData) {
        Long userId = getAuthenticatedUserId();
        UserEntity user = getUserById(userId);

        if (profileData.getBio() != null) user.setBio(profileData.getBio());
        if (profileData.getCategory() != null) user.setCategory(profileData.getCategory());

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    // 특정 사용자의 프로필 가져오기 (GET /api/users/{user-id}/profile)
    public UserProfileDto getUserProfile(Long userId) {
        UserEntity user = getUserById(userId);
        return toDto(user);
    }

    // 현재 사용자 계정 비활성화 (DELETE /api/users/withdraw)
    public void deactivateCurrentUser() {
        Long userId = getAuthenticatedUserId();
        UserEntity user = getUserById(userId);

        user.setActive(false);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    // ID로 사용자 가져오기
    private UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + userId));
    }

    // DTO -> Entity 변환
    private UserEntity toEntity(UserProfileDto profileDto) {
        return UserEntity.builder()
                .userId(profileDto.getUserId())
                .username(profileDto.getUsername())
                .bio(profileDto.getBio())
                .age(profileDto.getAge())
                .gender(profileDto.getGender())
                .category(profileDto.getCategory())
                .socialType(profileDto.getSocialType())
                .build();
    }

    // Entity -> DTO 변환
    private UserProfileDto toDto(UserEntity user) {
        return UserProfileDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .bio(user.getBio())
                .age(user.getAge())
                .gender(user.getGender())
                .category(user.getCategory())
                .socialType(user.getSocialType())
                .build();
    }

    // 인증된 사용자 ID 가져오기
    private Long getAuthenticatedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username)
                    .map(UserEntity::getUserId)
                    .orElseThrow(() -> new IllegalArgumentException("현재 인증된 사용자를 찾을 수 없습니다."));
        } else {
            throw new IllegalStateException("인증 정보가 유효하지 않습니다.");
        }
    }
}
