package br.edu.imepac.entidades;


import java.time.LocalDate;

public class Paciente {
    private int id_paciente;
    private String nome;
    private String cpf;
    private String email;
    private String sexo;
    private String telefone;
    private LocalDate datanasc;

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_pacinte) {
        this.id_paciente = id_pacinte;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(LocalDate datanasc) {
        this.datanasc = datanasc;
    }

    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }



}
