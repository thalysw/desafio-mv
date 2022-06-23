CREATE TABLE TB_ESTADO (
	ID_ESTADO SERIAL NOT NULL,
	CD_ESTADO VARCHAR(2) NOT NULL,
	NM_ESTADO VARCHAR(250) NOT NULL,

	constraint pk_ID_ESTADO primary key (ID_ESTADO)
);

INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "PB", "Paraiba");
INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "PE", "Pernambuco");
INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "RN", "Rio Grande do Norte");
INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "RS", "Rio Grande do Sul");
INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "BA", "Bahia");
INSERT INTO TB_ESTADO(id_estado, cd_estado, nm_estado) VALUES (nextval('seq_tb_estado'), "AL", "Alagoas");