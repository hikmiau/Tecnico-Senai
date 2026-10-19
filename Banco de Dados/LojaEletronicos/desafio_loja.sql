-- Aluno: Henrique Fagundes
-- Turma: 203
-- Data: 19/10/26

CREATE DATABASE IF NOT EXISTS loja_eletronicos;
USE loja_eletronicos;

-- Categoria
CREATE TABLE categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Fornecedor
CREATE TABLE fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    cidade VARCHAR(50)
);

-- Produto
CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL DEFAULT 0,
    id_categoria INT NOT NULL,
    id_fornecedor INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id)
);

-- Cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    cidade VARCHAR(50)
);

-- Pedido
CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_pedido DATETIME NOT NULL,
    status ENUM('PENDENTE', 'PAGO', 'ENVIADO', 'ENTREGUE', 'CANCELADO') NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

-- Item Pedido
CREATE TABLE item_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    id_pedido INT NOT NULL,
    id_produto INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);

-- Pagamento
CREATE TABLE pagamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_pagamento DATETIME,
    valor DECIMAL(10, 2) NOT NULL,
    metodo ENUM('PIX', 'CARTAO', 'BOLETO') NOT NULL,
    status ENUM('PENDENTE', 'APROVADO', 'RECUSADO') NOT NULL,
    id_pedido INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

-- Entrega
CREATE TABLE entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_rastreio VARCHAR(50),
    data_envio DATETIME,
    data_entrega DATETIME,
    status ENUM('AGUARDANDO_ENVIO', 'EM_TRANSITO', 'ENTREGUE', 'ATRASADA') NOT NULL,
    id_pedido INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);

-- Avaliação
CREATE TABLE avaliacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    data_avaliacao DATETIME,
    id_cliente INT NOT NULL,
    id_produto INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);





-- POPULAÇÃO DAS TABELAS

USE loja_eletronicos;

-- CATEGORIA
INSERT INTO categoria (id, nome, descricao) VALUES
(1, 'Notebooks', 'Notebooks de alta performance, ultrabooks e gamers.'),
(2, 'Periféricos', 'Teclados, mouses, headsets e acessórios para digitação/jogos.'),
(3, 'Componentes', 'Placas de vídeo, processadores, memórias RAM e placas-mãe.'),
(4, 'Monitores', 'Monitores LED, IPS, Ultrawide e Gamers de alta frequência.'),
(5, 'Acessórios', 'Cabos, adaptadores, suportes, mochilas e organizadores.');

-- FORNECEDOR
-- Note: O fornecedor ID 6 ("LogiTech Distribuidora") não terá produtos vinculados.
INSERT INTO fornecedor (id, nome, email, telefone, cidade) VALUES
(1, 'Dell Brasil Tech', 'contato@delltech.com.br', '(11) 3000-1000', 'São Paulo'),
(2, 'ASUS Importação', 'vendas@asusimport.com.br', '(21) 2500-2000', 'Rio de Janeiro'),
(3, 'Corsair Brasil', 'atendimento@corsair.com.br', '(31) 3200-3000', 'Belo Horizonte'),
(4, 'LG Eletrônicos', 'comercial@lgeletro.com.br', '(41) 3300-4000', 'Curitiba'),
(5, 'Kingston Suprimentos', 'suporte@kingston.com.br', '(51) 3400-5000', 'Porto Alegre'),
(6, 'LogiTech Distribuidora', 'vendas@logitechdist.com.br', '(47) 3500-6000', 'Blumenau');

