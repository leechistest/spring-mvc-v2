package org.example.blog.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BlogDAO {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    public int insert(Map<String, Object> map) {
        Integer blogContSeq = this.sqlSessionTemplate.selectOne("TB_BLG_CONT.insert", map);
        return blogContSeq != null ? blogContSeq : -1;
    }

    public Map<String, Object> selectOne(int blogContSeq) {
        return this.sqlSessionTemplate.selectOne("TB_BLG_CONT.selectOne", blogContSeq);
    }

}

