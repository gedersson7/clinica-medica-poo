package br.edu.imepac.atendimento.entidades;

import br.edu.imepac.adminsitrativo.entidades.Paciente;

import java.util.Date;

    public class Consulta {
        private int id;
        private Medico medico;
        private Paciente paciente;
        private Date dataConsulta;
        private String status;
        private String diagnostico;

        // Construtor
        public Consulta(int id, Medico medico, Paciente paciente, Date dataConsulta, String status, String diagnostico) {
            this.id = id;
            this.medico = medico;
            this.paciente = paciente;
            this.dataConsulta = dataConsulta;
            this.status = status;
            this.diagnostico = diagnostico;
        }

        // Getters e Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Medico getMedico() {
            return medico;
        }

        public void setMedico(Medico medico) {
            this.medico = medico;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public Date getDataConsulta() {
            return dataConsulta;
        }

        public void setDataConsulta(Date dataConsulta) {
            this.dataConsulta = dataConsulta;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDiagnostico() {
            return diagnostico;
        }

        public void setDiagnostico(String diagnostico) {
            this.diagnostico = diagnostico;
        }
    }
