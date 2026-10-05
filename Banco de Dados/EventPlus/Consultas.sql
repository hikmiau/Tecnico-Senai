USE eventplus;

-- Listar todos os eventos
SELECT *
FROM Evento;


-- Consultar eventos por categoria
SELECT Evento.titulo, Categoria.nome AS categoria
FROM Evento
JOIN Categoria ON Evento.Categoria_id = Categoria.id
WHERE Categoria.nome = 'Tecnologia';


-- Identificar o organizador de cada evento
SELECT Evento.titulo, Organizador.nome AS organizador
FROM Evento
JOIN Organizador ON Evento.Organizador_id = Organizador.id;


-- Listar os participantes de um evento
SELECT Participante.nome, Participante.email
FROM Participante
JOIN Inscricao
    ON Participante.id = Inscricao.Participante_id
JOIN Evento
    ON Inscricao.Evento_id = Evento.id
WHERE Evento.titulo = 'Workshop de Programação';


-- Consultar as inscrições realizadas
SELECT
    Inscricao.id,
    Participante.nome AS participante,
    Evento.titulo AS evento,
    Inscricao.data_inscricao
FROM Inscricao
JOIN Participante
    ON Inscricao.Participante_id = Participante.id
JOIN Evento
    ON Inscricao.Evento_id = Evento.id;


-- Verificar a presença dos participantes
SELECT
    Participante.nome,
    Evento.titulo AS evento,
    Inscricao.presenca
FROM Inscricao
JOIN Participante
    ON Inscricao.Participante_id = Participante.id
JOIN Evento
    ON Inscricao.Evento_id = Evento.id;


-- Mostrar apenas quem esteve presente
SELECT
    Participante.nome,
    Evento.titulo AS evento
FROM Inscricao
JOIN Participante
    ON Inscricao.Participante_id = Participante.id
JOIN Evento
    ON Inscricao.Evento_id = Evento.id
WHERE Inscricao.presenca = 1;


-- Quantidade de inscrições por evento
SELECT
    Evento.titulo,
    COUNT(Inscricao.id) AS quantidade_inscricoes
FROM Evento
LEFT JOIN Inscricao
    ON Evento.id = Inscricao.Evento_id
GROUP BY Evento.id, Evento.titulo;


-- Combinar informações de várias tabelas
SELECT
    Evento.titulo AS evento,
    Categoria.nome AS categoria,
    Organizador.nome AS organizador,
    COUNT(Inscricao.id) AS inscricoes
FROM Evento
JOIN Categoria
    ON Evento.Categoria_id = Categoria.id
JOIN Organizador
    ON Evento.Organizador_id = Organizador.id
LEFT JOIN Inscricao
    ON Evento.id = Inscricao.Evento_id
GROUP BY Evento.id, Evento.titulo, Categoria.nome, Organizador.nome;