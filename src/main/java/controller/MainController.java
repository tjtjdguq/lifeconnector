package controller;

import Model.Vo.TestVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    String testingApi(){
        return "index.jsp";
    }

    @GetMapping("/mainpage")
    ResponseEntity<TestVo> tset2(){return new ResponseEntity<>(HttpStatus.OK);}
}
