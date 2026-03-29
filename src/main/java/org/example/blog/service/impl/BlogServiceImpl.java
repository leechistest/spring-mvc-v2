package org.example.blog.service.impl;

import org.example.blog.dao.BlogDAO;
import org.example.blog.service.BlogService;
import org.example.blog.vo.BlogEditRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogDAO blogDAO;

    @Autowired
    public BlogServiceImpl(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Override
    public int create(Map<String, Object> map) {
        return this.blogDAO.insert(map);
    }

    @Override
    public Map<String, Object> read(int blogContSeq) {
        return this.blogDAO.selectOne(blogContSeq);
    }

    @Override
    public boolean edit(BlogEditRequestVO blogEditRequestVO) {
        return this.blogDAO.update(blogEditRequestVO) > 0;
    }
}