-- PRODUTO 
-- Note: O produto ID 12 ("Suporte para Monitor") não terá vendas (sem item_pedido).
INSERT INTO produto (id, nome, descricao, preco, estoque, id_categoria, id_fornecedor) VALUES
(1, 'Notebook Gamer Nitro 5', 'Intel i7, 16GB RAM, RTX 3060, SSD 512GB', 4800.00, 15, 1, 1),
(2, 'Notebook Ultrabook ZenBook', 'Intel i5, 8GB RAM, SSD 256GB, Tela 14', 3500.00, 10, 1, 2),
(3, 'Mouse Sem Fio Corsair Dark Core', 'Mouse gamer sem fio 18000 DPI', 450.00, 30, 2, 3),
(4, 'Teclado Mecânico K70 RGB', 'Teclado mecânico Switch Red RGB', 650.00, 25, 2, 3),
(5, 'Placa de Vídeo RTX 4070', '12GB GDDR6X 192-bit', 4200.00, 8, 3, 2),
(6, 'Memória RAM 16GB DDR4 3200MHz', 'Pente único de memória alta velocidade', 280.00, 50, 3, 5),
(7, 'Processador Intel Core i7 13700K', '16 Cores 24 Threads Turbo 5.4GHz', 2400.00, 12, 3, 1),
(8, 'Monitor Ultrawide 29 IPS', 'Full HD 75Hz HDR10 HDMI', 1200.00, 20, 4, 4),
(9, 'Monitor Gamer 27 165Hz', '1ms QHD Curvo HDMI/DisplayPort', 1800.00, 14, 4, 4),
(10, 'Cabo HDMI 2.1 Ultra HD 4K', 'Cabo trançado de alta velocidade 2m', 60.00, 100, 5, 5),
(11, 'Mochila Impermeável para Notebook', 'Suporta notebooks até 15.6 polegadas', 150.00, 40, 5, 1),
(12, 'Suporte Articulado para Monitor', 'Suporte a gás para monitores até 32 polegadas', 220.00, 18, 5, 3);

-- CLIENTE
-- Note: Os clientes IDs 14 e 15 não terão pedidos cadastrados.
INSERT INTO cliente (id, nome, email, telefone, cidade) VALUES
(1, 'Carlos Silva', 'carlos.silva@email.com', '(11) 98888-1001', 'São Paulo'),
(2, 'Ana Oliveira', 'ana.oliveira@email.com', '(21) 98888-1002', 'Rio de Janeiro'),
(3, 'Bruno Santos', 'bruno.santos@email.com', '(31) 98888-1003', 'Belo Horizonte'),
(4, 'Camila Souza', 'camila.souza@email.com', '(41) 98888-1004', 'Curitiba'),
(5, 'Diego Ferreira', 'diego.ferreira@email.com', '(51) 98888-1005', 'Porto Alegre'),
(6, 'Eduarda Lima', 'eduarda.lima@email.com', '(47) 98888-1006', 'Blumenau'),
(7, 'Felipe Costa', 'felipe.costa@email.com', '(11) 98888-1007', 'São Paulo'),
(8, 'Gabriela Rocha', 'gabriela.rocha@email.com', '(19) 98888-1008', 'Campinas'),
(9, 'Heitor Pereira', 'heitor.pereira@email.com', '(81) 98888-1009', 'Recife'),
(10, 'Isabela Martins', 'isabela.martins@email.com', '(71) 98888-1010', 'Salvador'),
(11, 'João Pedro Alves', 'joao.alves@email.com', '(61) 98888-1011', 'Brasília'),
(12, 'Larissa Ramos', 'larissa.ramos@email.com', '(48) 98888-1012', 'Florianópolis'),
(13, 'Lucas Rodrigues', 'lucas.rodrigues@email.com', '(85) 98888-1013', 'Fortaleza'),
(14, 'Mariana Barbosa', 'mariana.barbosa@email.com', '(11) 98888-1014', 'São Paulo'),
(15, 'Nicolas Mendes', 'nicolas.mendes@email.com', '(21) 98888-1015', 'Rio de Janeiro');

