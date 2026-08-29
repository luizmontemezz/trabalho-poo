package view;

import com.google.zxing.WriterException;
import controller.Cadastro;
import model.Carrinho;
import model.Cliente;
import model.Funcionaro;
import model.Roupas;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner leitura = new Scanner(System.in);
    private static boolean validar = true;
    private static int idCliCont = 0;
    private static int idFuncCont = 0;
    private static Cadastro cadastro = new Cadastro();
    private static Carrinho carrinho = new Carrinho();

    public static void main(String[] args) {
        cadastro.carregarTudo();
        System.out.println("Bem vindo ao menu da loja de roupas!");
        while (validar){
            menuLogin();
        }
        System.out.println("Até mais!");
    }

    public static void verImagemCodigoBarras(){
        System.out.println("Digite o código de barras da roupa:");
        leitura.nextLine();
        String codigo = leitura.nextLine();
        Roupas roupa = cadastro.buscarRoupa(codigo);
        if (roupa == null) {
            System.out.println("Nenhuma roupa encontrada com esse código de barras.");
            return;
        }
        File pastaImagens = new File("imagens");
        if (!pastaImagens.exists()) {
            pastaImagens.mkdirs();
        }
        String caminhoArquivo = "imagens" + File.separator + "codigo_" + codigo + ".png";
        try {
            roupa.gerarImagemCodigoBarras(caminhoArquivo);
            System.out.println("Imagem do código de barras gerada em: " + caminhoArquivo);
            File imagem = new File(caminhoArquivo);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(imagem);
            } else {
                System.out.println("Abra o arquivo manualmente para visualizar a imagem.");
            }
        } catch (WriterException | IOException e) {
            System.out.println("Erro ao gerar a imagem do código de barras: " + e.getMessage());
        }
    }

    public static void menuLogin(){
        String opc = new String();
        System.out.println("========== MENU ==========");
        System.out.println("Quem vai entrar agora?");
        System.out.println("1 | Gerente");
        System.out.println("2 | Funcionário");
        System.out.println("3 | Cliente");
        System.out.println("==========================");
        System.out.println("4 | Sair");
        System.out.println("==========================");
        System.out.println("Digite o número da opção desejada:");
        opc = leitura.next();
        switch (opc){
            case "1":
                menuGerente(opc);
                break;
            case "2":
                menuFuncionario(opc);
                break;
            case "3":
                menuCliente(opc);
                break;
            case "4":
                System.out.println("Desligando sistema. Até mais!");
                validar = false;
                break;
            default:
                System.out.println("Opção inválida. Tente novamente!");
                break;
        }

    }

    public static void menuGerente(String opc){
        String opc2 = new String();
        System.out.println("========== MENU GERENTE ==========");
        System.out.println("1 | Cadastrar");
        System.out.println("2 | Listar");
        System.out.println("3 | Pesquisar");
        System.out.println("4 | Ver imagem do código de barras");
        System.out.println("==========================");
        System.out.println("0 | Voltar");
        System.out.println("==========================");
        System.out.println("Digite o número da opção desejada:");
        opc = leitura.next();
        switch (opc){
            case "1":
                System.out.println("O que você deseja cadastrar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("1 | Funcionário(s)");
                System.out.println("2 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("3 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("4 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        Funcionaro f = new Funcionaro();
                        System.out.println("Digite o nome do funcionario:");
                        leitura.nextLine();
                        f.setNome(leitura.nextLine());
                        System.out.println("Digite o CPF do funcionario:");
                        f.setCpf(leitura.nextLine());
                        System.out.println("Digite o cargo do funcionario");
                        f.setCargo(leitura.nextLine());
                        System.out.println("Digite o salario do funcionaio:");
                        f.setSalario(leitura.nextDouble());
                        leitura.nextLine();
                        System.out.println("Digite o endereco do funcionario:");
                        f.setEndereco(leitura.nextLine());
                        System.out.println("O ID n.º "+idFuncCont +" foi dado ao cliente.");
                        f.setIdFunc(String.valueOf(idFuncCont));
                        idFuncCont+=1;
                        cadastro.cadastrarFuncionario(f);
                        System.out.println("Dados salvos em arquivo!");
                        menuGerente(opc);
                        break;
                    case "2":
                        Cliente c = new Cliente();
                        System.out.println("Digite o nome do cliente:");
                        leitura.nextLine();
                        c.setNome(leitura.nextLine());
                        System.out.println("Digite o CPF do cliente:");
                        c.setCpf(leitura.nextLine());
                        System.out.println("Digite o email do cliente:");
                        c.setEmail(leitura.nextLine());
                        System.out.println("Digite o endereco do cliente:");
                        c.setEndereco(leitura.nextLine());
                        System.out.println("O ID n.º "+idCliCont +" foi dado ao cliente.");
                        c.setIdCliente(String.valueOf(idCliCont));
                        idCliCont+=1;
                        cadastro.cadastrarCliente(c);
                        System.out.println("Dados salvos em arquivo!");
                        menuGerente(opc);
                        break;
                    case "3":
                        Roupas r = new Roupas();
                        System.out.println("Digite a marca da peca:");
                        leitura.nextLine();
                        r.setMarca(leitura.nextLine());
                        System.out.println("Digite a cor da peca:");
                        r.setCor(leitura.nextLine());
                        System.out.println("Digite o publico-alvo da peca:");
                        r.setSexo(leitura.nextLine());
                        System.out.println("Digite o tamanho da peca:");
                        r.setTamanho(leitura.nextLine());
                        System.out.println("Digite o preco da peca:");
                        r.setPreco(leitura.nextDouble());
                        leitura.nextLine();
                        System.out.println("Digite o codigo de barras da peca:");
                        r.setCodigoBarras(leitura.nextLine());
                        cadastro.cadastrarRoupas(r);
                        System.out.println("Dados salvos em arquivo!");
                        menuGerente(opc);
                        break;
                    case "4":
                        System.out.println("Voltando ao menu de gerente...");
                        menuGerente(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuGerente(opc);
                        break;
                }
                break;
            case "2":
                System.out.println("O que você deseja listar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("1 | Funcionário(s)");
                System.out.println("2 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("3 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("4 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        System.out.println("========== FUNCIONARIOS ==========");
                        cadastro.listarFuncionarios();
                        menuGerente(opc);
                        break;
                    case "2":
                        System.out.println("========== CLIENTES ==========");
                        cadastro.listarClientes();
                        menuGerente(opc);
                        break;
                    case "3":
                        System.out.println("========== ROUPAS ==========");
                        cadastro.listarRoupas();
                        menuGerente(opc);
                        break;
                    case "4":
                        System.out.println("Voltando ao menu de gerente...");
                        menuGerente(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuGerente(opc);
                        break;
                }
                break;
            case "3":
                System.out.println("O que você deseja pesquisar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("(A pesquisa deve ser feita pelo CPF)");
                System.out.println("1 | Funcionário(s)");
                System.out.println("2 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("3 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("4 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        System.out.println("Digite o CPF do funcionario que deseja pesquisar:");
                        leitura.nextLine();
                        cadastro.pesquisarFuncionario(leitura.nextLine());
                        menuGerente(opc);
                        break;
                    case "2":
                        System.out.println("Digite o CPF do cliente que deseja pesquisar:");
                        leitura.nextLine();
                        cadastro.pesquisarCliente(leitura.nextLine());
                        menuGerente(opc);
                        break;
                    case "3":
                        System.out.println("Digite o codigo de barras da roupa que deseja pesquisar:");
                        leitura.nextLine();
                        cadastro.pesquisarRoupa(leitura.nextLine());
                        menuGerente(opc);
                        break;
                    case "4":
                        System.out.println("Voltando ao menu de gerente...");
                        menuGerente(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuGerente(opc);
                        break;
                }
                break;
            case "4":
                verImagemCodigoBarras();
                menuGerente(opc);
                break;
            case "0":
                System.out.println("Voltando ao menu de login...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente!");
                menuGerente(opc);
                break;
        }
    }

    public static void menuFuncionario(String opc){
        String opc2 = new String();
        System.out.println("========== MENU FUNCIONARIO ==========");
//opcoes irao aqui
        System.out.println("1 | Cadastrar");
        System.out.println("2 | Listar");
        System.out.println("3 | Pesquisar");
        System.out.println("4 | Ver imagem do código de barras");
        System.out.println("==========================");
        System.out.println("0 | Voltar");
        System.out.println("==========================");
        System.out.println("Digite o número da opção desejada:");
        opc = leitura.next();
        switch (opc) {
            case "1":
                System.out.println("O que você deseja cadastrar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("1 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("2 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("3 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        Cliente c = new Cliente();
                        System.out.println("Digite o nome do cliente:");
                        leitura.nextLine();
                        c.setNome(leitura.nextLine());
                        System.out.println("Digite o CPF do cliente:");
                        c.setCpf(leitura.nextLine());
                        System.out.println("Digite o email do cliente:");
                        c.setEmail(leitura.nextLine());
                        System.out.println("Digite o endereco do cliente:");
                        c.setEndereco(leitura.nextLine());
                        System.out.println("O ID n.º "+idCliCont +" foi dado ao cliente.");
                        c.setIdCliente(String.valueOf(idCliCont));
                        idCliCont+=1;
                        cadastro.cadastrarCliente(c);
                        System.out.println("Dados salvos em arquivo!");
                        menuFuncionario(opc);
                        break;
                    case "2":
                        Roupas r = new Roupas();
                        System.out.println("Digite a marca da peca:");
                        leitura.nextLine();
                        r.setMarca(leitura.nextLine());
                        System.out.println("Digite a cor da peca:");
                        r.setCor(leitura.nextLine());
                        System.out.println("Digite o publico-alvo da peca:");
                        r.setSexo(leitura.nextLine());
                        System.out.println("Digite o tamanho da peca:");
                        r.setTamanho(leitura.nextLine());
                        System.out.println("Digite o preco da peca:");
                        r.setPreco(leitura.nextDouble());
                        leitura.nextLine();
                        System.out.println("Digite o codigo de barras da peca:");
                        r.setCodigoBarras(leitura.nextLine());
                        cadastro.cadastrarRoupas(r);
                        System.out.println("Dados salvos em arquivo!");
                        menuFuncionario(opc);
                        break;
                    case "3":
                        System.out.println("Voltando ao menu de funcionario...");
                        menuFuncionario(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuFuncionario(opc);
                        break;
                }
                break;
            case "2":
                System.out.println("O que você deseja listar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("1 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("2 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("3 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        System.out.println("========== CLIENTES ==========");
                        cadastro.listarClientes();
                        menuFuncionario(opc);
                        break;
                    case "2":
                        System.out.println("========== ROUPAS ==========");
                        cadastro.listarRoupas();
                        menuFuncionario(opc);
                        break;
                    case "3":
                        System.out.println("Voltando ao menu de funcionario...");
                        menuFuncionario(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuFuncionario(opc);
                        break;
                }
                break;
            case "3":
                System.out.println("O que você deseja pesquisar?");
                System.out.println("========== PESSOAS ==========");
                System.out.println("(A pesquisa deve ser feita pelo CPF)");
                System.out.println("1 | Cliente(s)");
                System.out.println("========== PRODUTO ==========");
                System.out.println("2 | Roupa(s)");
                System.out.println("==========================");
                System.out.println("3 | Voltar");
                System.out.println("==========================");
                System.out.println("Digite o número da opção desejada:");
                opc2 = leitura.next();
                switch (opc2) {
                    case "1":
                        System.out.println("Digite o CPF do funcionario que deseja pesquisar:");
                        leitura.nextLine();
                        cadastro.pesquisarFuncionario(leitura.nextLine());
                        menuFuncionario(opc);
                        break;
                    case "2":
                        System.out.println("Digite o codigo de barras da roupa que deseja pesquisar:");
                        leitura.nextLine();
                        cadastro.pesquisarRoupa(leitura.nextLine());
                        menuFuncionario(opc);
                        break;
                    case "3":
                        System.out.println("Voltando ao menu de funcionario...");
                        menuFuncionario(opc);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente!");
                        menuFuncionario(opc);
                        break;
                }
                break;
            case "4":
                verImagemCodigoBarras();
                menuFuncionario(opc);
                break;
            case "0":
                System.out.println("Voltando ao menu de login...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente!");
                menuFuncionario(opc);
        }
    }

    public static void menuCliente(String opc){
        System.out.println("========== MENU CLIENTE ==========");
        System.out.println("1 | Ver roupas disponíveis");
        System.out.println("2 | Pesquisar roupa");
        System.out.println("3 | Adicionar roupa ao carrinho");
        System.out.println("4 | Remover roupa do carrinho");
        System.out.println("5 | Ver carrinho");
        System.out.println("==========================");
        System.out.println("0 | Voltar");
        System.out.println("==========================");
        System.out.println("Digite o número da opção desejada:");
        opc = leitura.next();
        switch (opc){
            case "1":
                System.out.println("========== ROUPAS DISPONÍVEIS ==========");
                cadastro.listarRoupas();
                menuCliente(opc);
                break;
            case "2":
                System.out.println("Digite o código de barras da roupa que deseja pesquisar:");
                leitura.nextLine();
                cadastro.pesquisarRoupa(leitura.nextLine());
                menuCliente(opc);
                break;
            case "3":
                System.out.println("Digite o código de barras da roupa que deseja adicionar ao carrinho:");
                leitura.nextLine();
                String codigoAdicionar = leitura.nextLine();
                Roupas roupaAdicionar = cadastro.buscarRoupa(codigoAdicionar);
                if (roupaAdicionar == null) {
                    System.out.println("Nenhuma roupa encontrada com esse código de barras.");
                } else {
                    carrinho.adicionarItem(roupaAdicionar);
                }
                menuCliente(opc);
                break;
            case "4":
                if (carrinho.isVazio()) {
                    System.out.println("O carrinho está vazio.");
                } else {
                    System.out.println("Digite o código de barras da roupa que deseja remover do carrinho:");
                    leitura.nextLine();
                    carrinho.removerItem(leitura.nextLine());
                }
                menuCliente(opc);
                break;
            case "5":
                System.out.println("========== CARRINHO ==========");
                carrinho.listarItens();
                menuCliente(opc);
                break;
            case "0":
                System.out.println("Voltando ao menu de login...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente!");
                menuCliente(opc);
                break;
        }
    }
}