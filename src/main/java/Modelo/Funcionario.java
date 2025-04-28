package Modelo;

import java.sql.Date;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String cargo;
    private String email;
    private Date admissao;
    private String cpf;

    public Funcionario(String nome, String cargo, String email, Date admissao) {
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.admissao = admissao;
    }
    // Construtor com o tipo Date para Data_Admissao
    public Funcionario(int idFuncionario, String nome, String cargo, String email, Date admissao) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.admissao = admissao;
    }

    // Getters e Setters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
