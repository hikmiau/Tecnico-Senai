USE biblioteca_escola;

INSERT INTO autor (id, nome, nacionalidade, data_nascimento) VALUES
(1, 'Machado de Assis', 'Brasileiro', '1839-06-21'),
(2, 'Clarice Lispector', 'Brasileira', '1920-12-10'),
(3, 'George Orwell', 'Britânico', '1903-06-25'),
(4, 'Isaac Asimov', 'Norte-Americano', '1920-01-02');

INSERT INTO categoria (id, nome, descricao) VALUES
(1, 'Tecnologia', 'Livros sobre tecnologia e computação'),
(2, 'Literatura', 'Obras de ficção e romances'),
(3, 'História', 'Livros de história geral e do Brasil'),
(4, 'Ciências', 'Livros sobre ciências naturais e exatas');

INSERT INTO livro (isbn, titulo, ano_publicacao, categoria_id) VALUES
('9788535902778', 'Dom Casmurro', '1899-01-01', 2),
('9788535914849', 'A Hora da Estrela', '1977-01-01', 2),
('9788535909555', '1984', '1949-01-01', 2),
('9788576572008', 'Eu, Robô', '1950-01-01', 1),
('9788576573005', 'Fundação', '1951-01-01', 1),
('9788535910667', 'Memórias Póstumas de Brás Cubas', '1881-01-01', 2);

INSERT INTO autor_livro (autor_id, livro_isbn) VALUES
(1, '9788535902778'),
(2, '9788535914849'),
(3, '9788535909555'),
(4, '9788576572008'),
(4, '9788576573005'),
(1, '9788535910667');

INSERT INTO aluno (id, matricula, nome, email, telefone) VALUES
(1, '2026001', 'João Silva', 'joao.blumenau@email.com', '47999990001'),
(2, '2026002', 'Maria Santos', 'maria.blumenau@email.com', '47999990002'),
(3, '2026003', 'Carlos Oliveira', 'carlos@email.com', '48999990003'),
(4, '2026004', 'Ana Souza', 'ana@email.com', '47999990004'),
(5, '2026005', 'Pedro Rocha', 'pedro@email.com', '47999990005'),
(6, '2026006', 'Fernanda Lima', 'fernanda@email.com', '47999990006');

INSERT INTO funcionario (cpf, nome, cargo_ou_turno) VALUES
('11122233344', 'Roberto Alves', 'Manhã'),
('55566677788', 'Patricia Costa', 'Tarde');

INSERT INTO emprestimo (id, data_emprestimo, data_devolucao_prevista, aluno_id, funcionario_cpf) VALUES
(1, '2026-02-01', '2026-02-15', 1, '11122233344'),
(2, '2026-02-02', '2026-02-16', 2, '11122233344'),
(3, '2026-02-03', '2026-02-17', 3, '55566677788'),
(4, '2026-02-04', '2026-02-18', 4, '55566677788'),
(5, '2026-02-05', '2026-02-19', 5, '11122233344'),
(6, '2026-02-06', '2026-02-20', 6, '55566677788');

INSERT INTO livro_emprestimo (livro_isbn, emprestimo_id) VALUES
('9788535902778', 1),
('9788535914849', 2),
('9788535909555', 3),
('9788576572008', 4),
('9788576573005', 5),
('9788535910667', 6);

SELECT * FROM livro;

SELECT * FROM livro WHERE categoria_id = 2;

SELECT * FROM aluno WHERE email LIKE '%blumenau%';

SELECT * FROM emprestimo;

SELECT * FROM livro ORDER BY ano_publicacao ASC;

SELECT * FROM livro ORDER BY ano_publicacao DESC;

SELECT * FROM categoria WHERE id = 1;

UPDATE categoria SET nome = 'Tecnologia e Inovação' WHERE id = 1;

SELECT * FROM categoria WHERE id = 1;

INSERT INTO livro (isbn, titulo, ano_publicacao, categoria_id) VALUES
('9780000000000', 'Livro Teste Exclusao', '2024-01-01', 1);

SELECT * FROM livro WHERE isbn = '9780000000000';

DELETE FROM livro WHERE isbn = '9780000000000';

SELECT * FROM livro WHERE isbn = '9780000000000';

SELECT l.isbn, l.titulo, l.ano_publicacao, c.nome AS categoria, a.nome AS autor
FROM livro l
JOIN categoria c ON l.categoria_id = c.id
JOIN autor_livro al ON l.isbn = al.livro_isbn
JOIN autor a ON al.autor_id = a.id;
