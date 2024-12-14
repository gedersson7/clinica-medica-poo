package br.edu.imepac.entidades;

public class Medico {
    private int id_medico;
    private String nome_medico;
    private String crm;
    private String email;
    private String telefone;
    private String especializacao;

    // Getters e Setters
    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNome_medico() {
        return nome_medico;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id_medico=" + id_medico +
                ", nome_medico='" + nome_medico + '\'' +
                ", crm='" + crm + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", especializacao='" + especializacao + '\'' +
                '}';
    }


}