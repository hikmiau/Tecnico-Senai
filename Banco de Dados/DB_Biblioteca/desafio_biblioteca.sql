-- Biblioteca DB

-- DDL
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
    ano_publicacao SMALLINT NOT NULL,
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

-- DML

INSERT INTO Categoria (id, nome, descricao) VALUES
(1, 'Literatura', 'Obras literarias e romances.'),
(2, 'Tecnologia', 'Livros sobre informatica e tecnologia.'),
(3, 'História', 'Obras sobre fatos e periodos historicos.'),
(4, 'Ciências', 'Livros de ciencias e conhecimentos naturais.'),
(5, 'Educação', 'Obras sobre ensino e aprendizagem.');

INSERT INTO Editora (id, nome, cidade, email) VALUES
(1, 'Editora Horizonte', 'São Paulo', 'contato@horizonte.com.br'),
(2, 'Ponto do Livro', 'Rio de Janeiro', 'contato@pontodolivro.com.br'),
(3, 'Saber Editora', 'Curitiba', 'contato@saber.com.br'),
(4, 'Conhecimento', 'Belo Horizonte', 'contato@conhecimento.com.br'),
(5, 'Nova Página', 'Florianópolis', 'contato@novapagina.com.br'),
(6, 'Editora Caminhos', 'Porto Alegre', 'contato@caminhos.com.br');

INSERT INTO Autor (id, nome, pais_origem, data_nascimento) VALUES
(1, 'Machado de Assis', 'Brasil', '1839-06-21'),
(2, 'Aluísio Azevedo', 'Brasil', '1857-04-14'),
(3, 'Clarice Lispector', 'Ucrânia', '1920-12-10'),
(4, 'Boris Fausto', 'Brasil', '1930-12-08'),
(5, 'Eric Hobsbawm', 'Egito', '1917-06-09'),
(6, 'Antony Beevor', 'Reino Unido', '1946-12-14'),
(7, 'Amabis', 'Brasil', NULL),
(8, 'Martho', 'Brasil', NULL),
(9, 'Carl Sagan', 'Estados Unidos', '1934-11-09'),
(10, 'Martha Reis', 'Brasil', NULL),
(11, 'Andrew Tanenbaum', 'Estados Unidos', '1944-03-16'),
(12, 'Marina Lopes', 'Brasil', NULL);

INSERT INTO Leitor (id, nome, email, telefone, cidade, data_cadastro) VALUES
(1, 'Ana Souza', 'ana.souza@email.com', '47990000001', 'Blumenau', '2026-01-10'),
(2, 'Bruno Lima', 'bruno.lima@email.com', '47990000002', 'Gaspar', '2026-01-12'),
(3, 'Carla Mendes', 'carla.mendes@email.com', '47990000003', 'Blumenau', '2026-01-15'),
(4, 'Daniel Rocha', 'daniel.rocha@email.com', '47990000004', 'Indaial', '2026-01-18'),
(5, 'Eduarda Alves', 'eduarda.alves@email.com', '47990000005', 'Blumenau', '2026-02-02'),
(6, 'Felipe Costa', 'felipe.costa@email.com', '47990000006', 'Timbó', '2026-02-05'),
(7, 'Giovana Martins', 'giovana.martins@email.com', '47990000007', 'Blumenau', '2026-02-09'),
(8, 'Henrique Melo', 'henrique.melo@email.com', '47990000008', 'Gaspar', '2026-02-12'),
(9, 'Isabela Ferreira', 'isabela.ferreira@email.com', '47990000009', 'Indaial', '2026-02-20'),
(10, 'João Pedro', 'joao.pedro@email.com', '47990000010', 'Blumenau', '2026-03-01'),
(11, 'Karen Ribeiro', 'karen.ribeiro@email.com', '47990000011', 'Timbó', '2026-03-04'),
(12, 'Lucas Almeida', 'lucas.almeida@email.com', '47990000012', 'Blumenau', '2026-03-08'),
(13, 'Mariana Santos', 'mariana.santos@email.com', '47990000013', 'Gaspar', '2026-03-12'),
(14, 'Nicolas Barros', 'nicolas.barros@email.com', '47990000014', 'Indaial', '2026-03-15'),
(15, 'Olivia Cardoso', 'olivia.cardoso@email.com', '47990000015', 'Blumenau', '2026-03-20');

