package br.com.patinhas;

import br.com.db.model.transacao.Ativo;
import br.com.db.model.transacao.Transacao;
import br.com.db.model.usuario.Conta;
import br.com.db.model.usuario.ContaInvestimento;
import br.com.db.model.usuario.ContaPoupanca;
import br.com.db.model.usuario.Usuario;
import java.util.Date;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando um usuário
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String emailUsuario = scanner.nextLine();
        System.out.print("Senha do usuário: ");
        String senhaUsuario = scanner.nextLine();
        Date dataCriacaoUsuario = new Date();

        Usuario usuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario, dataCriacaoUsuario);

        // Criando uma conta
        System.out.print("Número da conta: ");
        String numeroConta = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine();  // Limpar o buffer
        System.out.print("Tipo de moeda: ");
        String tipoMoeda = scanner.nextLine();
        Date dataCriacaoConta = new Date();
        System.out.print("Tipo de conta: ");
        String tipoConta = scanner.nextLine();

        Conta contaInvestimento = new ContaInvestimento(numeroConta, saldoInicial, tipoMoeda, dataCriacaoConta, 0.05);
        Conta contaPoupanca = new ContaPoupanca(numeroConta, saldoInicial, tipoMoeda, (java.sql.Date) dataCriacaoConta, tipoConta,0.01);

        // Criando um ativo
        System.out.print("Nome do ativo: ");
        String nomeAtivo = scanner.nextLine();
        System.out.print("Código do ativo: ");
        String codigoAtivo = scanner.nextLine();
        System.out.print("Preço atual do ativo: ");
        double precoAtivo = scanner.nextDouble();
        Date dataAtualizacaoAtivo = new Date();

        Ativo ativo = new Ativo(nomeAtivo, codigoAtivo, precoAtivo, dataAtualizacaoAtivo);

        // Criando uma transação
        System.out.print("Tipo de transação (compra/venda): ");
        scanner.nextLine();  // Limpar o buffer
        String tipoTransacao = scanner.nextLine();
        System.out.print("Montante da transação: ");
        double montanteTransacao = scanner.nextDouble();
        Date dataTransacao = new Date();

        Transacao transacao = new Transacao(tipoTransacao, montanteTransacao, dataTransacao, ativo);

        // Escolhendo valores de deposito e saque
        System.out.print("Valor de deposito: ");
        double valorDeposito = scanner.nextDouble();
        System.out.print("Valor de Saque: ");
        double valorSaque = scanner.nextDouble();

        contaPoupanca.depositar(valorDeposito);
        contaInvestimento.sacar(valorSaque);

        // Exibindo informações
        System.out.println("\nInformações do Usuário:");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());

        System.out.println("\nInformações da Conta:");
        System.out.println("Número da Conta: " + contaInvestimento.getNumeroConta());
        System.out.println("Saldo: " + contaInvestimento.getSaldo());
        System.out.println("Tipo de Moeda: " + contaInvestimento.getTipoMoeda());

        System.out.println("\nInformações do Ativo:");
        System.out.println("Nome do Ativo: " + ativo.getNome());
        System.out.println("Código do Ativo: " + ativo.getCodigo());
        System.out.println("Preço Atual: " + ativo.getPrecoAtual());

        System.out.println("\nInformações da Transação:");
        System.out.println("Tipo: " + transacao.getTipo());
        System.out.println("Montante: " + transacao.getMontante());
        System.out.println("Data: " + transacao.getData());
        System.out.println("Ativo: " + transacao.getAtivo().getNome());

        System.out.println("Saldo Conta Poupança: " + contaPoupanca.getSaldo());
        System.out.println("Saldo Conta Investimento: " + contaInvestimento.getSaldo());

    }
}



