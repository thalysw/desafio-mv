CREATE TABLE TB_CIDADE (
	ID_CIDADE SERIAL NOT NULL,
	CD_ESTADO VARCHAR(2) NOT NULL,
	NM_CIDADE VARCHAR(250) NOT NULL,
    ID_ESTADO INTEGER NOT NULL,

    constraint fk_id_estado foreign key (ID_ESTADO) references TB_ESTADO(ID_ESTADO),
	constraint pk_ID_CIDADE primary key (ID_CIDADE)
);


INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PB", "Campina Grande", 1);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PB", "João Pessoa", 1);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PB", "Patos", 1);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PE", "Recife", 2);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PE", "Jaboatão dos Guararapes", 2);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "PE", "Limoeiro", 1);
INSERT INTO TB_CIDADE(ID_CIDADE, CD_ESTADO, NM_CIDADE, ID_ESTADO VALUES (nextval('seq_tb_cidade'), "RN", "Natal", 3);