-- PEDIDO
-- Status: PENDENTE, PAGO, ENVIADO, ENTREGUE, CANCELADO.
INSERT INTO pedido (id, data_pedido, status, id_cliente) VALUES
(1,  '2026-01-10 10:30:00', 'ENTREGUE',  1),
(2,  '2026-01-12 14:15:00', 'ENTREGUE',  1),
(3,  '2026-01-15 09:00:00', 'PAGO',      1),
(4,  '2026-01-18 16:20:00', 'ENTREGUE',  2),
(5,  '2026-01-20 11:45:00', 'ENVIADO',   2),
(6,  '2026-01-22 08:30:00', 'PAGO',      3),
(7,  '2026-01-25 13:10:00', 'ENTREGUE',  3),
(8,  '2026-01-28 17:00:00', 'PENDENTE',  3),
(9,  '2026-02-01 10:00:00', 'ENTREGUE',  4),
(10, '2026-02-03 15:30:00', 'PAGO',      5),
(11, '2026-02-05 12:00:00', 'ENVIADO',   6),
(12, '2026-02-07 09:15:00', 'ENTREGUE',  7),
(13, '2026-02-10 18:40:00', 'CANCELADO', 8),
(14, '2026-02-12 14:00:00', 'ENTREGUE',  9),
(15, '2026-02-14 11:10:00', 'PAGO',      10),
(16, '2026-02-16 16:45:00', 'ENTREGUE',  11),
(17, '2026-02-18 10:20:00', 'PENDENTE',  12),
(18, '2026-02-20 13:30:00', 'CANCELADO', 13),
(19, '2026-02-22 15:00:00', 'PAGO',      1),
(20, '2026-02-25 17:15:00', 'ENTREGUE',  2);

-- ITEM_PEDIDO
-- Note: Produto ID 12 não é inserido aqui (permanece sem vendas).
INSERT INTO item_pedido (id, quantidade, preco_unitario, id_pedido, id_produto) VALUES
(1,  1, 4800.00, 1,  1),
(2,  2, 60.00,   1,  10),
(3,  1, 450.00,  2,  3),
(4,  1, 650.00,  2,  4),
(5,  1, 3500.00, 3,  2),
(6,  1, 4200.00, 4,  5),
(7,  2, 280.00,  4,  6),
(8,  1, 1800.00, 5,  9),
(9,  1, 2400.00, 6,  7),
(10, 1, 650.00,  6,  4),
(11, 1, 1200.00, 7,  8),
(12, 1, 150.00,  7,  11),
(13, 1, 4800.00, 8,  1),
(14, 2, 280.00,  9,  6),
(15, 1, 450.00,  9,  3),
(16, 1, 3500.00, 10, 2),
(17, 1, 1800.00, 11, 9),
(18, 1, 60.00,   11, 10),
(19, 1, 4200.00, 12, 5),
(20, 1, 150.00,  13, 11),
(21, 1, 2400.00, 14, 7),
(22, 1, 1200.00, 14, 8),
(23, 1, 650.00,  15, 4),
(24, 2, 60.00,   15, 10),
(25, 1, 4800.00, 16, 1),
(26, 1, 450.00,  16, 3),
(27, 2, 280.00,  17, 6),
(28, 1, 1800.00, 18, 9),
(29, 1, 650.00,  19, 4),
(30, 2, 60.00,   19, 10),
(31, 1, 1200.00, 20, 8),
(32, 1, 150.00,  20, 11);

