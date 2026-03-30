package org.example.blog.controller;

import org.example.blog.service.BlogService;
import org.example.blog.vo.BlogEditRequestVO;
import org.example.blog.vo.BlogListRequestVO;
import org.example.blog.vo.BlogListResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("GET /create - 글쓰기 폼 뷰를 반환한다")
    void getCreateReturnsCreateView() {
        String viewName = blogController.getCreate();

        assertEquals("blog/create", viewName);
    }

    @Test
    @DisplayName("POST /create - 글 등록 후 read 페이지로 리다이렉트한다")
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
    @DisplayName("GET /read/{id} - 글 조회 뷰와 모델을 반환한다")
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
    @DisplayName("GET /edit/{id} - 수정 폼 뷰와 모델을 반환한다")
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
    @DisplayName("GET /edit/{id} - 존재하지 않는 글이면 목록 페이지로 리다이렉트한다")
    void getEditRedirectsToListWhenNotFound() {
        when(blogService.read(999)).thenReturn(null);

        ModelAndView mav = blogController.getEdit(999);

        assertEquals("redirect:/list", mav.getViewName());
        verify(blogService).read(999);
    }

    @Test
    @DisplayName("PUT /edit/{id} - 수정 성공 시 수정 페이지로 리다이렉트한다")
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
    @DisplayName("PUT /edit/{id} - 수정 실패 시 목록 페이지로 리다이렉트한다")
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

    @Test
    @DisplayName("DELETE /delete - 삭제 후 목록 페이지로 리다이렉트한다")
    void deleteRedirectsToList() {
        when(blogService.delete(123)).thenReturn(true);

        String viewName = blogController.delete(123);

        assertEquals("redirect:/list", viewName);
        verify(blogService).delete(123);
    }

    @Test
    @DisplayName("GET /list - 전체 목록을 조회하고 뷰와 모델을 반환한다")
    void listReturnsListViewWithModel() {
        BlogListRequestVO requestVO = new BlogListRequestVO();
        BlogListResponseVO item1 = new BlogListResponseVO();
        item1.setBlgContSeq(1);
        item1.setTitle("제목1");
        item1.setContBdy("내용1");
        item1.setInsertDt(LocalDateTime.now());

        BlogListResponseVO item2 = new BlogListResponseVO();
        item2.setBlgContSeq(2);
        item2.setTitle("제목2");
        item2.setContBdy("내용2");
        item2.setInsertDt(LocalDateTime.now());

        List<BlogListResponseVO> list = Arrays.asList(item1, item2);
        Model model = new ConcurrentModel();

        when(blogService.list(requestVO)).thenReturn(list);

        String viewName = blogController.list(requestVO, model);

        assertEquals("blog/list", viewName);
        assertSame(requestVO, model.getAttribute("blogListRequestVO"));
        assertSame(list, model.getAttribute("blogListResponseVOList"));
        verify(blogService).list(requestVO);
    }

    @Test
    @DisplayName("GET /list - 검색어가 있으면 필터링된 목록을 반환한다")
    void listWithSearchReturnsFilteredList() {
        BlogListRequestVO requestVO = new BlogListRequestVO();
        requestVO.setSearch("스프링");

        BlogListResponseVO item = new BlogListResponseVO();
        item.setBlgContSeq(1);
        item.setTitle("스프링 MVC 정리");
        item.setContBdy("스프링 내용");
        item.setInsertDt(LocalDateTime.now());

        List<BlogListResponseVO> list = Arrays.asList(item);
        Model model = new ConcurrentModel();

        when(blogService.list(requestVO)).thenReturn(list);

        String viewName = blogController.list(requestVO, model);

        assertEquals("blog/list", viewName);
        assertEquals(1, ((List<?>) model.getAttribute("blogListResponseVOList")).size());
        verify(blogService).list(requestVO);
    }

    @Test
    @DisplayName("GET /list - 결과가 없으면 빈 목록을 반환한다")
    void listWithEmptyResultReturnsEmptyList() {
        BlogListRequestVO requestVO = new BlogListRequestVO();
        List<BlogListResponseVO> emptyList = new java.util.ArrayList<>();
        Model model = new ConcurrentModel();

        when(blogService.list(requestVO)).thenReturn(emptyList);

        String viewName = blogController.list(requestVO, model);

        assertEquals("blog/list", viewName);
        assertTrue(((List<?>) model.getAttribute("blogListResponseVOList")).isEmpty());
        verify(blogService).list(requestVO);
    }
}
