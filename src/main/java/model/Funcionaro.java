package model;

public class Funcionaro extends Pessoa {
    private String cargo;
    private String idFunc;
    private double salario;

    public Funcionaro (String nome, String cpf, String endereco, String idFunc, double salario, String cargo){
        super(nome, cpf, endereco);
        this.cargo=cargo;
        this.idFunc=idFunc;
        this.salario=salario;
    }

    public Funcionaro(){
    }

    @Override
    public String toString() {
        return super.toString() + " | idFuncionario: " +idFunc + " | Salário: " +salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(String idFunc) {
        this.idFunc = idFunc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
