CREATE SCHEMA IF NOT EXISTS modelo_recuperacao;
USE modelo_recuperacao;

CREATE TABLE cliente (
    id INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200),
    PRIMARY KEY (id),
    UNIQUE (cpf)
);

CREATE TABLE equipamento (
    id INT NOT NULL,
    cliente_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    numero_serie VARCHAR(50) NOT NULL,
    descricao VARCHAR(200),
    status VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (numero_serie),
    FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
);

CREATE TABLE ordem_servico (
    id INT NOT NULL,
    cliente_id INT NOT NULL,
    equipamento_id INT NOT NULL,
    data_abertura DATE NOT NULL,
    data_fechamento DATE,
    descricao VARCHAR(300) NOT NULL,
    status VARCHAR(30) NOT NULL,
    prioridade VARCHAR(25) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id)
        REFERENCES cliente(id),
    FOREIGN KEY (equipamento_id)
        REFERENCES equipamento(id)
);