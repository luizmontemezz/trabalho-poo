
public abstract class Pessoa{

    protected String nome;
    protected String cpf;
    protected String endereco;

    public Pessoa(String nome, String cpf, String endereco){
        this.nome=nome;
        this.cpf=cpf;
        this.endereco=endereco;
    }

    public Pessoa(){
    }

    @Override
    public String toString() {
        return "Nome: "+nome +"| CPF: "+cpf +"| Endereço: " +endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}