package org.example.blog.controller;

import org.example.blog.service.CommentService;
import org.example.blog.vo.CommentAddRequestVO;
import org.example.blog.vo.CommentResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ResponseBody
    public CommentResponseVO add(CommentAddRequestVO commentAddRequestVO) {
        return this.commentService.add(commentAddRequestVO);
    }
}
