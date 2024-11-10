package br.com.db.dao;

import  br.com.db.exception.EntidadeNaoEncontradaException;
import br.com.db.factory.ConnectionFactory;
import br.com.db.model.transacao.Ativo;
import br.com.db.model.transacao.Transacao;
import br.com.db.model.usuario.Conta;
import br.com.db.model.usuario.ContaInvestimento;
import br.com.db.model.usuario.ContaPoupanca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ContaDao {
    private Connection conexao;
    public ContaDao() throws SQLException {
        conexao = ConnectionFactory.getConnection();
    }
    public void cadastrar(Object obj) throws SQLException {
        if (obj instanceof ContaPoupanca){
            ContaPoupanca contaPoupanca = (ContaPoupanca) obj;
            PreparedStatement stmConta = conexao.prepareStatement("INSERT INTO CONTA (ID_CONTA,NUMERO_CONTA,SALDO,TIPO_MOEDA,DATA_CRIACAO,TIPO_CONTA) VALUES (seq_conta.nextval, ?, ?, ?, ?, ?)");
            stmConta.setString(1, contaPoupanca.getNumeroConta());
            stmConta.setDouble(2, contaPoupanca.getSaldo());
            stmConta.setString(3, contaPoupanca.getTipoMoeda());
            stmConta.setDate(4, new java.sql.Date(contaPoupanca.getDataCriacao().getTime()));
            stmConta.setString(5, contaPoupanca.getTipoConta());
            stmConta.executeUpdate();

            PreparedStatement stmContaPoupanca = conexao.prepareStatement("INSERT INTO CONTAPOUPANCA (ID_CONTA,TAXA_JUROS) VALUES (seq_conta.currval, ?)");
            stmContaPoupanca.setDouble(1, contaPoupanca.getTaxaJuros());
            stmContaPoupanca.executeUpdate();
        }else if(obj instanceof ContaInvestimento){
            ContaInvestimento contaInvestimento = (ContaInvestimento) obj;
            PreparedStatement stmConta = conexao.prepareStatement("INSERT INTO CONTA (ID_CONTA,NUMERO_CONTA,SALDO,TIPO_MOEDA,DATA_CRIACAO,TIPO_CONTA) VALUES (seq_conta.nextval, ?, ?, ?, ?, ?)");
            stmConta.setString(1, contaInvestimento.getNumeroConta());
            stmConta.setDouble(2, contaInvestimento.getSaldo());
            stmConta.setString(3, contaInvestimento.getTipoMoeda());
            stmConta.setDate(4, new java.sql.Date(contaInvestimento.getDataCriacao().getTime()));
            stmConta.setString(5, contaInvestimento.getTipoConta());
            stmConta.executeUpdate();

            PreparedStatement stmContaPoupanca = conexao.prepareStatement("INSERT INTO CONTAINVESTIMENTO (ID_CONTA,RENDIMENTO_ESPERADO ) VALUES (seq_conta.currval, ?)");
            stmContaPoupanca.setDouble(1, contaInvestimento.getRendimentoEsperado());
            stmContaPoupanca.executeUpdate();
        }
    }
    private Conta parseConta(ResultSet result) throws SQLException {
        long idConta = result.getLong("ID_CONTA");
        String numeroConta = result.getString("Numero_Conta");
        String tipoMoeda = result.getString("Tipo_Moeda");
        double saldo = result.getDouble("Saldo");
        Date dataCriacao = result.getDate("Data_Criacao");
        String tipoConta = result.getString("TIPO_CONTA");
        return new Conta(idConta, numeroConta, saldo, tipoMoeda, dataCriacao, tipoConta);
    }
    public Conta pesquisar(long codigo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM CONTA WHERE ID_conta = ?");
        stm.setLong(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        return parseConta(result);
    }
    public List<Conta> listar() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM Conta");
        ResultSet result = stm.executeQuery();
        List<Conta> lista = new ArrayList<>();
        while (result.next()){
            lista.add(parseConta(result));
        }
        return lista;
    }
    public void atualizar(Object obj) throws SQLException {
        if (obj instanceof ContaPoupanca){
            ContaPoupanca contaPoupanca = (ContaPoupanca) obj;
            PreparedStatement stmConta = conexao.prepareStatement("UPDATE Conta SET NUMERO_CONTA = ?, SALDO = ?, TIPO_MOEDA = ?, DATA_CRIACAO = ?, TIPO_CONTA = ? where Id_conta = ?");
            stmConta.setString(1, contaPoupanca.getNumeroConta());
            stmConta.setDouble(2, contaPoupanca.getSaldo());
            stmConta.setString(3, contaPoupanca.getTipoMoeda());
            stmConta.setDate(4, (java.sql.Date) contaPoupanca.getDataCriacao());
            stmConta.setString(5, contaPoupanca.getTipoConta());

            stmConta.setLong(6, contaPoupanca.getIdConta());
            stmConta.executeUpdate();

            PreparedStatement stmContaPoupanca = conexao.prepareStatement("UPDATE CONTAPOUPANCA SET TAXA_JUROS = ? where id_conta = ?");
            stmContaPoupanca.setDouble(1, contaPoupanca.getTaxaJuros());
            stmContaPoupanca.setLong(2, contaPoupanca.getIdConta());
            stmContaPoupanca.executeUpdate();

        }else if(obj instanceof ContaInvestimento){
            ContaInvestimento contaInvestimento = (ContaInvestimento) obj;
            PreparedStatement stmConta = conexao.prepareStatement("UPDATE Conta SET NUMERO_CONTA = ?, SALDO = ?, TIPO_MOEDA = ?, DATA_CRIACAO = ?, TIPO_CONTA = ? where Id_conta = ?");
            stmConta.setString(1, contaInvestimento.getNumeroConta());
            stmConta.setDouble(2, contaInvestimento.getSaldo());
            stmConta.setString(3, contaInvestimento.getTipoMoeda());
            stmConta.setDate(4, (java.sql.Date) contaInvestimento.getDataCriacao());
            stmConta.setString(5, contaInvestimento.getTipoConta());

            stmConta.setLong(6, contaInvestimento.getIdConta());
            stmConta.executeUpdate();

            PreparedStatement stmContaInvestimento = conexao.prepareStatement("UPDATE CONTAINVESTIMENTO SET RENDIMENTO_ESPERADO = ? where id_conta = ?");
            stmContaInvestimento.setDouble(1, contaInvestimento.getRendimentoEsperado());
            stmContaInvestimento.setLong(2, contaInvestimento.getIdConta());
            stmContaInvestimento.executeUpdate();
        }



    }
    public void remover(long codigo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from conta where Id_conta = ?");
        stm.setLong(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Conta não encontrado para ser removido");
    }
    public void fecharConexao() throws SQLException {
        conexao.close();
    }
    public void transferir(Transacao obj) throws SQLException {
        Transacao transacao = obj;
        PreparedStatement stmConta = conexao.prepareStatement("INSERT INTO TRANSACAO (ID_TRANSACAO,ID_Conta,Tipo,Montante,Data,ID_Ativo) VALUES (seq_transferencia.nextval, ?, ?, ?, ?, seq_transferencia.nextval)");
        stmConta.setLong(1, transacao.getConta().idConta);
        stmConta.setString(2, transacao.getTipo());
        stmConta.setDouble(3, transacao.getMontante());
        stmConta.setDate(4, new java.sql.Date(transacao.getData().getTime()));
        stmConta.executeUpdate();

        PreparedStatement stmContaPoupanca = conexao.prepareStatement("INSERT INTO ATIVO (ID_Ativo,Nome,Codigo,Preco_Atual,Data_Atualizacao) VALUES (seq_transferencia.currval, ?, seq_id_codigo.nextval, ?, ?)");
        stmContaPoupanca.setString(1, transacao.getAtivo().getNome());
        stmContaPoupanca.setDouble(2, transacao.getAtivo().getPrecoAtual());
        stmContaPoupanca.setDate(3, new java.sql.Date(transacao.getAtivo().getDataAtualizacao().getTime()));
        stmContaPoupanca.executeUpdate();
    }
}