-- PAGAMENTO
-- Note: Os pedidos 8, 13, 17 e 18 ficam sem registro de pagamento.
INSERT INTO pagamento (id, data_pagamento, valor, metodo, status, id_pedido) VALUES
(1,  '2026-01-10 10:35:00', 4920.00, 'PIX',    'APROVADO', 1),
(2,  '2026-01-12 14:20:00', 1100.00, 'CARTAO', 'APROVADO', 2),
(3,  '2026-01-15 09:05:00', 3500.00, 'BOLETO', 'APROVADO', 3),
(4,  '2026-01-18 16:25:00', 4760.00, 'CARTAO', 'APROVADO', 4),
(5,  '2026-01-20 11:50:00', 1800.00, 'PIX',    'APROVADO', 5),
(6,  '2026-01-22 08:35:00', 3050.00, 'BOLETO', 'APROVADO', 6),
(7,  '2026-01-25 13:15:00', 1350.00, 'PIX',    'APROVADO', 7),
(8,  '2026-02-01 10:05:00', 1010.00, 'CARTAO', 'APROVADO', 9),
(9,  '2026-02-03 15:35:00', 3500.00, 'PIX',    'APROVADO', 10),
(10, '2026-02-05 12:05:00', 1860.00, 'BOLETO', 'APROVADO', 11),
(11, '2026-02-07 09:20:00', 4200.00, 'CARTAO', 'APROVADO', 12),
(12, '2026-02-12 14:05:00', 3600.00, 'PIX',    'APROVADO', 14),
(13, '2026-02-14 11:15:00', 770.00,  'CARTAO', 'APROVADO', 15),
(14, '2026-02-16 16:50:00', 5250.00, 'PIX',    'APROVADO', 16),
(15, '2026-02-22 15:05:00', 770.00,  'PIX',    'APROVADO', 19),
(16, '2026-02-25 17:20:00', 1350.00, 'CARTAO', 'APROVADO', 20);

-- ENTREGA
-- Note: Os pedidos 8, 13, 17 e 18 ficam sem registro de entrega.
INSERT INTO entrega (id, codigo_rastreio, data_envio, data_entrega, status, id_pedido) VALUES
(1,  'BR100000001SP', '2026-01-11 08:00:00', '2026-01-13 15:00:00', 'ENTREGUE',        1),
(2,  'BR100000002SP', '2026-01-13 09:00:00', '2026-01-16 11:00:00', 'ENTREGUE',        2),
(3,  'BR100000003SP', '2026-01-16 10:00:00', NULL,                  'EM_TRANSITO',     3),
(4,  'BR100000004RJ', '2026-01-19 08:30:00', '2026-01-22 14:00:00', 'ENTREGUE',        4),
(5,  'BR100000005RJ', '2026-01-21 09:30:00', NULL,                  'EM_TRANSITO',     5),
(6,  'BR100000006MG', '2026-01-23 10:00:00', NULL,                  'AGUARDANDO_ENVIO', 6),
(7,  'BR100000007MG', '2026-01-26 08:00:00', '2026-01-29 16:30:00', 'ENTREGUE',        7),
(8,  'BR100000009PR', '2026-02-02 09:00:00', '2026-02-05 10:00:00', 'ENTREGUE',        9),
(9,  'BR100000010RS', '2026-02-04 11:00:00', NULL,                  'AGUARDANDO_ENVIO', 10),
(10, 'BR100000011SC', '2026-02-06 08:00:00', NULL,                  'EM_TRANSITO',     11),
(11, 'BR100000012SP', '2026-02-08 10:30:00', '2026-02-11 12:00:00', 'ENTREGUE',        12),
(12, 'BR100000014PE', '2026-02-13 09:00:00', '2026-02-17 17:00:00', 'ATRASADA',        14),
(13, 'BR100000015BA', '2026-02-15 14:00:00', NULL,                  'EM_TRANSITO',     15),
(14, 'BR100000016DF', '2026-02-17 08:00:00', '2026-02-20 11:30:00', 'ENTREGUE',        16),
(15, 'BR100000019SP', '2026-02-23 10:00:00', NULL,                  'AGUARDANDO_ENVIO', 19),
(16, 'BR100000020RJ', '2026-02-26 09:00:00', '2026-02-28 16:00:00', 'ENTREGUE',        20);

