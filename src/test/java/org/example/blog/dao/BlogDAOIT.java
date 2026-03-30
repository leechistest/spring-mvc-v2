package org.example.blog.dao;

import org.example.blog.vo.BlogEditRequestVO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/db-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class BlogDAOIT {

    @Autowired
    private BlogDAO blogDAO;

    @Test
    @Order(1)
    void insert_실제DB에_저장하고_PK를_반환한다() {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "[통합테스트] 제목");
        param.put("content_body", "[통합테스트] 내용");

        int seq = blogDAO.insert(param);

        assertTrue(seq > 0, "RETURNING으로 반환된 PK가 0보다 커야 함");
    }

    @Test
    @Order(2)
    void insert_후_selectOne으로_조회할_수_있다() {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "[통합테스트] 조회용 제목");
        param.put("content_body", "[통합테스트] 조회용 내용");

        int seq = blogDAO.insert(param);
        Map<String, Object> result = blogDAO.selectOne(seq);

        assertNotNull(result);
        assertEquals("[통합테스트] 조회용 제목", result.get("title"));
        assertEquals("[통합테스트] 조회용 내용", result.get("cont_bdy"));
    }

    @Test
    @Order(3)
    void selectOne_존재하지_않는_seq는_null을_반환한다() {
        Map<String, Object> result = blogDAO.selectOne(-9999);

        assertNull(result);
    }

    @Test
    @Order(4)
    void update_제목과_내용을_수정할_수_있다() {
        Map<String, Object> param = new HashMap<>();
        param.put("title", "[통합테스트] 수정 전 제목");
        param.put("content_body", "[통합테스트] 수정 전 내용");
        int seq = blogDAO.insert(param);

        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(seq);
        vo.setTitle("[통합테스트] 수정 후 제목");
        vo.setContBdy("[통합테스트] 수정 후 내용");

        int updated = blogDAO.update(vo);
        Map<String, Object> result = blogDAO.selectOne(seq);

        assertEquals(1, updated);
        assertEquals("[통합테스트] 수정 후 제목", result.get("title"));
        assertEquals("[통합테스트] 수정 후 내용", result.get("cont_bdy"));
    }

    @Test
    @Order(5)
    void update_존재하지_않는_seq는_0을_반환한다() {
        BlogEditRequestVO vo = new BlogEditRequestVO();
        vo.setBlgContSeq(-9999);
        vo.setTitle("없는 제목");
        vo.setContBdy("없는 내용");

        int updated = blogDAO.update(vo);

        assertEquals(0, updated);
    }
}