INSERT INTO Funcionario (id, nome, email, matricula) VALUES
(1, 'Paulo Nunes', 'paulo.nunes@biblioteca.com.br', 'FUNC001'),
(2, 'Renata Dias', 'renata.dias@biblioteca.com.br', 'FUNC002'),
(3, 'Sergio Martins', 'sergio.martins@biblioteca.com.br', 'FUNC003'),
(4, 'Tatiana Lopes', 'tatiana.lopes@biblioteca.com.br', 'FUNC004'),
(5, 'Victor Freitas', 'victor.freitas@biblioteca.com.br', 'FUNC005');

INSERT INTO Livro (id, titulo, isbn, ano_publicacao, Editora_id, Categoria_id) VALUES
(1, 'Dom Casmurro', '9788535902778', 1899, 1, 1),
(2, 'O Cortiço', '9788572326972', 1890, 2, 1),
(3, 'Memórias Póstumas de Brás Cubas', '9788535910668', 1881, 1, 1),
(4, 'A Hora da Estrela', '9788532508126', 1977, 3, 1),
(5, 'História do Brasil', '9788508105947', 1994, 4, 3),
(6, 'A Segunda Guerra Mundial', '9788520924574', 2012, 4, 3),
(7, 'A Era das Revoluções', '9788520004673', 1962, 5, 3),
(8, 'Introdução à Biologia', '9788508123456', 2015, 2, 4),
(9, 'O Universo em Expansão', '9788571234567', 1980, 3, 4),
(10, 'Química no Cotidiano', '9788508123982', 2018, 5, 4),
(11, 'Fundamentos de Computação', '9788535220000', 2010, 1, 2),
(12, 'Redes de Computadores', '9788543000000', 2014, 2, 2),
(13, 'Aprender a Estudar', '9788521600000', 2001, 3, 5),
(14, 'Práticas de Ensino', '9788521610000', 2008, 4, 5),
(15, 'Guia de Leitura', '9788521620000', 2020, 5, 5);

