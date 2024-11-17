package kr.co.onedayclass.controller;

import kr.co.onedayclass.dto.UserProfileDto;
import kr.co.onedayclass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 현재 사용자 프로필 가져오기
    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getCurrentUserProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

    // 현재 사용자 프로필 업데이트
    @PutMapping("/me")
    public ResponseEntity<String> updateCurrentUserProfile(@RequestBody UserProfileDto profileData) {
        userService.updateCurrentUserProfile(profileData);
        return ResponseEntity.ok("프로필이 성공적으로 업데이트되었습니다.");
    }

    // 현재 사용자 프로필 데이터 추가
    @PostMapping("/me")
    public ResponseEntity<String> addProfileField(@RequestBody UserProfileDto profileData) {
        userService.addProfileField(profileData);
        return ResponseEntity.ok("프로필 데이터가 성공적으로 추가되었습니다.");
    }

    // 특정 사용자 프로필 가져오기
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    // 회원 탈퇴
    @DeleteMapping("/withdraw")
    public ResponseEntity<String> deactivateCurrentUser() {
        userService.deactivateCurrentUser();
        return ResponseEntity.ok("회원 탈퇴가 성공적으로 처리되었습니다.");
    }
}
