package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.vo.CommentInsertVO;

@Mapper
public interface CommentMapper {
    int insert(CommentInsertVO commentInsertVO);
}
