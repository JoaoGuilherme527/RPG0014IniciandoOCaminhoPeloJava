package cadastropoo;

import cadastropoo.model.entidades.PessoaJuridica;
import cadastropoo.model.entidades.PessoaFisica;
import cadastropoo.model.gerenciadores.PessoaFisicaRepo;
import cadastropoo.model.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.Scanner;

public class CadastroPOO {

    private final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private final Scanner input = new Scanner(System.in);

    private String escolhaPessoa() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        return input.nextLine();
    }

    private void scanInput() {
        while (true) {
            System.out.println("\n===========================");
            System.out.println(" 1 - Incluir Pessoa");
            System.out.println(" 2 - Alterar Pessoa");
            System.out.println(" 3 - Excluir Pessoa");
            System.out.println(" 4 - Buscar pelo Id");
            System.out.println(" 5 - Exibir Todos");
            System.out.println(" 6 - Persistir Dados");
            System.out.println(" 7 - Recuperar Dados");
            System.out.println(" 0 - Finalizar Programa");
            System.out.println("===========================");

            switch (input.nextLine()) {
                case "1" -> incluirPessoa();
                case "2" -> alterarPessoa();
                case "3" -> excluirPessoa();
                case "4" -> buscarPessoaPorId();
                case "5" -> exibirTodos();
                case "6" -> persistirDados();
                case "7" -> recuperarDados();
                case "0" -> {
                    System.out.println("Finalizando o programa...");
                    input.close();
                    return;
                }
                default -> System.out.println("Opcao invalida!");
            }
        }
    }

    private void incluirPessoa() {
        String tipo = escolhaPessoa();
        System.out.println("Insira os Dados...");

        System.out.println("Nome: ");
        String nome = input.nextLine();

        if (tipo.equalsIgnoreCase("j")) {
            System.out.println("CNPJ: ");
            repoJuridica.inserir(new PessoaJuridica(nome, input.nextLine()));
            System.out.println("Pessoa juridica adicionada!");
        } else if (tipo.equalsIgnoreCase("f")) {
            System.out.println("CPF: ");
            String cpf = input.nextLine();
            System.out.println("Idade: ");
            int idade = Integer.parseInt(input.nextLine());
            repoFisica.inserir(new PessoaFisica(nome, cpf, idade));
            System.out.println("Pessoa fisica adicionada!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private void alterarPessoa() {
        String tipo = escolhaPessoa();
        System.out.println("Digite o ID da pessoa:");
        String id = input.nextLine();

        if (tipo.equalsIgnoreCase("j")) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa != null) alterarJuridica(pessoa);
            else System.out.println("Pessoa juridica nao encontrada!");
        } else if (tipo.equalsIgnoreCase("f")) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa != null) alterarFisica(pessoa);
            else System.out.println("Pessoa fisica nao encontrada!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private void alterarJuridica(PessoaJuridica pessoa) {
        System.out.println("Novo Nome:");
        String nome = input.nextLine();
        if (!nome.isBlank()) pessoa.setNome(nome);

        System.out.println("Novo CNPJ:");
        String cnpj = input.nextLine();
        if (!cnpj.isBlank()) pessoa.setCnpj(cnpj);

        repoJuridica.alterar(pessoa);
        System.out.println("Pessoa juridica alterada!");
    }

    private void alterarFisica(PessoaFisica pessoa) {
        System.out.println("Novo Nome:");
        String nome = input.nextLine();
        if (!nome.isBlank()) pessoa.setNome(nome);

        System.out.println("Novo CPF:");
        String cpf = input.nextLine();
        if (!cpf.isBlank()) pessoa.setCpf(cpf);

        System.out.println("Nova Idade:");
        String idade = input.nextLine();
        if (!idade.isBlank()) pessoa.setIdade(Integer.parseInt(idade));

        repoFisica.alterar(pessoa);
        System.out.println("Pessoa fisica alterada!");
    }

    private void excluirPessoa() {
        String tipo = escolhaPessoa();
        System.out.println("Digite o ID da pessoa:");
        String id = input.nextLine();

        if (tipo.equalsIgnoreCase("j")) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa juridica excluida!");
        } else if (tipo.equalsIgnoreCase("f")) {
            repoFisica.excluir(id);
            System.out.println("Pessoa fisica excluida!");
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private void buscarPessoaPorId() {
        String tipo = escolhaPessoa();
        System.out.println("Digite o ID da pessoa:");
        String id = input.nextLine();

        if (tipo.equalsIgnoreCase("j")) repoJuridica.obter(id).exibir();
        else if (tipo.equalsIgnoreCase("f")) repoFisica.obter(id).exibir();
        else System.out.println("Tipo invalido!");
    }

    private void exibirTodos() {
        String escolha = escolhaPessoa();
        if (escolha.equalsIgnoreCase("j")) repoJuridica.obterTodos().forEach(PessoaJuridica::exibir);
        else if (escolha.equalsIgnoreCase("f")) repoFisica.obterTodos().forEach(PessoaFisica::exibir);
        else System.out.println("Opcao invalida!");
    }

    private void persistirDados() {
        System.out.println("Prefixo do arquivo:");
        String prefixo = input.nextLine();
        try {
            repoJuridica.persistir(prefixo);
            repoFisica.persistir(prefixo);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void recuperarDados() {
        System.out.println("Prefixo do arquivo:");
        String prefixo = input.nextLine();
        try {
            repoJuridica.recuperar(prefixo);
            repoFisica.recuperar(prefixo);
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CadastroPOO().scanInput();
    }
}
