package org.example.blog.service;

import org.example.blog.vo.BlogEditRequestVO;
import org.example.blog.vo.BlogListRequestVO;
import org.example.blog.vo.BlogListResponseVO;

import java.util.List;
import java.util.Map;

public interface BlogService {
    int create(Map<String, Object> map);

    Map<String, Object> read(int blogContSeq);

    boolean edit(BlogEditRequestVO blogEditRequestVO);

    boolean delete(int blogContSeq);

    List<BlogListResponseVO> list(BlogListRequestVO blogListRequestVO);
}
