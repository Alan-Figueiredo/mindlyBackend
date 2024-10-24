
CREATE TABLE tb_profissional(
cpf_prof VARCHAR (20)  NOT NULL UNIQUE,
nome_prof VARCHAR(155) NOT NULL,
crp VARCHAR(10) NOT NULL UNIQUE,
email_prof VARCHAR(155) NOT NULL UNIQUE,
senha VARCHAR(255) NOT NULL,
descricao_prof VARCHAR(500),
especialidade VARCHAR(255),
endereco_prof VARCHAR(255),
telefone_prof VARCHAR(20),
PRIMARY KEY (cpf_prof)
);

CREATE TABLE tb_paciente (
	cpf_paciente VARCHAR(11) NOT NULL,
    nome_paciente VARCHAR(155) NOT NULL,
    email_paciente VARCHAR(155) NOT NULL UNIQUE,
	senha VARCHAR(255) NOT NULL,
	nascimento DATE NOT NULL,
	medicacao VARCHAR(255),
	endereco_paciente VARCHAR(255),
	telefone_paciente VARCHAR(20),
	PRIMARY KEY (cpf_paciente)
);

CREATE TABLE tb_agenda (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	dia_da_semana VARCHAR(15) NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_fim TIME NOT NULL,
	duracao INT NOT NULL ,
	ativo TINYINT(1) DEFAULT 1,
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof)
);

CREATE TABLE tb_agendamento (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	cpf_paciente VARCHAR(11) NOT NULL,
    data_agendamento DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	duracao INT NOT NULL,
	link_video VARCHAR(255),
	lembrete_enviado TINYINT(1) DEFAULT 0,
	observacoes VARCHAR(500),
	status ENUM('PENDENTE', 'APROVADO', 'RECUSADO', 'CANCELADO') NOT NULL DEFAULT 'PENDENTE',
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof),
	FOREIGN KEY (cpf_paciente) REFERENCES tb_paciente(cpf_paciente)
);

CREATE TABLE tb_sessao (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	cpf_paciente VARCHAR(11) NOT NULL,
    id_agendamento INT NOT NULL,
	data_sessao DATE NOT NULL,
    quantidade_total INT NOT NULL DEFAULT 0,
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof),
	FOREIGN KEY (cpf_paciente) REFERENCES tb_paciente(cpf_paciente),
	FOREIGN KEY (id_agendamento) REFERENCES tb_agendamento(id)
);

CREATE VIEW vw_profissional_publico AS
SELECT
	cpf_prof,
	nome_prof,
	email_prof,
	especialidade,
	descricao_prof,
    telefone_prof
FROM
    tb_profissional;
