USE biblioteca;

INSERT INTO autores (id_autor, nome) VALUES
(1, 'Machado de Assis'),
(2, 'Clarice Lispector'),
(3, 'George Orwell'),
(4, 'Isaac Asimov');

INSERT INTO categorias (id_categoria, nome) VALUES
(1, 'Tecnologia'),
(2, 'Literatura'),
(3, 'História'),
(4, 'Ciências');

INSERT INTO livros (id_livro, titulo, ano_publicacao, id_autor, id_categoria) VALUES
(1, 'Dom Casmurro', 1899, 1, 2),
(2, 'A Hora da Estrela', 1977, 2, 2),
(3, '1984', 1949, 3, 2),
(4, 'Eu, Robô', 1950, 4, 1),
(5, 'Fundação', 1951, 4, 1),
(6, 'Memórias Póstumas de Brás Cubas', 1881, 1, 2);

INSERT INTO alunos (id_aluno, nome, cidade) VALUES
(1, 'João Silva', 'Blumenau'),
(2, 'Maria Santos', 'Blumenau'),
(3, 'Carlos Oliveira', 'Florianópolis'),
(4, 'Ana Souza', 'Gaspar'),
(5, 'Pedro Rocha', 'Indaial'),
(6, 'Fernanda Lima', 'Joinville');

INSERT INTO funcionarios (id_funcionario, nome) VALUES
(1, 'Roberto Alves'),
(2, 'Patricia Costa');

INSERT INTO emprestimos (id_emprestimo, id_aluno, id_livro, id_funcionario, data_emprestimo) VALUES
(1, 1, 1, 1, '2026-02-01'),
(2, 2, 2, 1, '2026-02-02'),
(3, 3, 3, 2, '2026-02-03'),
(4, 4, 4, 2, '2026-02-04'),
(5, 5, 5, 1, '2026-02-05'),
(6, 6, 6, 2, '2026-02-06');

SELECT * FROM livros;

SELECT * FROM livros WHERE id_categoria = 2;

SELECT * FROM alunos WHERE cidade = 'Blumenau';

SELECT * FROM emprestimos;

SELECT * FROM livros ORDER BY ano_publicacao ASC;

SELECT * FROM livros ORDER BY ano_publicacao DESC;

SELECT * FROM categorias WHERE id_categoria = 1;

UPDATE categorias SET nome = 'Tecnologia e Inovação' WHERE id_categoria = 1;

SELECT * FROM categorias WHERE id_categoria = 1;

INSERT INTO livros (id_livro, titulo, ano_publicacao, id_autor, id_categoria) VALUES
(7, 'Livro Teste Exclusao', 2024, 1, 1);

SELECT * FROM livros WHERE id_livro = 7;

DELETE FROM livros WHERE id_livro = 7;

SELECT * FROM livros WHERE id_livro = 7;

SELECT l.id_livro, l.titulo, a.nome AS autor, c.nome AS categoria
FROM livros l
JOIN autores a ON l.id_autor = a.id_autor
JOIN categorias c ON l.id_categoria = c.id_categoria;