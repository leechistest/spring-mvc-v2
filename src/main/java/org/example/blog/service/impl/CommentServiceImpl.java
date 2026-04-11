package org.example.blog.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.blog.mapper.CommentMapper;
import org.example.blog.service.CommentService;
import org.example.blog.vo.CommentAddRequestVO;
import org.example.blog.vo.CommentInsertVO;
import org.example.blog.vo.CommentResponseVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentResponseVO add(CommentAddRequestVO commentAddRequestVO) {
        ModelMapper modelMapper = new ModelMapper();
        CommentInsertVO commentInsertVO = modelMapper.map(commentAddRequestVO, CommentInsertVO.class);

        if (commentInsertVO.getTmpPw() != null) {
            String sha256hex = DigestUtils.sha256Hex(commentInsertVO.getTmpPw());
            commentInsertVO.setTmpPw(sha256hex);
        }

        int affectRowCount = this.commentMapper.insert(commentInsertVO);
        if (affectRowCount == 0) {
            return null;
        }

        ModelMapper modelMapperInsertToResponse = new ModelMapper();

        return modelMapperInsertToResponse.map(commentInsertVO, CommentResponseVO.class);
    }
}
