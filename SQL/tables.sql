CREATE TABLE blog.tb_blg_cont
(
    blg_cont_seq int4 GENERATED ALWAYS AS IDENTITY   NOT NULL,
    title        text                                NOT NULL,
    cont_bdy     text                                NOT NULL,
    insert_dt    timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT tb_blg_cont_pk PRIMARY KEY (blg_cont_seq)
);