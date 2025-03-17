package cadastropoo.model.entidades;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Inter
 */
public abstract class Pessoa implements Serializable {

    private String id;
    private String nome;
    private UUID UUID;

    public Pessoa(String nome) {
        this.id = UUID.randomUUID().toString();
        this.setNome(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public abstract void exibir();
}
