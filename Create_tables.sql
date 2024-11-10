-- Criação de sequencia para conta
CREATE SEQUENCE seq_conta
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
   
-- Criação da tabela Usuario
CREATE TABLE Usuario (
    ID_Usuario NUMBER PRIMARY KEY,
    Nome VARCHAR2(100) NOT NULL,
    Email VARCHAR2(30) NOT NULL,
    Data_Criacao TIMESTAMP NOT NULL
);

-- Criação da tabela Conta
CREATE TABLE Conta (
    ID_Conta NUMBER PRIMARY KEY,
    Numero_Conta VARCHAR2(100) NOT NULL,
    Saldo NUMBER(15, 2) NOT NULL,
    Tipo_Moeda VARCHAR2(100) NOT NULL,
    Data_Criacao DATE NOT NULL,
    Tipo_Conta VARCHAR2(50) NOT NULL
);

-- Criação da tabela Ativo
CREATE TABLE Ativo (
    ID_Ativo NUMBER PRIMARY KEY,
    Nome VARCHAR2(100) NOT NULL,
    Codigo VARCHAR2(50) NOT NULL,
    Preco_Atual NUMBER(18, 2) NOT NULL,
    Data_Atualizacao TIMESTAMP NOT NULL
);

-- Criação da tabela ContaPoupanca
CREATE TABLE ContaPoupanca (
    ID_Conta NUMBER PRIMARY KEY,
    Taxa_Juros NUMBER(5, 2) NOT NULL,
    CONSTRAINT FK_ContaPoupanca FOREIGN KEY (ID_Conta) REFERENCES Conta(ID_Conta) ON DELETE CASCADE
);

-- Criação da tabela ContaInvestimento
CREATE TABLE ContaInvestimento (
    ID_Conta NUMBER PRIMARY KEY,
    Rendimento_Esperado NUMBER(5, 2) NOT NULL,
    CONSTRAINT FK_ContaInvestimento FOREIGN KEY (ID_Conta) REFERENCES Conta(ID_Conta) ON DELETE CASCADE
);

-- Criação da tabela Transacao
CREATE TABLE Transacao (
    ID_Transacao NUMBER PRIMARY KEY,
    ID_Conta NUMBER NOT NULL,
    Tipo VARCHAR2(50) NOT NULL,
    Montante NUMBER(18, 2) NOT NULL,
    Data TIMESTAMP NOT NULL,
    ID_Ativo NUMBER NOT NULL,
    CONSTRAINT FK_ID_Conta FOREIGN KEY (ID_Conta) REFERENCES Conta(ID_Conta),
    CONSTRAINT FK_ID_Ativo FOREIGN KEY (ID_Ativo) REFERENCES Ativo(ID_Ativo)
);

-- Índices
CREATE INDEX idx_usuario_email ON Usuario (Email);
CREATE INDEX idx_conta_numero_conta ON Conta (Numero_Conta);
CREATE INDEX idx_conta_tipo_conta ON Conta (Tipo_Conta);
CREATE INDEX idx_conta_tipo_moeda ON Conta (Tipo_Moeda);
CREATE INDEX idx_conta_poupanca_taxa_juros ON ContaPoupanca (Taxa_Juros);
CREATE INDEX idx_conta_investimento_rendimento ON ContaInvestimento (Rendimento_Esperado);
CREATE INDEX idx_transacao_id_conta ON Transacao (ID_Conta);
CREATE INDEX idx_transacao_id_ativo ON Transacao (ID_Ativo);
CREATE INDEX idx_transacao_tipo ON Transacao (Tipo);