package cadastropoo.model.entidades;

import java.io.Serializable;

/**
 *
 * @author Inter
 */
public class PessoaJuridica extends Pessoa implements Serializable {

    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        setCnpj(cnpj);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public void exibir() {
        System.out.println("Id: " + getId() + "\n" + "Nome: " + getNome() + "\n" + "CNPJ: " + getCnpj());
    }
}
