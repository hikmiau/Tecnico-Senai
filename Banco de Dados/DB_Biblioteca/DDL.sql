CREATE DATABASE IF NOT EXISTS mydb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE mydb;

CREATE TABLE IF NOT EXISTS Categoria (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Editora (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Autor (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    pais_origem VARCHAR(100) NOT NULL,
    data_nascimento DATE NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Leitor (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefone VARCHAR(20) NULL,
    cidade VARCHAR(100) NULL,
    data_cadastro DATE NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Funcionario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    matricula VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (matricula)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Livro (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    ano_publicacao YEAR NOT NULL,
    Editora_id INT NOT NULL,
    Categoria_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (isbn),
    CONSTRAINT fk_Livro_Editora
        FOREIGN KEY (Editora_id) REFERENCES Editora (id),
    CONSTRAINT fk_Livro_Categoria
        FOREIGN KEY (Categoria_id) REFERENCES Categoria (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Autor_Livro (
    Autor_id INT NOT NULL,
    Livro_id INT NOT NULL,
    PRIMARY KEY (Autor_id, Livro_id),
    INDEX idx_Autor_Livro_Livro (Livro_id),
    CONSTRAINT fk_Autor_Livro_Autor
        FOREIGN KEY (Autor_id) REFERENCES Autor (id),
    CONSTRAINT fk_Autor_Livro_Livro
        FOREIGN KEY (Livro_id) REFERENCES Livro (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Exemplar (
    id INT NOT NULL AUTO_INCREMENT,
    codigo_patrimonio VARCHAR(30) NOT NULL,
    status ENUM('DISPONIVEL', 'EMPRESTADO', 'RESERVADO', 'INATIVO') NOT NULL,
    Livro_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (codigo_patrimonio),
    CONSTRAINT fk_Exemplar_Livro
        FOREIGN KEY (Livro_id) REFERENCES Livro (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Emprestimo (
    id INT NOT NULL AUTO_INCREMENT,
    data_emprestimo DATE NOT NULL,
    data_prevista_devolucao DATE NOT NULL,
    data_devolucao DATE NULL,
    status ENUM('ATIVO', 'DEVOLVIDO', 'ATRASADO') NOT NULL,
    Leitor_id INT NOT NULL,
    Funcionario_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Emprestimo_Leitor
        FOREIGN KEY (Leitor_id) REFERENCES Leitor (id),
    CONSTRAINT fk_Emprestimo_Funcionario
        FOREIGN KEY (Funcionario_id) REFERENCES Funcionario (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Reserva (
    id INT NOT NULL AUTO_INCREMENT,
    data_reserva DATE NOT NULL,
    status ENUM('PENDENTE', 'ATENDIDA', 'CANCELADA') NOT NULL,
    Livro_id INT NOT NULL,
    Leitor_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_Reserva_Livro
        FOREIGN KEY (Livro_id) REFERENCES Livro (id),
    CONSTRAINT fk_Reserva_Leitor
        FOREIGN KEY (Leitor_id) REFERENCES Leitor (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Item_Emprestimo (
    id INT NOT NULL AUTO_INCREMENT,
    Emprestimo_id INT NOT NULL,
    Exemplar_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (Emprestimo_id, Exemplar_id),
    CONSTRAINT fk_Item_Emprestimo_Emprestimo
        FOREIGN KEY (Emprestimo_id) REFERENCES Emprestimo (id),
    CONSTRAINT fk_Item_Emprestimo_Exemplar
        FOREIGN KEY (Exemplar_id) REFERENCES Exemplar (id)
) ENGINE = InnoDB;