INSERT INTO Autor_Livro (Autor_id, Livro_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(1, 3),
(3, 4),
(4, 5),
(6, 6),
(5, 6),
(5, 7),
(7, 8),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(11, 12),
(4, 13),
(8, 14),
(9, 15),
(1, 2),
(7, 9);

INSERT INTO Exemplar (id, codigo_patrimonio, status, Livro_id) VALUES
(1, 'BIB-0001', 'EMPRESTADO', 1),
(2, 'BIB-0002', 'EMPRESTADO', 1),
(3, 'BIB-0003', 'EMPRESTADO', 1),
(4, 'BIB-0004', 'EMPRESTADO', 1),
(5, 'BIB-0005', 'EMPRESTADO', 1),
(6, 'BIB-0006', 'DISPONIVEL', 2),
(7, 'BIB-0007', 'DISPONIVEL', 2),
(8, 'BIB-0008', 'DISPONIVEL', 3),
(9, 'BIB-0009', 'DISPONIVEL', 4),
(10, 'BIB-0010', 'DISPONIVEL', 5),
(11, 'BIB-0011', 'EMPRESTADO', 6),
(12, 'BIB-0012', 'EMPRESTADO', 7),
(13, 'BIB-0013', 'EMPRESTADO', 8),
(14, 'BIB-0014', 'EMPRESTADO', 9),
(15, 'BIB-0015', 'EMPRESTADO', 10),
(16, 'BIB-0016', 'EMPRESTADO', 11),
(17, 'BIB-0017', 'EMPRESTADO', 12),
(18, 'BIB-0018', 'EMPRESTADO', 13),
(19, 'BIB-0019', 'EMPRESTADO', 14),
(20, 'BIB-0020', 'EMPRESTADO', 14),
(21, 'BIB-0021', 'DISPONIVEL', 15),
(22, 'BIB-0022', 'RESERVADO', 15),
(23, 'BIB-0023', 'RESERVADO', 15),
(24, 'BIB-0024', 'INATIVO', 15),
(25, 'BIB-0025', 'DISPONIVEL', 15);

INSERT INTO Emprestimo (id, data_emprestimo, data_prevista_devolucao, data_devolucao, status, Leitor_id, Funcionario_id) VALUES
(1, '2026-09-01', '2026-09-15', '2026-09-12', 'DEVOLVIDO', 1, 1),
(2, '2026-09-04', '2026-09-18', '2026-09-17', 'DEVOLVIDO', 2, 1),
(3, '2026-09-05', '2026-09-19', '2026-09-19', 'DEVOLVIDO', 3, 1),
(4, '2026-09-08', '2026-09-22', '2026-09-20', 'DEVOLVIDO', 4, 1),
(5, '2026-09-10', '2026-09-24', '2026-09-24', 'DEVOLVIDO', 1, 1),
(6, '2026-09-12', '2026-09-26', '2026-09-25', 'DEVOLVIDO', 5, 2),
(7, '2026-09-14', '2026-09-28', '2026-09-27', 'DEVOLVIDO', 6, 2),
(8, '2026-09-16', '2026-09-30', '2026-09-29', 'DEVOLVIDO', 7, 2),
(9, '2026-09-18', '2026-10-02', '2026-10-01', 'DEVOLVIDO', 8, 2),
(10, '2026-09-20', '2026-10-04', '2026-10-03', 'DEVOLVIDO', 9, 2),
(11, '2026-11-01', '2026-11-15', NULL, 'ATIVO', 1, 3),
(12, '2026-11-02', '2026-11-16', NULL, 'ATIVO', 2, 3),
(13, '2026-11-03', '2026-11-17', NULL, 'ATIVO', 3, 3),
(14, '2026-11-04', '2026-11-18', NULL, 'ATIVO', 4, 3),
(15, '2026-11-05', '2026-11-19', NULL, 'ATIVO', 5, 3),
(16, '2026-10-15', '2026-10-29', NULL, 'ATRASADO', 1, 4),
(17, '2026-10-16', '2026-10-30', NULL, 'ATRASADO', 2, 4),
(18, '2026-10-17', '2026-10-31', NULL, 'ATRASADO', 3, 4),
(19, '2026-10-18', '2026-11-01', NULL, 'ATRASADO', 4, 4),
(20, '2026-10-19', '2026-11-02', NULL, 'ATRASADO', 5, 4);

INSERT INTO Item_Emprestimo (id, Emprestimo_id, Exemplar_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 11, 1),
(13, 12, 12),
(14, 12, 2),
(15, 13, 13),
(16, 13, 3),
(17, 14, 14),
(18, 14, 4),
(19, 15, 15),
(20, 15, 5),
(21, 16, 16),
(22, 17, 17),
(23, 18, 18),
(24, 19, 19),
(25, 20, 20);

INSERT INTO Reserva (id, data_reserva, status, Livro_id, Leitor_id) VALUES
(1, '2026-10-01', 'PENDENTE', 1, 1),
(2, '2026-10-02', 'ATENDIDA', 2, 2),
(3, '2026-10-03', 'CANCELADA', 3, 3),
(4, '2026-10-04', 'PENDENTE', 4, 4),
(5, '2026-10-05', 'ATENDIDA', 5, 5),
(6, '2026-10-06', 'CANCELADA', 6, 6),
(7, '2026-10-07', 'PENDENTE', 7, 7),
(8, '2026-10-08', 'ATENDIDA', 8, 8),
(9, '2026-10-09', 'PENDENTE', 9, 9),
(10, '2026-10-10', 'CANCELADA', 10, 10),
(11, '2026-10-11', 'PENDENTE', 11, 11),
(12, '2026-10-12', 'PENDENTE', 15, 12);

-- Consultas

-- 1 - Livros e categorias
SELECT Livro.titulo, Livro.ano_publicacao, Categoria.nome AS categoria
FROM Livro
INNER JOIN Categoria ON Livro.Categoria_id = Categoria.id;

-- 2 - Livros e editoras
SELECT Livro.titulo, Livro.isbn, Editora.nome AS editora, Editora.cidade
FROM Livro
INNER JOIN Editora ON Livro.Editora_id = Editora.id;

-- 3 - Livros e autores
SELECT Livro.titulo, Autor.nome AS autor
FROM Livro
INNER JOIN Autor_Livro ON Livro.id = Autor_Livro.Livro_id
INNER JOIN Autor ON Autor_Livro.Autor_id = Autor.id
ORDER BY Livro.titulo ASC, Autor.nome ASC;

-- 4 - Acervo completo
SELECT Livro.titulo, Categoria.nome AS categoria, Editora.nome AS editora,
       GROUP_CONCAT(Autor.nome ORDER BY Autor.nome SEPARATOR ', ') AS autores
FROM Livro
INNER JOIN Categoria ON Livro.Categoria_id = Categoria.id
INNER JOIN Editora ON Livro.Editora_id = Editora.id
LEFT JOIN Autor_Livro ON Livro.id = Autor_Livro.Livro_id
LEFT JOIN Autor ON Autor_Livro.Autor_id = Autor.id
GROUP BY Livro.id, Livro.titulo, Categoria.nome, Editora.nome
ORDER BY Livro.titulo;

-- 5 - Exemplares por livro
SELECT Livro.titulo, Exemplar.codigo_patrimonio, Exemplar.status
FROM Livro
INNER JOIN Exemplar ON Livro.id = Exemplar.Livro_id
ORDER BY Livro.titulo, Exemplar.codigo_patrimonio;

-- 6 - Quantidade de emprestimos por leitor
SELECT Leitor.nome, COUNT(Emprestimo.id) AS quantidade_emprestimos
FROM Leitor
LEFT JOIN Emprestimo ON Leitor.id = Emprestimo.Leitor_id
GROUP BY Leitor.id, Leitor.nome
ORDER BY quantidade_emprestimos DESC, Leitor.nome;

-- 7 - Leitores sem emprestimos
SELECT Leitor.id, Leitor.nome
FROM Leitor
LEFT JOIN Emprestimo ON Leitor.id = Emprestimo.Leitor_id
WHERE Emprestimo.id IS NULL;

-- 8 - Quantidade de livros por categoria
SELECT Categoria.nome AS categoria, COUNT(Livro.id) AS quantidade_livros
FROM Categoria
LEFT JOIN Livro ON Categoria.id = Livro.Categoria_id
GROUP BY Categoria.id, Categoria.nome
ORDER BY quantidade_livros DESC;

-- 9 - Categoria com mais livros
SELECT Categoria.nome AS categoria, COUNT(Livro.id) AS quantidade_livros
FROM Categoria
LEFT JOIN Livro ON Categoria.id = Livro.Categoria_id
GROUP BY Categoria.id, Categoria.nome
ORDER BY quantidade_livros DESC
LIMIT 1;

-- 10 - Editoras e titulos publicados
SELECT Editora.nome AS editora, COUNT(Livro.id) AS quantidade_livros
FROM Editora
LEFT JOIN Livro ON Editora.id = Livro.Editora_id
GROUP BY Editora.id, Editora.nome
ORDER BY Editora.nome;

-- 11 - Editoras sem livros
SELECT Editora.id, Editora.nome
FROM Editora
LEFT JOIN Livro ON Editora.id = Livro.Editora_id
WHERE Livro.id IS NULL;

-- 12 - Livro mais antigo
SELECT titulo, ano_publicacao
FROM Livro
WHERE ano_publicacao = (SELECT MIN(ano_publicacao) FROM Livro);

-- 13 - Livros nunca emprestados
SELECT Livro.id, Livro.titulo
FROM Livro
LEFT JOIN Exemplar ON Livro.id = Exemplar.Livro_id
LEFT JOIN Item_Emprestimo ON Exemplar.id = Item_Emprestimo.Exemplar_id
GROUP BY Livro.id, Livro.titulo
HAVING COUNT(Item_Emprestimo.id) = 0;

-- 14 - Itens por emprestimo
SELECT Emprestimo.id AS numero_emprestimo, Leitor.nome AS leitor,
       COUNT(Item_Emprestimo.id) AS quantidade_exemplares
FROM Emprestimo
INNER JOIN Leitor ON Emprestimo.Leitor_id = Leitor.id
LEFT JOIN Item_Emprestimo ON Emprestimo.id = Item_Emprestimo.Emprestimo_id
GROUP BY Emprestimo.id, Leitor.nome
ORDER BY Emprestimo.id;

-- 15 - Livros mais emprestados
SELECT Livro.titulo, COUNT(Item_Emprestimo.id) AS vezes_emprestado
FROM Livro
LEFT JOIN Exemplar ON Livro.id = Exemplar.Livro_id
LEFT JOIN Item_Emprestimo ON Exemplar.id = Item_Emprestimo.Exemplar_id
GROUP BY Livro.id, Livro.titulo
ORDER BY vezes_emprestado DESC, Livro.titulo;

-- 16 - Emprestimos e funcionarios
SELECT Emprestimo.id AS numero_emprestimo, Leitor.nome AS leitor,
       Funcionario.nome AS funcionario, Emprestimo.data_emprestimo, Emprestimo.status
FROM Emprestimo
INNER JOIN Leitor ON Emprestimo.Leitor_id = Leitor.id
INNER JOIN Funcionario ON Emprestimo.Funcionario_id = Funcionario.id
ORDER BY Emprestimo.id;

-- 17 - Emprestimos sem devolucao
SELECT Emprestimo.id AS numero_emprestimo, Leitor.nome AS leitor,
       Emprestimo.data_prevista_devolucao, Emprestimo.status
FROM Emprestimo
INNER JOIN Leitor ON Emprestimo.Leitor_id = Leitor.id
WHERE Emprestimo.status IN ('ATIVO', 'ATRASADO')
ORDER BY Emprestimo.data_prevista_devolucao;

-- 18 - Historico de emprestimos
SELECT Leitor.nome AS leitor, Livro.titulo, Exemplar.codigo_patrimonio,
       Emprestimo.data_emprestimo, Emprestimo.data_devolucao
FROM Emprestimo
INNER JOIN Leitor ON Emprestimo.Leitor_id = Leitor.id
INNER JOIN Item_Emprestimo ON Emprestimo.id = Item_Emprestimo.Emprestimo_id
INNER JOIN Exemplar ON Item_Emprestimo.Exemplar_id = Exemplar.id
INNER JOIN Livro ON Exemplar.Livro_id = Livro.id
ORDER BY Emprestimo.data_emprestimo, Leitor.nome;

-- 19 - Livros sem exemplar disponivel
SELECT Livro.id, Livro.titulo
FROM Livro
LEFT JOIN Exemplar
    ON Livro.id = Exemplar.Livro_id
    AND Exemplar.status = 'DISPONIVEL'
WHERE Exemplar.id IS NULL;

-- 20 - Media de emprestimos por categoria
SELECT Categoria.nome AS categoria,
       ROUND(AVG(emprestimos_por_livro.quantidade_emprestimos), 2) AS media_emprestimos_por_titulo
FROM Categoria
INNER JOIN (
    SELECT Livro.id, Livro.Categoria_id, COUNT(Item_Emprestimo.id) AS quantidade_emprestimos
    FROM Livro
    INNER JOIN Exemplar ON Livro.id = Exemplar.Livro_id
    INNER JOIN Item_Emprestimo ON Exemplar.id = Item_Emprestimo.Exemplar_id
    GROUP BY Livro.id, Livro.Categoria_id
) AS emprestimos_por_livro
    ON Categoria.id = emprestimos_por_livro.Categoria_id
GROUP BY Categoria.id, Categoria.nome
ORDER BY Categoria.nome;

-- 21 - Quantidade de reservas por livro
SELECT Livro.titulo, COUNT(Reserva.id) AS quantidade_reservas
FROM Livro
LEFT JOIN Reserva ON Livro.id = Reserva.Livro_id
GROUP BY Livro.id, Livro.titulo
ORDER BY quantidade_reservas DESC, Livro.titulo;

-- 22 - Emprestimos por status
SELECT status, COUNT(id) AS quantidade
FROM Emprestimo
GROUP BY status
ORDER BY status;

-- 23 - Leitores com devolucoes atrasadas
SELECT Leitor.nome, COUNT(Emprestimo.id) AS quantidade_atrasados
FROM Leitor
INNER JOIN Emprestimo ON Leitor.id = Emprestimo.Leitor_id
WHERE Emprestimo.status = 'ATRASADO'
GROUP BY Leitor.id, Leitor.nome
ORDER BY quantidade_atrasados DESC, Leitor.nome;

-- 24 - Quantidade de emprestimos por funcionario
SELECT Funcionario.nome, COUNT(Emprestimo.id) AS quantidade_emprestimos
FROM Funcionario
LEFT JOIN Emprestimo ON Funcionario.id = Emprestimo.Funcionario_id
GROUP BY Funcionario.id, Funcionario.nome
ORDER BY quantidade_emprestimos DESC, Funcionario.nome;

-- 25 - Resumo por leitor
SELECT Leitor.nome,
       COALESCE(emprestimos.quantidade, 0) AS quantidade_emprestimos,
       COALESCE(reservas.quantidade, 0) AS quantidade_reservas
FROM Leitor
LEFT JOIN (
    SELECT Leitor_id, COUNT(id) AS quantidade
    FROM Emprestimo
    GROUP BY Leitor_id
) AS emprestimos ON Leitor.id = emprestimos.Leitor_id
LEFT JOIN (
    SELECT Leitor_id, COUNT(id) AS quantidade
    FROM Reserva
    GROUP BY Leitor_id
) AS reservas ON Leitor.id = reservas.Leitor_id
ORDER BY Leitor.nome;

-- 26 - Leitores com muitos emprestimos
SELECT Leitor.nome, COUNT(Emprestimo.id) AS quantidade_emprestimos
FROM Leitor
INNER JOIN Emprestimo ON Leitor.id = Emprestimo.Leitor_id
GROUP BY Leitor.id, Leitor.nome
HAVING COUNT(Emprestimo.id) > 2
ORDER BY quantidade_emprestimos DESC;

-- 27 - Categorias com varios titulos
SELECT Categoria.nome AS categoria, COUNT(Livro.id) AS quantidade_livros
FROM Categoria
INNER JOIN Livro ON Categoria.id = Livro.Categoria_id
GROUP BY Categoria.id, Categoria.nome
HAVING COUNT(Livro.id) > 2;

-- 28 - Livros com alta procura
SELECT Livro.titulo, COUNT(Item_Emprestimo.id) AS quantidade_emprestimos
FROM Livro
INNER JOIN Exemplar ON Livro.id = Exemplar.Livro_id
INNER JOIN Item_Emprestimo ON Exemplar.id = Item_Emprestimo.Exemplar_id
GROUP BY Livro.id, Livro.titulo
HAVING COUNT(Item_Emprestimo.id) >= 3
ORDER BY quantidade_emprestimos DESC;

-- 29 - Leitores com mais de um emprestimo ativo ou atrasado
SELECT Leitor.nome, COUNT(Emprestimo.id) AS quantidade_em_aberto
FROM Leitor
INNER JOIN Emprestimo ON Leitor.id = Emprestimo.Leitor_id
WHERE Emprestimo.status IN ('ATIVO', 'ATRASADO')
GROUP BY Leitor.id, Leitor.nome
HAVING COUNT(Emprestimo.id) > 1
ORDER BY quantidade_em_aberto DESC;

-- 30 - Relatorio geral do acervo
SELECT Categoria.nome AS categoria,
       COALESCE(livros.quantidade, 0) AS quantidade_livros,
       COALESCE(exemplares.quantidade, 0) AS quantidade_exemplares,
       COALESCE(emprestimos.quantidade, 0) AS quantidade_emprestimos,
       COALESCE(reservas.quantidade, 0) AS quantidade_reservas
FROM Categoria
LEFT JOIN (
    SELECT Categoria_id, COUNT(id) AS quantidade
    FROM Livro
    GROUP BY Categoria_id
) AS livros ON Categoria.id = livros.Categoria_id
LEFT JOIN (
    SELECT Livro.Categoria_id, COUNT(Exemplar.id) AS quantidade
    FROM Livro
    INNER JOIN Exemplar ON Livro.id = Exemplar.Livro_id
    GROUP BY Livro.Categoria_id
) AS exemplares ON Categoria.id = exemplares.Categoria_id
LEFT JOIN (
    SELECT Livro.Categoria_id, COUNT(DISTINCT Emprestimo.id) AS quantidade
    FROM Livro
    INNER JOIN Exemplar ON Livro.id = Exemplar.Livro_id
    INNER JOIN Item_Emprestimo ON Exemplar.id = Item_Emprestimo.Exemplar_id
    INNER JOIN Emprestimo ON Item_Emprestimo.Emprestimo_id = Emprestimo.id
    GROUP BY Livro.Categoria_id
) AS emprestimos ON Categoria.id = emprestimos.Categoria_id
LEFT JOIN (
    SELECT Livro.Categoria_id, COUNT(Reserva.id) AS quantidade
    FROM Livro
    INNER JOIN Reserva ON Livro.id = Reserva.Livro_id
    GROUP BY Livro.Categoria_id
) AS reservas ON Categoria.id = reservas.Categoria_id
ORDER BY quantidade_emprestimos DESC, Categoria.nome;

-- CONSULTAS COMPLEMENTARES
-- Ano de publicacao mais recente do acervo
SELECT MAX(ano_publicacao) AS ano_publicacao_mais_recente
FROM Livro;

-- Total de livros, somando as quantidades agrupadas por categoria
SELECT SUM(livros_por_categoria.quantidade_livros) AS total_livros
FROM (
    SELECT Categoria_id, COUNT(id) AS quantidade_livros
    FROM Livro
    GROUP BY Categoria_id
) AS livros_por_categoria;

-- EXPLICACAO DA QUESTAO 30
-- A consulta comeca pela tabela Categoria para mostrar todas as categorias.
-- Depois, relaciona cada categoria com os livros, os exemplares, os emprestimos e as reservas.
-- Cada parte que conta esses dados e agrupada por categoria antes de ser relacionada ao resultado principal.
-- Assim, as quantidades nao ficam multiplicadas quando uma categoria tem varios livros, exemplares ou reservas.
-- O LEFT JOIN mantem categorias mesmo quando nao existem registros relacionados.
-- O COALESCE mostra zero quando uma categoria nao possui registros, e o ORDER BY organiza pelo total de emprestimos.