package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    int delete(int blgContSeq);
}
