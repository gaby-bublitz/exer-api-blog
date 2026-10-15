CREATE TABLE tb_comentario (
    id BINARY(16) NOT NULL,
    post_id BINARY(16) NOT NULL,
    comentario TEXT NOT NULL,
    data DATE NOT NULL,
    CONSTRAINT pk_tb_comentario PRIMARY KEY (id),
    CONSTRAINT fk_tb_comentario_posts FOREIGN KEY (post_id) REFERENCES tb_post(id) ON DELETE CASCADE
);