-- AVALIACAO
-- Note: Produtos 2, 8, 10, 11 e 12 ficam sem avaliações para testar consultas.
INSERT INTO avaliacao (id, nota, comentario, data_avaliacao, id_cliente, id_produto) VALUES
(1,  5, 'Excelente notebook! Roda tudo com ótimo desempenho.', '2026-01-14 10:00:00', 1, 1),
(2,  4, 'Mouse muito preciso e confortável.',                  '2026-01-17 11:30:00', 1, 3),
(3,  5, 'Teclado sensacional, construção impecável.',           '2026-01-17 12:00:00', 1, 4),
(4,  5, 'Placa monstra, rodo jogos em 4K sem travamento.',     '2026-01-23 15:20:00', 2, 5),
(5,  4, 'Memória muito boa e estável.',                        '2026-01-23 16:00:00', 2, 6),
(6,  5, 'Processador top de linha, entrega muito desempenho.', '2026-01-24 09:10:00', 3, 7),
(7,  4, 'Teclado ótimo, porém um pouco barulhento.',           '2026-01-24 09:40:00', 3, 4),
(8,  4, 'Mouse de alta qualidade, bateria dura bastante.',    '2026-02-06 14:00:00', 4, 3),
(9,  5, 'Monitor incrível, taxa de atualização muito fluida.','2026-02-08 10:15:00', 6, 9),
(10, 5, 'Placa fantástica! Silenciosa e fria.',               '2026-02-12 18:00:00', 7, 5),
(11, 3, 'Processador bom, mas esquenta bastante.',            '2026-02-18 11:00:00', 9, 7),
(12, 5, 'Notebook sensacional, liga em segundos!',            '2026-02-21 16:30:00', 11, 1),
(13, 2, 'Mouse deu um pequeno duplo clique com poucas semanas.','2026-02-22 09:00:00', 11, 3),
(14, 4, 'Excelente teclado, recomendo a todos.',              '2026-02-26 14:20:00', 1, 4);





USE loja_eletronicos;

-- CONSULTAS

-- Questão 01 - Produtos e categorias
SELECT 
    p.nome AS produto, 
    p.preco, 
    p.estoque, 
    c.nome AS categoria
FROM produto p
JOIN categoria c ON p.id_categoria = c.id;

-- Questão 02 - Produtos e fornecedores
SELECT 
    p.nome AS produto, 
    p.preco, 
    f.nome AS fornecedor, 
    f.cidade AS cidade_fornecedor
FROM produto p
JOIN fornecedor f ON p.id_fornecedor = f.id;

-- Questão 03 - Pedidos e clientes
SELECT 
    p.id AS numero_pedido, 
    p.data_pedido, 
    p.status, 
    c.nome AS cliente, 
    c.cidade
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id;

-- Questão 04 - Itens dos pedidos
SELECT 
    i.id_pedido AS numero_pedido, 
    pr.nome AS produto, 
    i.quantidade, 
    i.preco_unitario
FROM item_pedido i
JOIN produto pr ON i.id_produto = pr.id;

-- Questão 05 - Pedido completo
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    pr.nome AS produto, 
    i.quantidade, 
    i.preco_unitario, 
    p.status AS status_pedido
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN item_pedido i ON p.id = i.id_pedido
JOIN produto pr ON i.id_produto = pr.id;

-- Questão 06 - Quantidade de pedidos por cliente
SELECT 
    c.nome AS cliente, 
    COUNT(p.id) AS qtd_pedidos
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
GROUP BY c.id, c.nome
ORDER BY qtd_pedidos DESC;

-- Questão 07 - Clientes sem pedidos
SELECT 
    c.nome, 
    c.email, 
    c.cidade
FROM cliente c
LEFT JOIN pedido p ON c.id = p.id_cliente
WHERE p.id IS NULL;

-- Questão 08 - Quantidade de produtos por categoria
SELECT 
    c.nome AS categoria, 
    COUNT(p.id) AS qtd_produtos
FROM categoria c
JOIN produto p ON c.id = p.id_categoria
GROUP BY c.id, c.nome
ORDER BY qtd_produtos DESC;

-- Questão 09 - Categoria com mais produtos
SELECT 
    c.nome AS categoria, 
    COUNT(p.id) AS qtd_produtos
FROM categoria c
JOIN produto p ON c.id = p.id_categoria
GROUP BY c.id, c.nome
ORDER BY qtd_produtos DESC
LIMIT 1;

