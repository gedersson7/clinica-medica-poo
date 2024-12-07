package br.edu.imepac.atendimento.entidades;

import br.edu.imepac.adminsitrativo.entidades.Paciente;

import java.util.Date;

    public class Atendimento {
        private int id;
        private Consulta consulta;
        private Paciente paciente;
        private Medico medico;
        private Date dataAtendimento;
        private String observacoes;

        // Construtor
        public Atendimento(int id, Consulta consulta, Paciente paciente, Medico medico, Date dataAtendimento, String observacoes) {
            this.id = id;
            this.consulta = consulta;
            this.paciente = paciente;
            this.medico = medico;
            this.dataAtendimento = dataAtendimento;
            this.observacoes = observacoes;
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Consulta getConsulta() {
            return consulta;
        }

        public void setConsulta(Consulta consulta) {
            this.consulta = consulta;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public Medico getMedico() {
            return medico;
        }

        public void setMedico(Medico medico) {
            this.medico = medico;
        }

        public Date getDataAtendimento() {
            return dataAtendimento;
        }

        public void setDataAtendimento(Date dataAtendimento) {
            this.dataAtendimento = dataAtendimento;
        }

        public String getObservacoes() {
            return observacoes;
        }

        public void setObservacoes(String observacoes) {
            this.observacoes = observacoes;
        }
    }
