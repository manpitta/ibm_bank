-- Inserindo dados de exemplo para a tabela de clientes
INSERT INTO cliente (id, nome, idade, email) VALUES
(1, 'João Silva', 30,  'joao.silva@example.com'),
(2, 'Maria Oliveira', 25, 'maria.oliveira@example.com'),
(3, 'Carlos Pereira', 40, 'carlos.pereira@example.com');

-- Inserindo dados de exemplo para a tabela de contas (relacionada a clientes)
INSERT INTO conta (id, numero, saldo, cliente_id, tipo_conta, status) VALUES
(1, '123456789', 1500.00, 1, 'Corrente', 'ATIVA'),
(2, '987654321', 2000.00, 2, 'Poupança', 'ATIVA'),
(3, '456789123', 500.00, 3, 'Corrente', 'ATIVA');

-- Inserindo dados de exemplo para a tabela de contas de crédito (relacionada a contas)
INSERT INTO conta_credito (id, conta_id, limite_credito, saldo_utilizado, data_fechamento, data_pagamento, status) VALUES
(1, 1, 5000.00, 1500.00, '2024-10-20', '2024-11-01', 'ATIVA'),
(2, 2, 10000.00, 2500.00, '2024-10-22', '2024-11-03', 'ATIVA'),
(3, 3, 3000.00, 500.00, '2024-10-25', '2024-11-05', 'ATIVA');

-- Inserindo dados de exemplo para a tabela de movimentações (relacionada a contas e contas de crédito)
INSERT INTO movimentacao (id, descricao, valor, tipo, data_movimentacao, conta_id, conta_credito_id, data_competencia, status_pagamento) VALUES
(1, 'Compra no Supermercado', -200.00, 'Débito', '2024-10-01', 1, 1, '2024-10-15', 'PAGO'),
(2, 'Transferência Recebida', 500.00, 'Crédito', '2024-10-05', 2, 2, '2024-10-20', 'PENDENTE'),
(3, 'Pagamento de Conta', -150.00, 'Débito', '2024-10-10', 3, 3, '2024-10-25', 'PAGO'),
(4, 'Depósito em Conta', 1000.00, 'Crédito', '2024-10-12', 1, NULL, '2024-10-30', 'PENDENTE'),
(5, 'Saque', -300.00, 'Débito', '2024-10-15', 2, NULL, '2024-11-01', 'PAGO');