-- Questão 10 - Fornecedores cadastrados
SELECT 
    f.nome AS fornecedor, 
    COUNT(p.id) AS qtd_produtos
FROM fornecedor f
LEFT JOIN produto p ON f.id = p.id_fornecedor
GROUP BY f.id, f.nome
ORDER BY qtd_produtos DESC;

-- Questão 11 - Fornecedores sem produtos
SELECT 
    f.nome, 
    f.email, 
    f.cidade
FROM fornecedor f
LEFT JOIN produto p ON f.id = p.id_fornecedor
WHERE p.id IS NULL;

-- Questão 12 - Produto mais caro
SELECT 
    p.nome, 
    p.preco, 
    c.nome AS categoria
FROM produto p
JOIN categoria c ON p.id_categoria = c.id
WHERE p.preco = (SELECT MAX(preco) FROM produto);

-- Questão 13 - Produtos sem vendas
SELECT 
    p.nome, 
    c.nome AS categoria, 
    p.preco
FROM produto p
JOIN categoria c ON p.id_categoria = c.id
LEFT JOIN item_pedido i ON p.id = i.id_produto
WHERE i.id IS NULL;

-- Questão 14 - Quantidade de itens por pedido
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    SUM(i.quantidade) AS total_itens
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY p.id, c.nome
ORDER BY total_itens DESC;

-- Questão 15 - Produtos vendidos
SELECT 
    pr.nome AS produto, 
    COUNT(i.id) AS quantidade_vendas
FROM produto pr
JOIN item_pedido i ON pr.id = i.id_produto
GROUP BY pr.id, pr.nome
ORDER BY quantidade_vendas DESC;

-- Questão 16 - Pedidos e pagamentos
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    p.status AS status_pedido, 
    pg.valor, 
    pg.metodo, 
    pg.status AS status_pagamento
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN pagamento pg ON p.id = pg.id_pedido;

-- Questão 17 - Pedidos sem pagamento
SELECT 
    p.id AS numero_pedido, 
    p.data_pedido, 
    c.nome AS cliente, 
    p.status
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id
LEFT JOIN pagamento pg ON p.id = pg.id_pedido
WHERE pg.id IS NULL;

-- Questão 18 - Pedidos e entregas
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    p.status AS status_pedido, 
    e.codigo_rastreio, 
    e.status AS status_entrega
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id
JOIN entrega e ON p.id = e.id_pedido;

-- Questão 19 - Pedidos sem entrega
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    p.data_pedido, 
    p.status
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id
LEFT JOIN entrega e ON p.id = e.id_pedido
WHERE e.id IS NULL;

-- Questão 20 - Avaliações dos produtos
SELECT 
    pr.nome AS produto, 
    c.nome AS cliente, 
    a.nota, 
    a.comentario
FROM avaliacao a
JOIN produto pr ON a.id_produto = pr.id
JOIN cliente c ON a.id_cliente = c.id;

-- Questão 21 - Média de avaliação por produto
SELECT 
    pr.nome AS produto, 
    ROUND(AVG(a.nota), 2) AS media_avaliacao
FROM produto pr
JOIN avaliacao a ON pr.id = a.id_produto
GROUP BY pr.id, pr.nome
ORDER BY media_avaliacao DESC;

-- Questão 22 - Quantidade de avaliações por produto
SELECT 
    pr.nome AS produto, 
    COUNT(a.id) AS qtd_avaliacoes
FROM produto pr
LEFT JOIN avaliacao a ON pr.id = a.id_produto
GROUP BY pr.id, pr.nome
ORDER BY qtd_avaliacoes DESC;

-- Questão 23 - Valor total de cada pedido
SELECT 
    p.id AS numero_pedido, 
    c.nome AS cliente, 
    SUM(i.quantidade * i.preco_unitario) AS valor_total
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY p.id, c.nome
ORDER BY valor_total DESC;

