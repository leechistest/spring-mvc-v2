package org.example.blog.service.impl;

import org.example.blog.dao.BlogDAO;
import org.example.blog.mapper.BlogMapper;
import org.example.blog.vo.BlogEditRequestVO;
import org.example.blog.vo.BlogListRequestVO;
import org.example.blog.vo.BlogListResponseVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {

    @Mock
    private BlogDAO blogDAO;

    @Mock
    private BlogMapper blogMapper;

    @InjectMocks
    private BlogServiceImpl blogService;

    @Test
    void createReturnsInsertedSequence() {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "테스트 제목");
        param.put("content_body", "테스트 내용");

        when(blogDAO.insert(param)).thenReturn(123);

        int result = blogService.create(param);

        assertEquals(123, result);
        verify(blogDAO).insert(param);
    }

    @Test
    void readReturnsBlogContent() {
        Map<String, Object> blogCont = new HashMap<>();
        blogCont.put("blg_cont_seq", 123);
        blogCont.put("title", "테스트 제목");
        blogCont.put("cont_bdy", "테스트 내용");

        when(blogDAO.selectOne(123)).thenReturn(blogCont);

        Map<String, Object> result = blogService.read(123);

        assertSame(blogCont, result);
        verify(blogDAO).selectOne(123);
    }

    @Test
    void editReturnsTrueWhenUpdateSucceeds() {
        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(123);
        vo.setTitle("수정 제목");
        vo.setContBdy("수정 내용");

        when(blogDAO.update(vo)).thenReturn(1);

        boolean result = blogService.edit(vo);

        assertTrue(result);
        verify(blogDAO).update(vo);
    }

    @Test
    void editReturnsFalseWhenUpdateFails() {
        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(999);
        vo.setTitle("수정 제목");
        vo.setContBdy("수정 내용");

        when(blogDAO.update(vo)).thenReturn(0);

        boolean result = blogService.edit(vo);

        assertFalse(result);
        verify(blogDAO).update(vo);
    }

    @Test
    void deleteReturnsTrueWhenDeleteSucceeds() {
        when(blogMapper.delete(123)).thenReturn(1);

        boolean result = blogService.delete(123);

        assertTrue(result);
        verify(blogMapper).delete(123);
    }

    @Test
    void deleteReturnsFalseWhenDeleteFails() {
        when(blogMapper.delete(999)).thenReturn(0);

        boolean result = blogService.delete(999);

        assertFalse(result);
        verify(blogMapper).delete(999);
    }

    @Test
    void listReturnsBlogList() {
        BlogListRequestVO requestVO = new BlogListRequestVO();

        BlogListResponseVO item1 = new BlogListResponseVO();
        item1.setBlgContSeq(1);
        item1.setTitle("제목1");
        item1.setInsertDt(LocalDateTime.now());

        BlogListResponseVO item2 = new BlogListResponseVO();
        item2.setBlgContSeq(2);
        item2.setTitle("제목2");
        item2.setInsertDt(LocalDateTime.now());

        List<BlogListResponseVO> list = Arrays.asList(item1, item2);

        when(blogMapper.selectList(requestVO)).thenReturn(list);

        List<BlogListResponseVO> result = blogService.list(requestVO);

        assertSame(list, result);
        assertEquals(2, result.size());
        verify(blogMapper).selectList(requestVO);
    }

    @Test
    void listWithSearchReturnsFilteredList() {
        BlogListRequestVO requestVO = new BlogListRequestVO();
        requestVO.setSearch("스프링");

        BlogListResponseVO item = new BlogListResponseVO();
        item.setBlgContSeq(1);
        item.setTitle("스프링 MVC");
        item.setInsertDt(LocalDateTime.now());

        List<BlogListResponseVO> list = Arrays.asList(item);

        when(blogMapper.selectList(requestVO)).thenReturn(list);

        List<BlogListResponseVO> result = blogService.list(requestVO);

        assertSame(list, result);
        assertEquals(1, result.size());
        verify(blogMapper).selectList(requestVO);
    }

    @Test
    void listReturnsEmptyListWhenNoResults() {
        BlogListRequestVO requestVO = new BlogListRequestVO();
        when(blogMapper.selectList(requestVO)).thenReturn(new java.util.ArrayList<>());

        List<BlogListResponseVO> result = blogService.list(requestVO);

        assertTrue(result.isEmpty());
        verify(blogMapper).selectList(requestVO);
    }
}
