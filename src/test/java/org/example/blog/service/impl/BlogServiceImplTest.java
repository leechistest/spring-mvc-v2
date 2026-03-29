package org.example.blog.service.impl;

import org.example.blog.dao.BlogDAO;
import org.example.blog.vo.BlogEditRequestVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {

    @Mock
    private BlogDAO blogDAO;

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
}
