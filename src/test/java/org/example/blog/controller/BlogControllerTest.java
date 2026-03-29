package org.example.blog.controller;

import org.example.blog.service.BlogService;
import org.example.blog.vo.BlogEditRequestVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest {

    @Mock
    private BlogService blogService;

    private BlogController blogController;

    @BeforeEach
    void setUp() {
        blogController = new BlogController();
        ReflectionTestUtils.setField(blogController, "blogService", blogService);
    }

    @Test
    void getCreateReturnsCreateView() {
        String viewName = blogController.getCreate();

        assertEquals("blog/create", viewName);
    }

    @Test
    void postCreateRedirectsToReadPage() {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "테스트 제목");
        param.put("content_body", "테스트 내용");

        when(blogService.create(anyMap())).thenReturn(123);

        String viewName = blogController.postCreate(param);

        assertEquals("redirect:/read/123", viewName);
        verify(blogService).create(param);
    }

    @Test
    void getReadReturnsReadViewWithModel() {
        Map<String, Object> blogCont = new HashMap<>();
        blogCont.put("blg_cont_seq", 123);
        blogCont.put("title", "테스트 제목");
        blogCont.put("cont_bdy", "테스트 내용");
        Model model = new ConcurrentModel();

        when(blogService.read(123)).thenReturn(blogCont);

        String viewName = blogController.getRead(123, model);

        assertEquals("blog/read", viewName);
        assertSame(blogCont, model.getAttribute("blogCont"));
        verify(blogService).read(123);
    }

    @Test
    void getEditReturnsEditViewWithModel() {
        Map<String, Object> blogCont = new HashMap<>();
        blogCont.put("blg_cont_seq", 123);
        blogCont.put("title", "테스트 제목");
        blogCont.put("cont_bdy", "테스트 내용");

        when(blogService.read(123)).thenReturn(blogCont);

        ModelAndView mav = blogController.getEdit(123);

        assertEquals("/blog/edit", mav.getViewName());
        assertSame(blogCont, mav.getModel().get("blogCont"));
        verify(blogService).read(123);
    }

    @Test
    void getEditRedirectsToListWhenNotFound() {
        when(blogService.read(999)).thenReturn(null);

        ModelAndView mav = blogController.getEdit(999);

        assertEquals("redirect:/list", mav.getViewName());
        verify(blogService).read(999);
    }

    @Test
    void putEditRedirectsToEditOnSuccess() {
        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(123);
        vo.setTitle("수정 제목");
        vo.setContBdy("수정 내용");

        when(blogService.edit(vo)).thenReturn(true);

        String viewName = blogController.putEdit(vo);

        assertEquals("redirect:/edit/123", viewName);
        verify(blogService).edit(vo);
    }

    @Test
    void putEditRedirectsToListOnFail() {
        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(999);
        vo.setTitle("수정 제목");
        vo.setContBdy("수정 내용");

        when(blogService.edit(vo)).thenReturn(false);

        String viewName = blogController.putEdit(vo);

        assertEquals("redirect:/list", viewName);
        verify(blogService).edit(vo);
    }
}
