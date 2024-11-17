package kr.co.onedayclass.dto;

import kr.co.onedayclass.entity.UserEntity.SocialType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDto {
    private Long userId;
    private String username;
    private String bio;
    private Integer age;
    private String gender;
    private String category;
    private SocialType socialType;
}
