
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Cadastro {

    private static final String DELIMITADOR = ";";

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Funcionaro> funcionarios = new ArrayList<>();
    private ArrayList<Roupas> roupas = new ArrayList<>();

    public Cadastro() {
        clientes.add(new Cliente());
        funcionarios.add(new Funcionaro());
        roupas.add(new Roupas());
    }

    public boolean cadastrarCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getCpf() != null && c.getCpf().equals(cliente.getCpf())) {
                return false;
            }
        }
        if (clientes.get(0).getCpf() == null) {
            clientes.set(0, cliente);
        } else {
            clientes.add(cliente);
        }
        addCliente(cliente);
        return true;
    }

    public boolean cadastrarFuncionario(Funcionaro funcionario) {
        for (Funcionaro f : funcionarios) {
            if (f.getCpf() != null && f.getCpf().equals(funcionario.getCpf())) {
                return false;
            }
        }
        if (funcionarios.get(0).getCpf() == null) {
            funcionarios.set(0, funcionario);
        } else {
            funcionarios.add(funcionario);
        }
        addFuncionario(funcionario);
        return true;
    }

    public boolean cadastrarRoupas(Roupas roupa) {
        for (Roupas r : roupas) {
            if (r.getCodigoBarras() != null && r.getCodigoBarras().equals(roupa.getCodigoBarras())) {
                return false;
            }
        }
        if (roupas.get(0).getCodigoBarras() == null) {
            roupas.set(0, roupa);
        } else {
            roupas.add(roupa);
        }
        addRoupa(roupa);
        return true;
    }

    public void pesquisarCliente(String cpf) {
        if (clientes.get(0).getCpf() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                System.out.println(c);
                return;
            }
        }
        System.out.println("Nenhum cliente cadastrado neste CPF.");
    }

    public void pesquisarFuncionario(String cpf) {
        if (funcionarios.get(0).getCpf() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Funcionaro f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                System.out.println(f);
                return;
            }
        }
        System.out.println("Nenhum funcionario cadastrado neste CPF.");
    }

    public Roupas buscarRoupa(String codigoBarras) {
        if (roupas.get(0).getCodigoBarras() == null) {
            return null;
        }
        for (Roupas r : roupas) {
            if (r.getCodigoBarras().equals(codigoBarras)) {
                return r;
            }
        }
        return null;
    }

    public void pesquisarRoupa(String codigoBarras) {
        if (roupas.get(0).getCodigoBarras() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Roupas r : roupas) {
            if (r.getCodigoBarras().equals(codigoBarras)) {
                System.out.println(r);
                return;
            }
        }
        System.out.println("Nenhuma roupa encontrada.");
    }

    public void listarClientes() {
        if (clientes.get(0).getCpf() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.get(0).getCpf() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Funcionaro f : funcionarios) {
            System.out.println(f);
        }
    }

    public void listarRoupas() {
        if (roupas.get(0).getCodigoBarras() == null) {
            System.out.println("Não existem cadastros.");
            return;
        }
        for (Roupas r : roupas) {
            System.out.println(r);
        }
    }

    public void carregarTudo() {
        carregarClientes();
        carregarFuncionarios();
        carregarRoupas();
    }

    private void addCliente(Cliente c) {
        String linha = String.join(DELIMITADOR,
                c.getNome(), c.getCpf(), c.getEndereco(),
                c.getIdCliente(), c.getEmail()) + System.lineSeparator();
        Path arquivo = Paths.get("clientes.txt");
        try {
            Files.writeString(arquivo, linha, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    private void carregarClientes() {
        Path arquivo = Paths.get("clientes.txt");
        if (!Files.exists(arquivo)) return;

        try {
            for (String linha : Files.readAllLines(arquivo)) {
                if (linha.isBlank()) continue;
                String[] d = linha.split(DELIMITADOR, -1);
                Cliente c = new Cliente(d[0], d[1], d[2], d[3], d[4]);
                if (clientes.get(0).getCpf() == null) {
                    clientes.set(0, c);
                } else {
                    clientes.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private void addFuncionario(Funcionaro f) {
        String linha = String.join(DELIMITADOR,
                f.getNome(), f.getCpf(), f.getEndereco(),
                f.getIdFunc(), String.valueOf(f.getSalario()), f.getCargo()) + System.lineSeparator();
        Path arquivo = Paths.get("funcionarios.txt");
        try {
            Files.writeString(arquivo, linha, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionario: " + e.getMessage());
        }
    }

    private void carregarFuncionarios() {
        Path arquivo = Paths.get("funcionarios.txt");
        if (!Files.exists(arquivo)) return;

        try {
            for (String linha : Files.readAllLines(arquivo)) {
                if (linha.isBlank()) continue;
                String[] d = linha.split(DELIMITADOR, -1);
                Funcionaro f = new Funcionaro(d[0], d[1], d[2], d[3],
                        Double.parseDouble(d[4]), d[5]);
                if (funcionarios.get(0).getCpf() == null) {
                    funcionarios.set(0, f);
                } else {
                    funcionarios.add(f);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionarios: " + e.getMessage());
        }
    }

    private void addRoupa(Roupas r) {
        String linha = String.join(DELIMITADOR,
                r.getTamanho(), r.getCor(), r.getMarca(), r.getSexo(),
                String.valueOf(r.getPreco()), r.getCodigoBarras()) + System.lineSeparator();
        Path arquivo = Paths.get("roupas.txt");
        try {
            Files.writeString(arquivo, linha, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao salvar roupa: " + e.getMessage());
        }
    }

    private void carregarRoupas() {
        Path arquivo = Paths.get("roupas.txt");
        if (!Files.exists(arquivo)) return;

        try {
            for (String linha : Files.readAllLines(arquivo)) {
                if (linha.isBlank()) continue;
                String[] d = linha.split(DELIMITADOR, -1);
                Roupas r = new Roupas(d[0], d[1], d[2], d[3],
                        Double.parseDouble(d[4]), d[5]);
                if (roupas.get(0).getCodigoBarras() == null) {
                    roupas.set(0, r);
                } else {
                    roupas.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar roupas: " + e.getMessage());
        }
    }
}