-- Questão 24 - Valor total dos pedidos por cliente
SELECT 
    c.nome AS cliente, 
    COUNT(DISTINCT p.id) AS qtd_pedidos, 
    SUM(i.quantidade * i.preco_unitario) AS valor_total
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY c.id, c.nome
ORDER BY valor_total DESC;

-- Questão 25 - Análise completa de clientes
SELECT 
    c.nome AS cliente, 
    c.cidade, 
    COUNT(DISTINCT p.id) AS qtd_pedidos, 
    SUM(i.quantidade) AS qtd_itens
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY c.id, c.nome, c.cidade
ORDER BY qtd_pedidos DESC;

-- Questão 26 - Clientes com alto volume de pedidos
SELECT 
    c.nome AS cliente, 
    COUNT(p.id) AS qtd_pedidos
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
GROUP BY c.id, c.nome
HAVING COUNT(p.id) > 2;

-- Questão 27 - Categorias com maior quantidade de produtos
SELECT 
    c.nome AS categoria, 
    COUNT(p.id) AS qtd_produtos
FROM categoria c
JOIN produto p ON c.id = p.id_categoria
GROUP BY c.id, c.nome
HAVING COUNT(p.id) > 2;

-- Questão 28 - Produtos com boas avaliações
SELECT 
    pr.nome AS produto, 
    ROUND(AVG(a.nota), 2) AS media_avaliacao, 
    COUNT(a.id) AS qtd_avaliacoes
FROM produto pr
JOIN avaliacao a ON pr.id = a.id_produto
GROUP BY pr.id, pr.nome
HAVING AVG(a.nota) >= 4;

-- Questão 29 - Clientes e valor de compras
SELECT 
    c.nome AS cliente, 
    COUNT(DISTINCT p.id) AS qtd_pedidos, 
    SUM(i.quantidade * i.preco_unitario) AS valor_total
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY c.id, c.nome
HAVING SUM(i.quantidade * i.preco_unitario) > 1000.00;

-- Questão 30 - Relatório geral da Empresa
SELECT 
    c.nome AS cliente, 
    c.cidade, 
    COUNT(DISTINCT p.id) AS qtd_pedidos, 
    SUM(i.quantidade) AS total_itens, 
    SUM(i.quantidade * i.preco_unitario) AS valor_total
FROM cliente c
JOIN pedido p ON c.id = p.id_cliente
JOIN item_pedido i ON p.id = i.id_pedido
GROUP BY c.id, c.nome, c.cidade
ORDER BY valor_total DESC;

-- Explicação da Questão 30
/*
1. Relacionamento e Junção de Tabelas (JOIN):
   A consulta inicia na tabela 'cliente' para buscar os dados do comprador (nome e cidade). 
   Em seguida, conecta com a tabela 'pedido' através da chave 'pedido.id_cliente = cliente.id', 
   trazendo os pedidos associados a cada cliente. Por fim, junta com 'item_pedido' usando 
   'item_pedido.id_pedido = pedido.id' para acessar os itens comprados em cada pedido.

2. Agrupamento (GROUP BY):
   Com o agrupamento por 'c.id', 'c.nome' e 'c.cidade', o banco consolida os vários itens 
   e pedidos de um mesmo cliente em uma única linha de resumo no resultado.

3. Funções de Agregação:
   - COUNT(DISTINCT p.id): Conta apenas as chaves primárias únicas dos pedidos do cliente, 
     evitando duplicação causada pelo JOIN com os múltiplos itens.
   - SUM(i.quantidade): Soma a quantidade total de produtos comprados pelo cliente.
   - SUM(i.quantidade * i.preco_unitario): Multiplica a quantidade pelo preço unitário 
     de cada item e soma todos os subtotais para obter o valor geral das compras do cliente.

4. Ordenação (ORDER BY):
   O resultado é ordenado de forma decrescente (DESC) com base no 'valor_total', 
   destacando os clientes que geraram maior receita para a loja no topo do relatório.
*/