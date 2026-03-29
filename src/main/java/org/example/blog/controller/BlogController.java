package org.example.blog.controller;

import org.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreate() {
        return "blog/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCreate(@RequestParam Map<String, Object> map) {
        int blogContSeq = this.blogService.create(map);
        return "redirect:/read/" + blogContSeq;
    }

    @GetMapping(value = "/read/{blogContSeq}")
    public String getRead(@PathVariable("blogContSeq") int blogContSeq, Model model) {
        Map<String, Object> blogCont = this.blogService.read(blogContSeq);
        model.addAttribute("blogCont", blogCont);
        return "blog/read";
    }
}
