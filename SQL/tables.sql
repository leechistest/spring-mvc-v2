CREATE TABLE blog.tb_blg_cont
(
    blg_cont_seq int4 GENERATED ALWAYS AS IDENTITY   NOT NULL,
    title        text                                NOT NULL,
    cont_bdy     text                                NOT NULL,
    insert_dt    timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT tb_blg_cont_pk PRIMARY KEY (blg_cont_seq)
);

CREATE TABLE blog.tb_blg_cmt
(
    blg_cmt_seq  int4 GENERATED ALWAYS AS IDENTITY   NOT NULL,
    blg_cont_seq int4                                NOT NULL,
    cmt_bdy      text                                NULL,
    tmp_pw       varchar                             NULL,
    insert_dt    timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT tb_blg_cmt_pk PRIMARY KEY (blg_cmt_seq),
    CONSTRAINT tb_blg_cmt_tb_blg_cont_fk FOREIGN KEY (blg_cont_seq) REFERENCES blog.tb_blg_cont (blg_cont_seq)
);