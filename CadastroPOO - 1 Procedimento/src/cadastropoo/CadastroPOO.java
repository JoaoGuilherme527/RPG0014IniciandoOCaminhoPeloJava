package cadastropoo;

import cadastropoo.model.entidades.PessoaJuridica;
import cadastropoo.model.entidades.PessoaFisica;
import cadastropoo.model.gerenciadores.PessoaFisicaRepo;
import cadastropoo.model.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Inter
 */
public class CadastroPOO {

    public static void main(String[] args) {
        String arquivoPF = "pessoas_fisicas.dat";
        String arquivoPJ = "pessoas_juridicas.dat";

        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        repo1.inserir(new PessoaFisica("Joao", "017.880.720-65", 22));
        repo1.inserir(new PessoaFisica("Guilherme", "017.880.720-66", 25));

        try {
            repo1.persistir(arquivoPF);
            System.out.println("Dados de Pessoa Fisica Armazenados.");
        } catch (IOException e) {
            System.out.println("Error ao armazenar dados de Pessoa Fisica: " + e.getMessage());
        }

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar(arquivoPF);
            System.out.println("Dados de Pessoa Fisica Recuperados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados de Pessoa Fisica: " + e.getMessage());
        }

        repo2.obterTodos().forEach(PessoaFisica::exibir);

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        repo3.inserir(new PessoaJuridica("Empresa XPC", "00.000.00/0001-01"));
        repo3.inserir(new PessoaJuridica("Empresa XPTO", "00.000.00/0001-00"));

        try {
            repo3.persistir(arquivoPJ);
            System.out.println("Dados de Pessoa Juridica Armazenados.");
        } catch (IOException e) {
            System.out.println("Error ao armazenar dados de Pessoa Juridica: " + e.getMessage());
        }

        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar(arquivoPJ);
            System.out.println("Dados de Pessoa Juridica Recuperados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error ao recuperar dados de Pessoa Juridica: " + e.getMessage());
        }

        repo4.obterTodos().forEach(PessoaJuridica::exibir);

    }

}
