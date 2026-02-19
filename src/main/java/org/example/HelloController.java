package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping({"/","/hello"})
    public String hello(Model model) {
      model.addAttribute("message", "Spring 5 + Java 8 환경");

      return "hello";
    }
}
