CREATE TABLE IF NOT EXISTS credito 
(id_transacao INT AUTO_INCREMENT PRIMARY KEY, 
conta_id INT, 
valor_credito DECIMAL,
cliente_id INT,
tipo_conta VARCHAR(30));

INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500603806, 1000.00, 1, 'contacorrente');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500603806, 2000.00, 1, 'contacorrente');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500603806, 3000.00, 1, 'contacorrente');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500603806, 3000.00, 1, 'contacorrente');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500500005, 1000.00, 2, 'investimento');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500500005, 2000.00, 2, 'investimento');
INSERT INTO credito (conta_id, valor_credito, cliente_id, tipo_conta) VALUES (1500500005, 5000.00, 2, 'investimento');					 
