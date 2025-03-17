package cadastropoo.model.gerenciadores;

import cadastropoo.model.entidades.PessoaJuridica;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inter
 */
public class PessoaJuridicaRepo {

    private List<PessoaJuridica> pessoas = new ArrayList<PessoaJuridica>();

    public void inserir(PessoaJuridica entidade) {
        pessoas.add(entidade);
    }

    public void alterar(PessoaJuridica entidade) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId().equals(entidade.getId())) {
                pessoas.set(i, entidade);
                return;
            }
        }
    }

    public void exlcuir(String id) {
        pessoas.removeIf(pessoa -> pessoa.getId().equals(id));
    }

    public PessoaJuridica obter(String id) {
        return pessoas.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoas);
    }

    public void persistir(String arqNome) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arqNome))) {
            out.writeObject(pessoas);
        }
    }

    public void recuperar(String arqNome) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arqNome))) {
            pessoas = (List<PessoaJuridica>) in.readObject();
        }
    }
}
