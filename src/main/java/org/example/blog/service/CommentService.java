package org.example.blog.service;

import org.example.blog.vo.CommentAddRequestVO;
import org.example.blog.vo.CommentResponseVO;

public interface CommentService {

    CommentResponseVO add(CommentAddRequestVO commentAddRequestVO);
}