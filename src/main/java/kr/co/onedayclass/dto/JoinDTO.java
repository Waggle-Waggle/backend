package kr.co.onedayclass.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class JoinDTO {

    private String username;
    private String password;
}
