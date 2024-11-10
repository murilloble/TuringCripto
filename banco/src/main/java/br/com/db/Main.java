package br.com.db;

import br.com.db.dao.ContaDao;
import br.com.db.exception.EntidadeNaoEncontradaException;
import br.com.db.model.transacao.Ativo;
import br.com.db.model.transacao.Transacao;
import br.com.db.model.usuario.Conta;
import br.com.db.model.usuario.ContaInvestimento;
import br.com.db.model.usuario.ContaPoupanca;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("*****************************************************************");
        System.out.println("*          Bem-vindo ao TURINGCRIPTO, sua loja de cripto!       *");
        System.out.println("*                                                               *");
        System.out.println("*                         . ------- .                           *");
        System.out.println("*                      .'             `.                        *");
        System.out.println("*                     /                  \\                      *");
        System.out.println("*                    |   $$$$$$$$$$$$$$   |                     *");
        System.out.println("*                    |         $$         |                     *");
        System.out.println("*                    |         $$         |                     *");
        System.out.println("*                    |         $$         |                     *");
        System.out.println("*                    |         $$         |                     *");
        System.out.println("*                     \\        $$        /                      *");
        System.out.println("*                      `.             .'                        *");
        System.out.println("*                        `-._______.-'                          *");
        System.out.println("*                                                               *");
        System.out.println("*****************************************************************");
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        ContaDao dao = null;
        try {
            dao = new ContaDao();
            int escolha;
            do {
                System.out.println("\nEscolha uma opção: \n1-Cadastrar Conta \n2-Pesquisar por Código da Conta \n3-Listar Contas \n4-Atualizar Conta \n5-Remover Conta \n6-Transferir Valores \n0-Sair");
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        cadastrar(scanner, dao);
                        break;
                    case 2:
                        pesquisar(scanner, dao);
                        break;
                    case 3:
                        listar(dao);
                        break;
                    case 4:
                        atualizar(scanner, dao);
                        break;
                    case 5:
                        remover(scanner, dao);
                        break;
                    case 6:
                        Transferir(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }finally {
            try {
                if (dao != null) {
                    dao.fecharConexao();  // Garantir que a conexão seja fechada após sair do menu
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
    private static void cadastrar(Scanner scanner, ContaDao dao) {
        String tipoConta= " ";
        try {
            int escolha;

            do {
                System.out.println("Escolha um tipo de conta: \n1-Poupança \n2-Investimento \n0-Sair");
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                         poupanca(scanner, dao);
                        break;
                    case 2:
                         investimento(scanner, dao);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 0);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

    }
    private static void investimento(Scanner scanner, ContaDao dao) throws SQLException {
        System.out.println("Digite o numero da conta:");
        String numeroDaConta = scanner.next() + scanner.nextLine();
        System.out.println("Digite o saldo:");
        double saldo = scanner.nextDouble();
        System.out.println("Digite o tipo da moeda");
        String tipoMoeda = scanner.next() + scanner.nextLine();
        System.out.println("Digite o rendimento esperado:");
        double rendimento = scanner.nextDouble();

        ArrayList lista = new ArrayList();
        lista.add("\nNumero Conta [ "+numeroDaConta + " ]");
        lista.add("\nSaldo [ "+saldo+" ]");
        lista.add("\nTipo da Moeda [ "+tipoMoeda+ " ]");
        lista.add("\nRendimento esperado ["+rendimento+" ]");
        try {
            dao = new ContaDao();
            int escolha;
            do {
                System.out.println("\nDeseja comfirmar o cadastro da conta ? " + lista +"\n1-SIM 2-NÃO" );
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:

                        ContaInvestimento ContaInvestimento = new ContaInvestimento(0, numeroDaConta, saldo, tipoMoeda, new java.sql.Date(new java.util.Date().getTime()), "investimento", rendimento);
                        dao.cadastrar(ContaInvestimento);
                        System.out.println("Conta cadastrada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Cadastro cancelado.");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 2);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
        }
    }
    private static void poupanca(Scanner scanner, ContaDao dao) throws SQLException {
        System.out.println("Digite o numero da conta:");
        String numeroDaConta = scanner.next() + scanner.nextLine();
        System.out.println("Digite o saldo:");
        double saldo = scanner.nextDouble();
        System.out.println("Digite o tipo da moeda");
        String tipoMoeda = scanner.next() + scanner.nextLine();
        System.out.println("Digite a taxa de juros:");
        double taxajuros = scanner.nextDouble();

        ArrayList lista = new ArrayList();
        lista.add("\nNumero Conta [ "+numeroDaConta + " ]");
        lista.add("\nSaldo [ "+saldo+" ]");
        lista.add("\nTipo da Moeda [ "+tipoMoeda+ " ]");
        lista.add("\nTaxa de Juros escolhida ["+taxajuros+" ]");
        try {
            int escolha;
            dao = new ContaDao();
            do {
                System.out.println("\nDeseja comfirmar o cadastro da conta ? " + lista +"\n1-SIM 2-NÃO" );
                escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        ContaPoupanca ContaPoupanca = new ContaPoupanca(0,numeroDaConta, saldo, tipoMoeda,new java.sql.Date(new java.util.Date().getTime()),  "poupanca", taxajuros);
                        dao.cadastrar(ContaPoupanca);
                        System.out.println("Conta cadastrada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Cadastro cancelado.");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 2);
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta: " + e.getMessage());
        }
        dao.fecharConexao();
    }
    private static void pesquisar(Scanner scanner, ContaDao dao) {
        System.out.println("Digite o código da conta:");
        long codigo = scanner.nextLong();
        try {
            Conta conta = dao.pesquisar(codigo);
            System.out.println("Conta encontrado:");
            System.out.println("Numero da Conta: " + conta.getNumeroConta()+ ", Moeda: " + conta.getTipoMoeda() + ", R$ " + conta.getSaldo());
            System.out.println("Conta: " +conta.getTipoConta() + ", Criada em " + conta.getDataCriacao());
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao pesquisar a conta: " + e.getMessage());
        }
    }
    private static void listar(ContaDao dao) {
        try {
            List<Conta> contas = dao.listar();
            System.out.println("Lista das contas:");
            for (Conta conta : contas) {
                System.out.println(conta.getIdConta() + " , " + conta.getTipoConta() + " " + conta.getNumeroConta() + ", " + conta.getDataCriacao() + ", " +conta.getTipoMoeda() + ", " + "R$ " + conta.getSaldo());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as contas: " + e.getMessage());
        }
    }
    private static void atualizar(Scanner scanner, ContaDao dao) {
        System.out.println("Digite o código da conta que deseja atualizar:");
        long codigo = scanner.nextLong();
        try {
            Conta conta = dao.pesquisar(codigo);
            System.out.println("Digite o numero da conta:");
            String numeroDaConta = scanner.next() + scanner.nextLine();
            System.out.println("Digite o saldo:");
            double saldo = scanner.nextDouble();
            System.out.println("Digite o tipo da moeda");
            String tipoMoeda = scanner.next() + scanner.nextLine();
            System.out.println("Digite o tipo de conta (Poupanca ou Investimento):");
            String tipoConta = scanner.next() + scanner.nextLine();
            if (tipoConta.toLowerCase().equals("poupanca")){
                System.out.println("Digite a taxa de juros:");
                double taxaJuros = scanner.nextDouble();
                ContaInvestimento contaInvestimento = new ContaInvestimento(conta.idConta, numeroDaConta, saldo, tipoMoeda, new java.sql.Date(new java.util.Date().getTime()), tipoConta, taxaJuros );
                dao.atualizar(contaInvestimento);
            } else if (tipoConta.toLowerCase().equals("investimento")) {
                System.out.println("Digite o rendimento esperado:");
                double rendimento = scanner.nextDouble();
                ContaInvestimento ContaInvestimento = new ContaInvestimento(conta.idConta,numeroDaConta, saldo, tipoMoeda,new java.sql.Date(new java.util.Date().getTime()),  tipoConta, rendimento);
                dao.atualizar(ContaInvestimento);
            }

            System.out.println("Conta atualizado com sucesso!");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            System.err.println("Erro ao atualizar a conta: " + e.getMessage());
        }
    }
    private static void remover(Scanner scanner, ContaDao dao) {
        System.out.println("Digite o código do cliente que deseja remover:");
        long codigo = scanner.nextLong();

        try {
            dao = new ContaDao();
            int escolha;
            do {
                System.out.println("Deseja confirmar a remoção do cliente [ " + codigo +" ]? \n1-SIM 2-NÃO" );
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        try {
                            dao.remover(codigo);
                            System.out.println("Cliente removido com sucesso!");
                        } catch (SQLException | EntidadeNaoEncontradaException e) {
                            System.err.println("Erro ao remover o Cliente: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Remoção cancelado.");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (escolha != 2);
            dao.fecharConexao();
        } catch (SQLException e) {
            System.err.println("Erro ao remover conta: " + e.getMessage());
        }
    }
    private static void Transferir(Scanner scanner, ContaDao dao) {
        System.out.println("Digite o numero da conta:");
        String numeroDaConta = scanner.next() + scanner.nextLine();
        System.out.println("Digite o tipo de transferencia(TED, PIX):");
        String tipo = scanner.next() + scanner.nextLine();
        System.out.println("Digite o montante a tranferir");
        double montante = scanner.nextDouble();
        System.out.println("Digite o Preço atual");
        double preçoAtual = scanner.nextDouble();
        try {
            Transacao transacao = new Transacao(new Conta(0,numeroDaConta,0,"",new java.sql.Date(new java.util.Date().getTime()),""),tipo, montante, new java.sql.Date(new java.util.Date().getTime()), new Ativo(numeroDaConta, "", preçoAtual, new java.sql.Date(new java.util.Date().getTime())));
            dao.transferir(transacao);
            System.out.println("Transação efetuada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao fazer Transação: " + e.getMessage());
        }



    }
}