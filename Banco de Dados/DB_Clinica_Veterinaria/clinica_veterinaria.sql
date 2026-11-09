-- Clinica Veterinaria DB

-- DDL
CREATE DATABASE IF NOT EXISTS clinica_veterinaria
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE clinica_veterinaria;

CREATE TABLE IF NOT EXISTS categoria (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS tutor (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefone VARCHAR(20) NULL,
    cidade VARCHAR(100) NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS animal (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(60) NOT NULL,
    raca VARCHAR(100) NULL,
    data_nascimento DATE NULL,
    tutor_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_animal_tutor
        FOREIGN KEY (tutor_id) REFERENCES tutor (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS veterinario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    crmv VARCHAR(30) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_veterinario_crmv (crmv)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS servico (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    descricao TEXT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_servico_categoria
        FOREIGN KEY (categoria_id) REFERENCES categoria (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS consulta (
    id INT NOT NULL AUTO_INCREMENT,
    data_hora DATETIME NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    diagnostico TEXT NULL,
    status ENUM('AGENDADA', 'REALIZADA', 'CANCELADA', 'FALTOU') NOT NULL DEFAULT 'AGENDADA',
    animal_id INT NOT NULL,
    veterinario_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_consulta_animal
        FOREIGN KEY (animal_id) REFERENCES animal (id),
    CONSTRAINT fk_consulta_veterinario
        FOREIGN KEY (veterinario_id) REFERENCES veterinario (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS item_consulta (
    id INT NOT NULL AUTO_INCREMENT,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    consulta_id INT NOT NULL,
    servico_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uq_item_consulta_servico (consulta_id, servico_id),
    CONSTRAINT fk_item_consulta_consulta
        FOREIGN KEY (consulta_id) REFERENCES consulta (id),
    CONSTRAINT fk_item_consulta_servico
        FOREIGN KEY (servico_id) REFERENCES servico (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS pagamento (
    id INT NOT NULL AUTO_INCREMENT,
    data_pagamento DATETIME NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    metodo ENUM('PIX', 'CARTAO', 'DINHEIRO') NOT NULL,
    status ENUM('PENDENTE', 'APROVADO', 'RECUSADO') NOT NULL DEFAULT 'PENDENTE',
    consulta_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pagamento_consulta
        FOREIGN KEY (consulta_id) REFERENCES consulta (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS vacina (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    fabricante VARCHAR(120) NOT NULL,
    descricao TEXT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS aplicacao_vacina (
    id INT NOT NULL AUTO_INCREMENT,
    data_aplicacao DATE NOT NULL,
    lote VARCHAR(60) NOT NULL,
    animal_id INT NOT NULL,
    vacina_id INT NOT NULL,
    veterinario_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_aplicacao_animal
        FOREIGN KEY (animal_id) REFERENCES animal (id),
    CONSTRAINT fk_aplicacao_vacina
        FOREIGN KEY (vacina_id) REFERENCES vacina (id),
    CONSTRAINT fk_aplicacao_veterinario
        FOREIGN KEY (veterinario_id) REFERENCES veterinario (id)
) ENGINE = InnoDB;

-- DML

INSERT INTO categoria (id, nome, descricao) VALUES
(1, 'Consulta', 'Atendimentos e avaliacoes veterinarias.'),
(2, 'Exames', 'Exames laboratoriais e de imagem.'),
(3, 'Procedimentos', 'Procedimentos e pequenas cirurgias.'),
(4, 'Vacinacao', 'Vacinas e imunizacao dos animais.'),
(5, 'Cuidados preventivos', 'Servicos de prevencao e acompanhamento.');

INSERT INTO tutor (id, nome, email, telefone, cidade) VALUES
(1, 'Ana Souza', 'ana.souza@email.com', '47991000001', 'Blumenau'),
(2, 'Bruno Lima', 'bruno.lima@email.com', '47991000002', 'Gaspar'),
(3, 'Carla Mendes', 'carla.mendes@email.com', '47991000003', 'Blumenau'),
(4, 'Daniel Rocha', 'daniel.rocha@email.com', '47991000004', 'Indaial'),
(5, 'Eduarda Alves', 'eduarda.alves@email.com', '47991000005', 'Blumenau'),
(6, 'Felipe Costa', 'felipe.costa@email.com', '47991000006', 'Timbó'),
(7, 'Giovana Martins', 'giovana.martins@email.com', '47991000007', 'Blumenau'),
(8, 'Henrique Melo', 'henrique.melo@email.com', '47991000008', 'Gaspar'),
(9, 'Isabela Ferreira', 'isabela.ferreira@email.com', '47991000009', 'Indaial'),
(10, 'Joao Pedro', 'joao.pedro@email.com', '47991000010', 'Blumenau'),
(11, 'Karen Ribeiro', 'karen.ribeiro@email.com', '47991000011', 'Timbó'),
(12, 'Lucas Almeida', 'lucas.almeida@email.com', '47991000012', 'Blumenau'),
(13, 'Mariana Santos', 'mariana.santos@email.com', '47991000013', 'Gaspar'),
(14, 'Nicolas Barros', 'nicolas.barros@email.com', '47991000014', 'Indaial'),
(15, 'Olivia Cardoso', 'olivia.cardoso@email.com', '47991000015', 'Blumenau');

INSERT INTO animal (id, nome, especie, raca, data_nascimento, tutor_id) VALUES
(1, 'Thor', 'Cachorro', 'Labrador', '2021-03-12', 1),
(2, 'Mel', 'Cachorro', 'Shih-tzu', '2022-07-05', 1),
(3, 'Nina', 'Gato', 'Siames', '2020-11-20', 2),
(4, 'Simba', 'Gato', 'Persa', '2021-01-14', 3),
(5, 'Bob', 'Cachorro', 'Poodle', '2019-09-10', 3),
(6, 'Luna', 'Gato', 'SRD', '2023-02-18', 4),
(7, 'Max', 'Cachorro', 'Golden Retriever', '2020-06-22', 5),
(8, 'Amora', 'Coelho', 'Mini Lop', '2022-04-09', 6),
(9, 'Chico', 'Ave', 'Calopsita', '2021-12-01', 7),
(10, 'Belinha', 'Cachorro', 'Pinscher', '2018-08-15', 8),
(11, 'Fred', 'Gato', 'Maine Coon', '2020-05-27', 8),
(12, 'Kira', 'Cachorro', 'Border Collie', '2022-10-03', 9),
(13, 'Pipoca', 'Coelho', 'Angora', '2023-01-19', 10),
(14, 'Tom', 'Gato', 'SRD', '2019-02-11', 11),
(15, 'Rex', 'Cachorro', 'Pastor Alemao', '2017-07-30', 12),
(16, 'Mia', 'Gato', 'Siamês', '2022-12-12', 12),
(17, 'Sol', 'Cachorro', 'Beagle', '2021-05-16', 13),
(18, 'Cookie', 'Hamster', 'Sirio', '2023-06-08', 13),
(19, 'Floquinho', 'Cachorro', 'Lhasa Apso', '2020-09-21', 5),
(20, 'Nala', 'Gato', 'Bengal', '2022-03-04', 9);

INSERT INTO veterinario (id, nome, crmv, especialidade, email) VALUES
(1, 'Marina Oliveira', 'SC-10001', 'Clinica geral', 'marina@clinicavet.com.br'),
(2, 'Pedro Martins', 'SC-10002', 'Dermatologia', 'pedro@clinicavet.com.br'),
(3, 'Juliana Costa', 'SC-10003', 'Cirurgia', 'juliana@clinicavet.com.br'),
(4, 'Rafael Souza', 'SC-10004', 'Felinos', 'rafael@clinicavet.com.br'),
(5, 'Camila Ribeiro', 'SC-10005', 'Animais silvestres', 'camila@clinicavet.com.br'),
(6, 'André Lima', 'SC-10006', 'Ortopedia', 'andre@clinicavet.com.br');

INSERT INTO servico (id, nome, descricao, preco, categoria_id) VALUES
(1, 'Consulta geral', 'Avaliacao veterinaria de rotina.', 150.00, 1),
(2, 'Consulta de retorno', 'Reavaliacao apos atendimento.', 80.00, 1),
(3, 'Hemograma', 'Exame laboratorial de sangue.', 90.00, 2),
(4, 'Exame de urina', 'Analise laboratorial de urina.', 65.00, 2),
(5, 'Raio-X', 'Exame de imagem radiografica.', 180.00, 2),
(6, 'Ultrassom', 'Exame de imagem por ultrassonografia.', 240.00, 2),
(7, 'Castracao', 'Procedimento cirurgico de castracao.', 450.00, 3),
(8, 'Limpeza dentaria', 'Higienizacao e avaliacao dentaria.', 320.00, 3),
(9, 'Vacina antirrabica', 'Aplicacao de vacina contra raiva.', 110.00, 4),
(10, 'Vacina V8', 'Aplicacao de vacina polivalente canina.', 160.00, 4),
(11, 'Vermifugacao', 'Administracao de antiparasitario.', 55.00, 5),
(12, 'Controle de pulgas', 'Tratamento preventivo contra pulgas.', 75.00, 5);

INSERT INTO consulta (id, data_hora, motivo, diagnostico, status, animal_id, veterinario_id) VALUES
(1, '2026-09-01 09:00:00', 'Avaliacao de rotina', 'Animal saudavel.', 'REALIZADA', 1, 1),
(2, '2026-09-02 10:00:00', 'Coceira frequente', 'Dermatite leve.', 'REALIZADA', 2, 2),
(3, '2026-09-03 11:00:00', 'Falta de apetite', 'Necessita acompanhamento.', 'REALIZADA', 3, 1),
(4, '2026-09-04 09:30:00', 'Dor ao caminhar', 'Exame de imagem solicitado.', 'REALIZADA', 4, 3),
(5, '2026-09-05 14:00:00', 'Vermifugacao', 'Procedimento concluido.', 'REALIZADA', 5, 2),
(6, '2026-09-08 15:00:00', 'Avaliacao pre-cirurgica', 'Apto para procedimento.', 'REALIZADA', 6, 4),
(7, '2026-09-09 09:00:00', 'Mau halito', 'Necessita limpeza dentaria.', 'REALIZADA', 7, 1),
(8, '2026-09-10 10:30:00', 'Revisao de vacinas', 'Vacinacao atualizada.', 'REALIZADA', 8, 3),
(9, '2026-09-11 13:00:00', 'Queda de penas', 'Sem alteracoes graves.', 'REALIZADA', 9, 5),
(10, '2026-09-12 09:00:00', 'Exame de rotina', 'Exames coletados.', 'REALIZADA', 10, 2),
(11, '2026-09-15 11:00:00', 'Alteracao urinaria', 'Exame de urina solicitado.', 'REALIZADA', 11, 4),
(12, '2026-09-16 14:00:00', 'Dor abdominal', 'Raio-X solicitado.', 'REALIZADA', 12, 5),
(13, '2026-09-17 09:00:00', 'Acompanhamento', 'Ultrassom solicitado.', 'REALIZADA', 13, 1),
(14, '2026-09-18 10:00:00', 'Retorno de rotina', 'Vacina aplicada.', 'REALIZADA', 14, 2),
(15, '2026-09-19 13:30:00', 'Revisao anual', 'Vacinacao atualizada.', 'REALIZADA', 15, 3),
(16, '2026-11-12 09:00:00', 'Avaliacao de rotina', NULL, 'AGENDADA', 16, 4),
(17, '2026-11-14 10:30:00', 'Consulta preventiva', NULL, 'AGENDADA', 17, 5),
(18, '2026-10-20 11:00:00', 'Retorno', NULL, 'CANCELADA', 1, 1),
(19, '2026-10-22 14:00:00', 'Avaliacao dermatologica', NULL, 'FALTOU', 2, 2),
(20, '2026-11-07 09:30:00', 'Espirros frequentes', 'Acompanhamento recomendado.', 'REALIZADA', 3, 3);

INSERT INTO item_consulta (id, quantidade, preco_unitario, consulta_id, servico_id) VALUES
(1, 1, 150.00, 1, 1),
(2, 1, 90.00, 1, 3),
(3, 1, 110.00, 1, 9),
(4, 1, 150.00, 2, 1),
(5, 1, 65.00, 2, 4),
(6, 1, 150.00, 3, 1),
(7, 1, 180.00, 3, 5),
(8, 1, 150.00, 4, 1),
(9, 1, 240.00, 4, 6),
(10, 1, 150.00, 5, 1),
(11, 1, 90.00, 5, 3),
(12, 1, 150.00, 6, 1),
(13, 1, 450.00, 6, 7),
(14, 1, 150.00, 7, 1),
(15, 1, 320.00, 7, 8),
(16, 1, 150.00, 8, 1),
(17, 1, 160.00, 8, 10),
(18, 1, 150.00, 9, 1),
(19, 1, 55.00, 9, 11),
(20, 1, 150.00, 10, 1),
(21, 1, 90.00, 10, 3),
(22, 1, 150.00, 11, 1),
(23, 1, 65.00, 11, 4),
(24, 1, 150.00, 12, 1),
(25, 1, 180.00, 12, 5),
(26, 1, 150.00, 13, 1),
(27, 1, 240.00, 13, 6),
(28, 1, 150.00, 14, 1),
(29, 1, 110.00, 14, 9),
(30, 1, 150.00, 15, 1),
(31, 1, 160.00, 15, 10),
(32, 1, 150.00, 20, 1),
(33, 1, 90.00, 20, 3);

INSERT INTO pagamento (id, data_pagamento, valor, metodo, status, consulta_id) VALUES
(1, '2026-09-01 09:45:00', 350.00, 'PIX', 'APROVADO', 1),
(2, '2026-09-02 10:45:00', 215.00, 'CARTAO', 'APROVADO', 2),
(3, '2026-09-03 11:45:00', 330.00, 'DINHEIRO', 'APROVADO', 3),
(4, '2026-09-04 10:15:00', 390.00, 'PIX', 'APROVADO', 4),
(5, '2026-09-05 14:45:00', 240.00, 'CARTAO', 'APROVADO', 5),
(6, '2026-09-08 15:45:00', 600.00, 'PIX', 'APROVADO', 6),
(7, '2026-09-09 10:00:00', 470.00, 'CARTAO', 'APROVADO', 7),
(8, '2026-09-10 11:15:00', 310.00, 'DINHEIRO', 'APROVADO', 8),
(9, '2026-09-11 13:45:00', 205.00, 'PIX', 'APROVADO', 9),
(10, '2026-09-12 09:45:00', 240.00, 'CARTAO', 'APROVADO', 10),
(11, '2026-09-15 11:45:00', 215.00, 'PIX', 'APROVADO', 11),
(12, '2026-09-16 14:45:00', 330.00, 'CARTAO', 'APROVADO', 12),
(13, '2026-09-17 09:45:00', 390.00, 'PIX', 'APROVADO', 13),
(14, '2026-09-18 10:45:00', 260.00, 'DINHEIRO', 'PENDENTE', 14),
(15, '2026-09-19 14:15:00', 310.00, 'CARTAO', 'APROVADO', 15),
(16, '2026-11-07 10:15:00', 240.00, 'PIX', 'APROVADO', 20);

INSERT INTO vacina (id, nome, fabricante, descricao) VALUES
(1, 'V8', 'VetBio', 'Vacina polivalente para caes.'),
(2, 'Antirrabica', 'Saude Animal', 'Prevencao contra raiva.'),
(3, 'Quintupla felina', 'FelixVet', 'Imunizacao para gatos.'),
(4, 'Gripe canina', 'VetBio', 'Prevencao de doencas respiratorias.'),
(5, 'Giardia', 'AnimalCare', 'Prevencao contra giardiase.'),
(6, 'Leishmaniose', 'ProtegePet', 'Vacina preventiva contra leishmaniose.');

INSERT INTO aplicacao_vacina (id, data_aplicacao, lote, animal_id, vacina_id, veterinario_id) VALUES
(1, '2026-01-10', 'V8-2026-001', 1, 1, 1),
(2, '2026-01-10', 'AR-2026-010', 1, 2, 1),
(3, '2026-02-05', 'V8-2026-002', 2, 1, 2),
(4, '2026-02-12', 'QF-2026-001', 3, 3, 1),
(5, '2026-02-20', 'QF-2026-002', 4, 3, 3),
(6, '2026-03-01', 'V8-2026-003', 5, 1, 2),
(7, '2026-03-08', 'AR-2026-011', 6, 2, 4),
(8, '2026-03-15', 'V8-2026-004', 7, 1, 1),
(9, '2026-03-20', 'GC-2026-001', 8, 4, 3),
(10, '2026-04-02', 'GC-2026-002', 9, 4, 5),
(11, '2026-04-09', 'V8-2026-005', 10, 1, 2),
(12, '2026-04-16', 'QF-2026-003', 11, 3, 4),
(13, '2026-04-22', 'AR-2026-012', 12, 2, 5),
(14, '2026-05-03', 'GD-2026-001', 13, 5, 1),
(15, '2026-05-10', 'QF-2026-004', 14, 3, 2),
(16, '2026-05-18', 'V8-2026-006', 15, 1, 3),
(17, '2026-07-10', 'V8-2026-007', 1, 1, 1),
(18, '2026-07-20', 'AR-2026-013', 5, 2, 2);

-- CONSULTAS

-- 1 - Animais e tutores
SELECT a.nome AS animal, a.especie, a.raca, t.nome AS tutor, t.cidade
FROM animal a
INNER JOIN tutor t ON a.tutor_id = t.id
ORDER BY a.nome;

-- 2 - Animais por tutor
SELECT a.nome AS animal, t.nome AS tutor, t.email, t.telefone
FROM animal a
INNER JOIN tutor t ON a.tutor_id = t.id
ORDER BY t.nome, a.nome;

-- 3 - Consultas e animais
SELECT c.data_hora, c.status, c.motivo, a.nome AS animal
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
ORDER BY c.data_hora;

-- 4 - Consultas e veterinarios
SELECT c.data_hora, c.status, v.nome AS veterinario, v.especialidade
FROM consulta c
INNER JOIN veterinario v ON c.veterinario_id = v.id
ORDER BY c.data_hora;

-- 5 - Detalhamento dos atendimentos
SELECT c.id AS consulta, a.nome AS animal, v.nome AS veterinario,
       s.nome AS servico, ic.quantidade, ic.preco_unitario
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
INNER JOIN veterinario v ON c.veterinario_id = v.id
INNER JOIN item_consulta ic ON c.id = ic.consulta_id
INNER JOIN servico s ON ic.servico_id = s.id
ORDER BY c.id, s.nome;

-- 6 - Quantidade de consultas por tutor
SELECT t.nome AS tutor, COUNT(DISTINCT c.id) AS quantidade_consultas
FROM tutor t
LEFT JOIN animal a ON t.id = a.tutor_id
LEFT JOIN consulta c ON a.id = c.animal_id
GROUP BY t.id, t.nome
ORDER BY quantidade_consultas DESC, t.nome;

-- 7 - Tutores sem animais
SELECT t.id, t.nome
FROM tutor t
LEFT JOIN animal a ON t.id = a.tutor_id
WHERE a.id IS NULL;

-- 8 - Quantidade de animais por especie
SELECT especie, COUNT(id) AS quantidade_animais
FROM animal
GROUP BY especie
ORDER BY quantidade_animais DESC, especie;

-- 9 - Especie com mais animais
SELECT especie, COUNT(id) AS quantidade_animais
FROM animal
GROUP BY especie
ORDER BY quantidade_animais DESC, especie
LIMIT 1;

-- 10 - Veterinarios e consultas
SELECT v.nome AS veterinario, COUNT(c.id) AS quantidade_consultas
FROM veterinario v
LEFT JOIN consulta c ON v.id = c.veterinario_id
GROUP BY v.id, v.nome
ORDER BY quantidade_consultas DESC, v.nome;

-- 11 - Veterinarios sem consultas
SELECT v.id, v.nome
FROM veterinario v
LEFT JOIN consulta c ON v.id = c.veterinario_id
WHERE c.id IS NULL;

-- 12 - Servico mais caro
SELECT s.nome AS servico, s.preco, cat.nome AS categoria
FROM servico s
INNER JOIN categoria cat ON s.categoria_id = cat.id
WHERE s.preco = (SELECT MAX(preco) FROM servico);

-- 13 - Servicos nunca utilizados
SELECT s.id, s.nome
FROM servico s
LEFT JOIN item_consulta ic ON s.id = ic.servico_id
WHERE ic.id IS NULL;

-- 14 - Servicos por consulta
SELECT c.id AS consulta, COUNT(ic.id) AS quantidade_itens
FROM consulta c
LEFT JOIN item_consulta ic ON c.id = ic.consulta_id
GROUP BY c.id
ORDER BY c.id;

-- 15 - Servicos mais realizados
SELECT s.nome AS servico, COUNT(ic.id) AS quantidade_ocorrencias
FROM servico s
LEFT JOIN item_consulta ic ON s.id = ic.servico_id
GROUP BY s.id, s.nome
ORDER BY quantidade_ocorrencias DESC, s.nome;

-- 16 - Consultas e pagamentos
SELECT c.id AS consulta, a.nome AS animal, t.nome AS tutor,
       p.valor, p.metodo, p.status AS status_pagamento
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
INNER JOIN tutor t ON a.tutor_id = t.id
INNER JOIN pagamento p ON c.id = p.consulta_id
ORDER BY c.id;

-- 17 - Consultas sem pagamento
SELECT c.id AS consulta, c.data_hora, a.nome AS animal, c.status
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
LEFT JOIN pagamento p ON c.id = p.consulta_id
WHERE p.id IS NULL
ORDER BY c.data_hora;

-- 18 - Historico de vacinacao
SELECT a.nome AS animal, va.nome AS vacina, ap.lote,
       ap.data_aplicacao, v.nome AS veterinario
FROM aplicacao_vacina ap
INNER JOIN animal a ON ap.animal_id = a.id
INNER JOIN vacina va ON ap.vacina_id = va.id
INNER JOIN veterinario v ON ap.veterinario_id = v.id
ORDER BY a.nome, ap.data_aplicacao;

-- 19 - Animais sem vacinacao registrada
SELECT a.id, a.nome, a.especie
FROM animal a
LEFT JOIN aplicacao_vacina ap ON a.id = ap.animal_id
WHERE ap.id IS NULL;

-- 20 - Media de preco por categoria
SELECT cat.nome AS categoria, ROUND(AVG(s.preco), 2) AS preco_medio
FROM categoria cat
INNER JOIN servico s ON cat.id = s.categoria_id
GROUP BY cat.id, cat.nome
ORDER BY preco_medio DESC;

-- 21 - Quantidade de aplicacoes por vacina
SELECT va.nome AS vacina, COUNT(ap.id) AS quantidade_aplicacoes
FROM vacina va
LEFT JOIN aplicacao_vacina ap ON va.id = ap.vacina_id
GROUP BY va.id, va.nome
ORDER BY quantidade_aplicacoes DESC, va.nome;

-- 22 - Quantidade de consultas por status
SELECT status, COUNT(id) AS quantidade_consultas
FROM consulta
GROUP BY status
ORDER BY status;

-- 23 - Valor total por consulta
SELECT c.id AS consulta, a.nome AS animal,
       COALESCE(SUM(ic.quantidade * ic.preco_unitario), 0) AS valor_total
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
LEFT JOIN item_consulta ic ON c.id = ic.consulta_id
GROUP BY c.id, a.nome
ORDER BY c.id;

-- 24 - Valor total dos atendimentos por tutor
SELECT t.nome AS tutor, COUNT(DISTINCT c.id) AS quantidade_consultas,
       COALESCE(SUM(ic.quantidade * ic.preco_unitario), 0) AS valor_total_servicos
FROM tutor t
LEFT JOIN animal a ON t.id = a.tutor_id
LEFT JOIN consulta c ON a.id = c.animal_id
LEFT JOIN item_consulta ic ON c.id = ic.consulta_id
GROUP BY t.id, t.nome
ORDER BY valor_total_servicos DESC, t.nome;

-- 25 - Resumo dos animais
SELECT a.nome AS animal, a.especie, t.nome AS tutor,
       COALESCE(consultas.quantidade, 0) AS quantidade_consultas,
       COALESCE(aplicacoes.quantidade, 0) AS quantidade_aplicacoes
FROM animal a
INNER JOIN tutor t ON a.tutor_id = t.id
LEFT JOIN (
    SELECT animal_id, COUNT(id) AS quantidade
    FROM consulta
    GROUP BY animal_id
) consultas ON a.id = consultas.animal_id
LEFT JOIN (
    SELECT animal_id, COUNT(id) AS quantidade
    FROM aplicacao_vacina
    GROUP BY animal_id
) aplicacoes ON a.id = aplicacoes.animal_id
ORDER BY a.nome;

-- 26 - Tutores com muitas consultas
SELECT t.nome AS tutor, COUNT(DISTINCT c.id) AS quantidade_consultas
FROM tutor t
INNER JOIN animal a ON t.id = a.tutor_id
INNER JOIN consulta c ON a.id = c.animal_id
GROUP BY t.id, t.nome
HAVING COUNT(DISTINCT c.id) > 2
ORDER BY quantidade_consultas DESC, t.nome;

-- 27 - Categorias com varios servicos
SELECT cat.nome AS categoria, COUNT(s.id) AS quantidade_servicos
FROM categoria cat
INNER JOIN servico s ON cat.id = s.categoria_id
GROUP BY cat.id, cat.nome
HAVING COUNT(s.id) > 2;

-- 28 - Animais com vacinacao recorrente
SELECT a.nome AS animal, COUNT(ap.id) AS quantidade_aplicacoes
FROM animal a
INNER JOIN aplicacao_vacina ap ON a.id = ap.animal_id
GROUP BY a.id, a.nome
HAVING COUNT(ap.id) >= 2
ORDER BY quantidade_aplicacoes DESC, a.nome;

-- 29 - Consultas com valor alto
SELECT c.id AS consulta, a.nome AS animal,
       SUM(ic.quantidade * ic.preco_unitario) AS valor_total
FROM consulta c
INNER JOIN animal a ON c.animal_id = a.id
INNER JOIN item_consulta ic ON c.id = ic.consulta_id
GROUP BY c.id, a.nome
HAVING SUM(ic.quantidade * ic.preco_unitario) > 300.00
ORDER BY valor_total DESC;

-- 30 - Relatorio geral da clinica
SELECT t.nome AS tutor, t.cidade,
       COUNT(DISTINCT a.id) AS quantidade_animais,
       COUNT(DISTINCT CASE WHEN c.status = 'REALIZADA' THEN c.id END) AS consultas_realizadas,
       COALESCE(SUM(ic.quantidade * ic.preco_unitario), 0) AS valor_total_servicos
FROM tutor t
LEFT JOIN animal a ON t.id = a.tutor_id
LEFT JOIN consulta c ON a.id = c.animal_id
LEFT JOIN item_consulta ic ON c.id = ic.consulta_id
GROUP BY t.id, t.nome, t.cidade
HAVING COUNT(DISTINCT c.id) > 0
ORDER BY valor_total_servicos DESC, t.nome;

-- EXPLICACAO DA QUESTAO 30
-- A consulta comeca pela tabela tutor e relaciona cada tutor aos seus animais.
-- Depois, relaciona os animais as consultas e as consultas aos itens de servico.
-- O COUNT(DISTINCT a.id) conta os animais sem repetir o mesmo animal quando ele tem varias consultas.
-- O COUNT(DISTINCT CASE...) conta somente as consultas com status REALIZADA.
-- A soma de quantidade vezes preco_unitario calcula o valor dos servicos registrados para cada tutor.
-- O GROUP BY separa os resultados por tutor, e o HAVING remove quem nao possui consulta.
-- Por fim, o ORDER BY mostra primeiro os tutores com maior valor total em servicos.
