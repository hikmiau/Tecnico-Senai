USE modelo_recuperacao;

INSERT INTO cliente (id, nome, cpf, telefone, email, endereco) VALUES
(1, 'Lucas Almeida', '12345678901', '47991234567', 'lucas@gmail.com', 'Rua das Flores, 120'),
(2, 'Mariana Souza', '23456789012', '47987654321', 'mariana@gmail.com', 'Rua XV de Novembro, 450'),
(3, 'Pedro Martins', '34567890123', '47996541238', 'pedro@gmail.com', 'Rua Amazonas, 85');


INSERT INTO equipamento (id, cliente_id, tipo, marca, modelo, numero_serie, descricao, status) VALUES
(1, 1, 'Notebook', 'Dell', 'Inspiron 15', 'DL123456', 'Notebook com problema de inicialização', 'Em manutenção'),
(2, 1, 'Celular', 'Samsung', 'Galaxy A54', 'SM789321', 'Tela com falhas', 'Aguardando peça'),
(3, 2, 'Notebook', 'Lenovo', 'IdeaPad 3', 'LN456789', 'Equipamento lento', 'Finalizado'),
(4, 3, 'Desktop', 'ASUS', 'TUF Gaming', 'AS987654', 'Computador desligando sozinho', 'Em manutenção');


INSERT INTO ordem_servico (id, cliente_id, equipamento_id, data_abertura, data_fechamento, descricao, status, prioridade) VALUES
(1, 1, 1, '2026-09-20', NULL, 'Verificar falha na inicialização', 'Aberta', 'Alta'),
(2, 1, 2, '2026-09-21', NULL, 'Analisar problema apresentado na tela', 'Em andamento', 'Média'),
(3, 2, 3, '2026-09-18', '2026-09-22', 'Realizar manutenção e otimização do sistema', 'Finalizada', 'Baixa'),
(4, 3, 4, '2026-09-23', NULL, 'Verificar desligamentos durante o uso', 'Aberta', 'Alta');


CREATE ROLE 'perfil_atendente';
CREATE ROLE 'perfil_tecnico';
CREATE ROLE 'perfil_admin';


GRANT SELECT, INSERT, UPDATE ON modelo_recuperacao.cliente
TO 'perfil_atendente';

GRANT SELECT, INSERT, UPDATE ON modelo_recuperacao.equipamento
TO 'perfil_atendente';

GRANT SELECT, INSERT, UPDATE ON modelo_recuperacao.ordem_servico
TO 'perfil_atendente';


GRANT SELECT ON modelo_recuperacao.cliente
TO 'perfil_tecnico';

GRANT SELECT, UPDATE ON modelo_recuperacao.equipamento
TO 'perfil_tecnico';

GRANT SELECT, INSERT, UPDATE ON modelo_recuperacao.ordem_servico
TO 'perfil_tecnico';


GRANT ALL PRIVILEGES ON modelo_recuperacao.*
TO 'perfil_admin';


CREATE USER 'atendente'@'localhost' IDENTIFIED BY '1234';
CREATE USER 'tecnico'@'localhost' IDENTIFIED BY '1234';
CREATE USER 'admin'@'localhost' IDENTIFIED BY '1234';


GRANT 'perfil_atendente' TO 'atendente'@'localhost';
GRANT 'perfil_tecnico' TO 'tecnico'@'localhost';
GRANT 'perfil_admin' TO 'admin'@'localhost';


SET DEFAULT ROLE 'perfil_atendente' TO 'atendente'@'localhost';
SET DEFAULT ROLE 'perfil_tecnico' TO 'tecnico'@'localhost';
SET DEFAULT ROLE 'perfil_admin' TO 'admin'@'localhost';