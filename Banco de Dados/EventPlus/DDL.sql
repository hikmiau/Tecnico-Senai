CREATE DATABASE eventplus;
USE eventplus;

CREATE TABLE Categoria (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (nome)
);

CREATE TABLE Organizador (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    descricao VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE Participante (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cidade VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE Evento (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    Categoria_id INT NOT NULL,
    Organizador_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (Categoria_id) REFERENCES Categoria(id),
    FOREIGN KEY (Organizador_id) REFERENCES Organizador(id)
);

CREATE TABLE Inscricao (
    id INT NOT NULL AUTO_INCREMENT,
    data_inscricao DATE NOT NULL,
    presenca TINYINT NOT NULL,
    Evento_id INT NOT NULL,
    Participante_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (Evento_id, Participante_id),
    FOREIGN KEY (Evento_id) REFERENCES Evento(id),
    FOREIGN KEY (Participante_id) REFERENCES Participante(id)
);