package org.example.blog.vo;

public class CommentInsertVO {
    private int blgContSeq;
    private String cmtBdy;
    private String tmpPw;
    private int seqBlgCmt;

    public int getBlgContSeq() {
        return blgContSeq;
    }

    public void setBlgContSeq(int blgContSeq) {
        this.blgContSeq = blgContSeq;
    }

    public String getCmtBdy() {
        return cmtBdy;
    }

    public void setCmtBdy(String cmtBdy) {
        this.cmtBdy = cmtBdy;
    }

    public String getTmpPw() {
        return tmpPw;
    }

    public void setTmpPw(String tmpPw) {
        this.tmpPw = tmpPw;
    }

    public int getSeqBlgCmt() {
        return seqBlgCmt;
    }

    public void setSeqBlgCmt(int seqBlgCmt) {
        this.seqBlgCmt = seqBlgCmt;
    }
}
