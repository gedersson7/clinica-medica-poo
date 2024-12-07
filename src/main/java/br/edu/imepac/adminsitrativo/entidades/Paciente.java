package br.edu.imepac.adminsitrativo.entidades;

import java.time.LocalDate;

public class Paciente {
    private int id_paciente;
    private String cpf;
    private String nome;
    private String email ;
    private String telefone;
    private LocalDate dataNascimento; // Data de nascimento
    private String sexo;


    public Paciente(int id, String cpf, String nome, String email, String sexo, LocalDate dataNascimento, String telefone) {
        this.id_paciente = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String crm) {
        this.cpf = crm;
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
}
