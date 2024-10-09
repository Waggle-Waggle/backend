package kr.co.onedayclass.controller;

import kr.co.onedayclass.dto.JoinDTO;
import kr.co.onedayclass.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService =joinService;
    }



    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        System.out.println(joinDTO.getUsername());

        joinService.joinProcess(joinDTO );


        return "ok";
    }
}
