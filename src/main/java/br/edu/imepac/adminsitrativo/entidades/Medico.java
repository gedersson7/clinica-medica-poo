package br.edu.imepac.adminsitrativo.entidades;

public class Medico {
    private int id;
    private String crm;
    private String nome;
    private String email;
    private String especialidade;

    public Medico(int id, String crm, String nome, String email) {
        this.id = id;
        this.crm = crm;
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}