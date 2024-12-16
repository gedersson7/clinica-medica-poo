package br.edu.imepac.entidades;

public class Usuario {
        private Long id_usuario;
        private String nome;
        private Long cpf;
        private String email;
        private String senha;
        private String dataNascimento;
        private Long telefone;
        private boolean paciente;

        public void setId_usuario(Long id_usuario) {
            this.id_usuario = id_usuario;
        }

        public Long getId_usuario() {
            return id_usuario;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getCpf() {
            return cpf;
        }

        public void setCpf(Long cpf) {
            this.cpf = cpf;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public Long getTelefone() {
            return telefone;
        }

        public void setTelefone(Long telefone) {
            this.telefone = telefone;
        }



        public boolean isPaciente() {
            return paciente;
        }

        public void setPaciente(boolean paciente) {
            this.paciente = paciente;
        }
}
