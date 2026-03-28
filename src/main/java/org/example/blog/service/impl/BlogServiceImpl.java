package org.example.blog.service.impl;

import org.example.blog.dao.BlogDAO;
import org.example.blog.service.BlogService;
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
        return blogDAO.insert(map);
    }
}
