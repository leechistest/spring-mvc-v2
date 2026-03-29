package org.example.blog.vo;

public class BlogEditRequestVO {
    private int blgContSeq;
    private String title;
    private String contBdy;

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
}
