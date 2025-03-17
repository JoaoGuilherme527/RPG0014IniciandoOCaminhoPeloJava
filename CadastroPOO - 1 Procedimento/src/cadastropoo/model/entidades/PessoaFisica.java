package cadastropoo.model.entidades;

import java.io.Serializable;

/**
 *
 * @author Inter
 */
public class PessoaFisica extends Pessoa implements Serializable {

    private Integer idade;
    private String cpf;
    public PessoaFisica(String nome, String cpf, Integer idade) {
        super(nome);
        setCpf(cpf);
        setIdade(idade);
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override public void exibir() {
        System.out.println("Id: " + getId() + "\n" + "Nome: " + getNome() + "\n" + "Idade: " + getIdade() + "\n" + "CPF: " + getCpf());
    }

}
