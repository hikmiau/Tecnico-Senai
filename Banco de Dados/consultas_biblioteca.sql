USE biblioteca_escola;

-- 1. Qual categoria possui mais livros cadastrados na biblioteca?
SELECT 
    c.nome AS categoria,
    COUNT(l.isbn) AS quantidade_livros
FROM categoria c
JOIN livro l ON c.id = l.categoria_id
GROUP BY c.id, c.nome
ORDER BY quantidade_livros DESC
LIMIT 1;

-- 2. Quantos alunos estão cadastrados na biblioteca?
SELECT 
    COUNT(*) AS total_alunos_cadastrados
FROM aluno;

-- 3. Qual é o livro com o ano de publicação mais antigo cadastrado na biblioteca?
SELECT 
    titulo,
    ano_publicacao
FROM livro
ORDER BY ano_publicacao ASC
LIMIT 1;

-- 4. Quais funcionários ainda não registraram nenhum empréstimo?
SELECT 
    f.nome AS funcionario
FROM funcionario f
LEFT JOIN emprestimo e ON f.cpf = e.funcionario_cpf
WHERE e.id IS NULL;

-- 5. Quantos empréstimos cada aluno realizou? (Ordenado do maior para o menor)
SELECT 
    a.nome AS aluno,
    COUNT(e.id) AS total_emprestimos
FROM aluno a
LEFT JOIN emprestimo e ON a.id = e.aluno_id
GROUP BY a.id, a.nome
ORDER BY total_emprestimos DESC;