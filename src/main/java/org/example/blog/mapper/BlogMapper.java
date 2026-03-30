package org.example.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.blog.vo.BlogListRequestVO;
import org.example.blog.vo.BlogListResponseVO;

import java.util.List;

@Mapper
public interface BlogMapper {
    int delete(int blgContSeq);

    List<BlogListResponseVO> selectList(BlogListRequestVO blogListRequestVO);
}
