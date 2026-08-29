package model;

public class Cliente extends Pessoa {
    private String email;
    private String idCliente;

    public Cliente (String nome, String cpf, String endereco, String idCliente, String email){
        super(nome, cpf, endereco);
        this.idCliente=idCliente;
        this.email=email;
    }

    public Cliente(){
    }

    @Override
    public String toString(){
        return super.toString() + " | idCliente: " +idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
