package org.example.blog.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlogListResponseVO {
    private int blgContSeq;
    private String title;
    private String contBdy;
    private LocalDateTime insertDt;

    public int getBlgContSeq() {
        return blgContSeq;
    }

    public void setBlgContSeq(int blgContSeq) {
        this.blgContSeq = blgContSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContBdy() {
        return contBdy;
    }

    public void setContBdy(String contBdy) {
        this.contBdy = contBdy;
    }

    public LocalDateTime getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(LocalDateTime insertDt) {
        this.insertDt = insertDt;
    }

    public String getInsertDtFormat() {
        return this.insertDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
