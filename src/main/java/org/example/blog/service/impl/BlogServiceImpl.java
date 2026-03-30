package org.example.blog.service.impl;

import org.example.blog.dao.BlogDAO;
import org.example.blog.mapper.BlogMapper;
import org.example.blog.service.BlogService;
import org.example.blog.vo.BlogEditRequestVO;
import org.example.blog.vo.BlogListRequestVO;
import org.example.blog.vo.BlogListResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogDAO blogDAO;
    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogDAO blogDAO, BlogMapper blogMapper) {
        this.blogDAO = blogDAO;
        this.blogMapper = blogMapper;
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

    @Override
    public boolean delete(int blogContSeq) {
        return this.blogMapper.delete(blogContSeq) > 0;
    }

    @Override
    public List<BlogListResponseVO> list(BlogListRequestVO blogListRequestVO) {
        return this.blogMapper.selectList(blogListRequestVO);
    }
}
