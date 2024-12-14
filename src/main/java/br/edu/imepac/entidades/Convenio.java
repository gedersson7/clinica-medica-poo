package br.edu.imepac.entidades;

public class Convenio {
    private int id;
    private String nome;
    private String codigo;
    private String validade;
    private double cobertura;

    // Construtores
    public Convenio() {
    }

    public Convenio(int id, String nome, String codigo, String validade, double cobertura) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.validade = validade;
        this.cobertura = cobertura;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getCobertura() {
        return cobertura;
    }

    public void setCobertura(double cobertura) {
        this.cobertura = cobertura;
    }

}