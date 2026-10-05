USE eventplus;

-- Categorias
INSERT INTO Categoria (nome) VALUES
('Tecnologia'),
('Música'),
('Esportes');

-- Organizadores
INSERT INTO Organizador (nome, email, telefone, descricao) VALUES
('Tech Eventos', 'tech@eventplus.com', '47999990001', 'Eventos de tecnologia'),
('Music Hall', 'music@eventplus.com', '47999990002', 'Eventos musicais'),
('Sport Center', 'sport@eventplus.com', '47999990003', 'Eventos esportivos');

-- Participantes
INSERT INTO Participante (nome, email, telefone, cidade) VALUES
('Ana Souza', 'ana@email.com', '47988880001', 'Blumenau'),
('Carlos Silva', 'carlos@email.com', '47988880002', 'Joinville'),
('Marina Costa', 'marina@email.com', '47988880003', 'Blumenau'),
('Lucas Alves', 'lucas@email.com', '47988880004', 'Itajaí'),
('Julia Mendes', 'julia@email.com', '47988880005', 'Gaspar');

-- Eventos
INSERT INTO Evento
(titulo, descricao, data_inicio, data_fim, valor, Categoria_id, Organizador_id)
VALUES
('Workshop de Programação',
 'Workshop sobre desenvolvimento de software',
 '2026-10-15',
 '2026-10-15',
 50.00,
 1,
 1),

('Feira de Tecnologia',
 'Feira com novidades da área de tecnologia',
 '2026-11-05',
 '2026-11-07',
 80.00,
 1,
 1),

('Festival de Música',
 'Festival com apresentações musicais',
 '2026-11-20',
 '2026-11-21',
 100.00,
 2,
 2),

('Campeonato de Futsal',
 'Campeonato entre equipes da região',
 '2026-12-02',
 '2026-12-03',
 30.00,
 3,
 3);

-- Inscrições
INSERT INTO Inscricao
(data_inscricao, presenca, Evento_id, Participante_id)
VALUES
('2026-10-01', 1, 1, 1),
('2026-10-02', 1, 1, 2),
('2026-10-03', 0, 1, 3),
('2026-10-20', 1, 2, 1),
('2026-10-21', 0, 2, 4),
('2026-11-01', 1, 3, 5),
('2026-11-10', 1, 4, 2);