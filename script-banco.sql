DROP DATABASE IF EXISTS projeto;
CREATE DATABASE projeto;
USE projeto;

CREATE TABLE usuario (
    idusuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    email VARCHAR(255),
    datanascimento DATE,
    login VARCHAR(255),
    senha VARCHAR(255),
    PRIMARY KEY (idusuario)
);

CREATE TABLE receita (
    idreceita INT NOT NULL AUTO_INCREMENT,
    idusuario INT,
    descricao VARCHAR(255),
    valor DECIMAL(10 , 2 ),
    datareceita DATE,
    FOREIGN KEY (idusuario)
        REFERENCES usuario (idusuario)
        ON DELETE CASCADE,
    PRIMARY KEY (idreceita)
);

CREATE TABLE despesa (
    iddespesa INT NOT NULL AUTO_INCREMENT,
    idusuario INT,
    descricao VARCHAR(255),
    valor DECIMAL(10 , 2 ),
    datavencimento DATE,
    datapagamento DATE,
    FOREIGN KEY (idusuario)
        REFERENCES usuario (idusuario),
    PRIMARY KEY (iddespesa)
);

INSERT INTO usuario (nome, cpf, email, datanascimento, login, senha) 
VALUES ('Maria', '01234567890', 'malu@gmail.com', '2003-09-29', 'malu', 'malu');

INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-01-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 4000.00, '2023-01-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-02-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 5000.00, '2023-02-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-03-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 2000.00, '2023-03-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 3500.00, '2023-03-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-04-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-05-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 5000.00, '2023-05-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 8000.00, '2023-06-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 1000.00, '2023-06-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-07-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 5000.00, '2023-07-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-08-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-09-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 2000.00, '2023-09-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-10-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 3000.00, '2023-10-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 3000.00, '2023-10-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-11-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 4000.00, '2023-11-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2023-12-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 5000.00, '2023-12-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'salário', 9000.00, '2022-06-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'consultoria', 6000.00, '2022-06-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (1, 'aulas', 5000.00, '2022-06-21');

INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-01-10', '2023-01-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 3000.00, '2023-01-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-02-10', '2023-02-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-02-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-03-10', '2023-03-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-03-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 4000.00, '2023-03-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-04-10', '2023-04-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-05-10', '2023-05-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-05-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-06-10', '2023-06-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 2000.00, '2023-06-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-07-10', '2023-07-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-07-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-08-10', '2023-08-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-08-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 3500.00, '2023-08-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-09-10', '2023-09-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-10-10', '2023-10-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 4500.00, '2023-10-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-11-10', '2023-11-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2023-12-10', '2023-12-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2023-12-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 5000.00, '2023-12-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Aluguel', 2500.00, '2022-12-10', '2022-12-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Escola', 1500.00, '2022-12-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (1, 'Comida', 5000.00, '2022-12-12', NULL);

INSERT INTO usuario (nome, cpf, email, datanascimento, login, senha) VALUES ('Victoria', '08876543210', 'vic@gmail.com', '2002-03-01', 'zelda', 'zelda');

INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'salário', 9000.00, '2023-05-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'consultoria', 6000.00, '2023-06-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'aulas', 5000.00, '2023-07-21');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'salário', 9000.00, '2023-08-01');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'consultoria', 6000.00, '2023-09-10');
INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (2, 'aulas', 5000.00, '2023-10-21');

INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Aluguel', 2500.00, '2023-05-10', '2023-05-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Escola', 1500.00, '2023-06-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Comida', 5000.00, '2023-07-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Aluguel', 2500.00, '2023-08-10', '2023-05-10');
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Escola', 1500.00, '2023-09-12', NULL);
INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (2, 'Comida', 5000.00, '2023-10-12', NULL);