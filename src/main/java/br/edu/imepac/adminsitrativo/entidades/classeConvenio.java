public class ConvenioMedico {
    // Atributos
    private String nomeConvenio;
    private String tipoPlano;
    private String cobertura;
    private String validade;
    private String nomePaciente;
    private String numeroCartao;
    private String telefoneContato;
    private double valorConvenio;

    // Construtor
    public ConvenioMedico(String nomeConvenio, String tipoPlano, String cobertura, String validade, String nomePaciente, String numeroCartao, String telefoneContato, double valorConvenio) {
        this.nomeConvenio = nomeConvenio;
        this.tipoPlano = tipoPlano;
        this.cobertura = cobertura;
        this.validade = validade;
        this.nomePaciente = nomePaciente;
        this.numeroCartao = numeroCartao;
        this.telefoneContato = telefoneContato;
        this.valorConvenio = valorConvenio;
    }

    // Getters e Setters
    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public double getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(double valorConvenio) {
        this.valorConvenio = valorConvenio;
    }

    // Método para verificar se o convênio está válido
    public boolean isConvenioValido(String dataAtual) {
        return validade.compareTo(dataAtual) >= 0;
    }

    // Método para imprimir informações do convênio
    public void imprimirDetalhes() {
        System.out.println("Convênio: " + nomeConvenio);
        System.out.println("Tipo de Plano: " + tipoPlano);
        System.out.println("Cobertura: " + cobertura);
        System.out.println("Validade: " + validade);
        System.out.println("Nome do Paciente: " + nomePaciente);
        System.out.println("Número do Cartão: " + numeroCartao);
        System.out.println("Telefone de Contato: " + telefoneContato);
        System.out.println("Valor do Convênio: " + valorConvenio);
    }
}
