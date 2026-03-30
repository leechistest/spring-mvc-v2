package org.example.blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/db-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class BlogControllerIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Order(1)
    @DisplayName("GET /create - 글쓰기 폼 페이지를 반환한다")
    void GET_create_폼_페이지를_반환한다() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog/create"));
    }

    @Test
    @Order(2)
    @DisplayName("POST /create - 글 저장 후 read 페이지로 리다이렉트한다")
    void POST_create_저장_후_read_페이지로_리다이렉트한다() throws Exception {
        mockMvc.perform(post("/create")
                        .param("title", "[통합테스트] 컨트롤러 제목")
                        .param("content_body", "[통합테스트] 컨트롤러 내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/read/*"));
    }

    @Test
    @Order(3)
    @DisplayName("GET /read/{id} - 저장된 글을 조회한다")
    void GET_read_저장된_글을_조회한다() throws Exception {
        String redirectUrl = mockMvc.perform(post("/create")
                        .param("title", "[통합테스트] 조회용 제목")
                        .param("content_body", "[통합테스트] 조회용 내용"))
                .andExpect(status().is3xxRedirection())
                .andReturn()
                .getResponse()
                .getRedirectedUrl();

        assert redirectUrl != null;
        String seq = redirectUrl.replace("/read/", "");

        mockMvc.perform(get("/read/" + seq))
                .andExpect(status().isOk())
                .andExpect(view().name("blog/read"))
                .andExpect(model().attributeExists("blogCont"));
    }

    @Test
    @Order(4)
    @DisplayName("GET /edit/{id} - 저장된 글의 수정 폼을 반환한다")
    void GET_edit_저장된_글의_수정_폼을_반환한다() throws Exception {
        String redirectUrl = mockMvc.perform(post("/create")
                        .param("title", "[통합테스트] 수정 폼 제목")
                        .param("content_body", "[통합테스트] 수정 폼 내용"))
                .andReturn()
                .getResponse()
                .getRedirectedUrl();

        assert redirectUrl != null;
        String seq = redirectUrl.replace("/read/", "");

        mockMvc.perform(get("/edit/" + seq))
                .andExpect(status().isOk())
                .andExpect(view().name("/blog/edit"))
                .andExpect(model().attributeExists("blogCont"));
    }

    @Test
    @Order(5)
    @DisplayName("GET /edit/{id} - 존재하지 않는 글이면 목록 페이지로 리다이렉트한다")
    void GET_edit_존재하지_않는_글은_list로_리다이렉트한다() throws Exception {
        mockMvc.perform(get("/edit/-9999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));
    }

    @Test
    @Order(6)
    @DisplayName("PUT /edit/{id} - 글을 수정하고 수정 페이지로 리다이렉트한다")
    void PUT_edit_글을_수정하고_edit_페이지로_리다이렉트한다() throws Exception {
        String redirectUrl = mockMvc.perform(post("/create")
                        .param("title", "[통합테스트] 수정 전 제목")
                        .param("content_body", "[통합테스트] 수정 전 내용"))
                .andReturn()
                .getResponse()
                .getRedirectedUrl();

        assert redirectUrl != null;
        String seq = redirectUrl.replace("/read/", "");

        mockMvc.perform(put("/edit/" + seq)
                        .param("blgContSeq", seq)
                        .param("title", "[통합테스트] 수정 후 제목")
                        .param("contBdy", "[통합테스트] 수정 후 내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/edit/" + seq));
    }

    @Test
    @Order(7)
    @DisplayName("DELETE /delete - 글을 삭제하고 목록 페이지로 리다이렉트한다")
    void DELETE_delete_글을_삭제하고_list로_리다이렉트한다() throws Exception {
        String redirectUrl = mockMvc.perform(post("/create")
                        .param("title", "[통합테스트] 삭제용 제목")
                        .param("content_body", "[통합테스트] 삭제용 내용"))
                .andReturn()
                .getResponse()
                .getRedirectedUrl();

        assert redirectUrl != null;
        String seq = redirectUrl.replace("/read/", "");

        mockMvc.perform(delete("/delete")
                        .param("blogContSeq", seq))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));
    }
}
