package org.example.blog.service;

import org.example.blog.vo.BlogEditRequestVO;

import java.util.Map;

public interface BlogService {
    int create(Map<String, Object> map);

    Map<String, Object> read(int blogContSeq);

    boolean edit(BlogEditRequestVO blogEditRequestVO);
}
