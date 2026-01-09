-- Generic seed data for projeto_haras
-- Run this SQL against the application's H2 database (jdbc:h2:mem:bancoharas) or file DB.
-- Disable referential integrity for seed (H2 only)
SET REFERENTIAL_INTEGRITY FALSE;

-- Pessoas (base users)
INSERT INTO Pessoa (id, email, nome, senha) VALUES (1, 'adm@email.com', 'Administrador', 'a7ec159e76d31f3d869712e677deb4b352e7cb6594838ba3cf4579f2a4490245');
INSERT INTO Pessoa (id, email, nome, senha) VALUES (2, 'joao.proprietario@example.com', 'João Proprietário', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251');
INSERT INTO Pessoa (id, email, nome, senha) VALUES (3, 'maria.cuidador@example.com', 'Maria Cuidadora', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251');
INSERT INTO Pessoa (id, email, nome, senha) VALUES (4, 'pedro.veterinario@example.com', 'Pedro Veterinário', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251');

-- Administrador (subclass referencing Pessoa.id)
INSERT INTO Administrador (id, nomeEmpresa) VALUES (1, 'Haras Principal');

-- Baias (let DB generate ids)
-- Baias (explicit ids to make associations simple)
INSERT INTO Baia (id, tamanho, tipo) VALUES (1, 12.5, 'Grande');
INSERT INTO Baia (id, tamanho, tipo) VALUES (2, 8.0, 'Média');

-- Alimentos (let DB generate ids)
-- Alimentos (explicit ids)
INSERT INTO Alimento (id, nome, preco) VALUES (1, 'Ração Top', 25.50);
INSERT INTO Alimento (id, nome, preco) VALUES (2, 'Feno Especial', 12.00);

-- Serviços adicionais (let DB generate ids)
-- Serviços adicionais (explicit ids)
INSERT INTO ServicoAdicional (servico_adicional_id, servico, preco) VALUES (1, 'Banho', 40.0);
INSERT INTO ServicoAdicional (servico_adicional_id, servico, preco) VALUES (2, 'Corte de Casco', 30.0);

-- Subclasses that reuse Pessoa.id: use SELECT to map correctly
INSERT INTO Proprietario (id, cpf, limiteFatura)
SELECT p.id, '111.222.333-44', 3 FROM Pessoa p WHERE p.email='joao.proprietario@example.com';

INSERT INTO Cuidador (id, dataInicioContrato)
SELECT p.id, DATE '2022-01-01' FROM Pessoa p WHERE p.email='maria.cuidador@example.com';

INSERT INTO Veterinario (id, crmv)
SELECT p.id, 'CRMV-9999' FROM Pessoa p WHERE p.email='pedro.veterinario@example.com';

-- Equinos: assign foreign keys by subselects (find baia, pessoa ids)
-- Equinos (explicit ids referencing known parents)
INSERT INTO Equino (id, nascimento, nome, raca, sexo, baia_id, cuidador_id, proprietario_id, veterinario_id)
VALUES (1, DATE '2018-05-05', 'Estrela', 'Mangalarga', 'F', 1, 3, 2, 4);

INSERT INTO Equino (id, nascimento, nome, raca, sexo, baia_id, cuidador_id, proprietario_id, veterinario_id)
VALUES (2, DATE '2020-09-10', 'Fujão', 'Puro-Sangue', 'M', 2, 3, 2, 4);

-- Associações equino-alimento: use subselects to find recently created ids
-- Associações equino-alimento (explicit ids)
INSERT INTO Equino_Alimento (equino_id, alimento_id) VALUES (1, 1);
INSERT INTO Equino_Alimento (equino_id, alimento_id) VALUES (1, 2);
INSERT INTO Equino_Alimento (equino_id, alimento_id) VALUES (2, 1);

-- Associações equino-servico: map servico_adicional by name
-- Associações equino-servico (explicit ids)
INSERT INTO equino_servico (qtd, timestamp, servico_adicional_id, equino_id) VALUES (1, CURRENT_TIMESTAMP(), 1, 1);
INSERT INTO equino_servico (qtd, timestamp, servico_adicional_id, equino_id) VALUES (2, CURRENT_TIMESTAMP(), 2, 2);

-- Faturas: link to proprietario via Pessoa.email
INSERT INTO Fatura (create_at, diaMaxPagamento, foiPaga, valor, proprietario_id)
VALUES (CURRENT_DATE(), DATE '2026-02-01', FALSE, 150.00, (SELECT id FROM Pessoa WHERE email='joao.proprietario@example.com' LIMIT 1));

-- Re-enable referential integrity
SET REFERENTIAL_INTEGRITY TRUE;

-- Notes:
-- This script uses subselects to resolve generated ids and avoid explicit id assumptions.
