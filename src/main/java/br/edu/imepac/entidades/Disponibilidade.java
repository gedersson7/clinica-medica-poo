package br.edu.imepac.entidades;

public class Disponibilidade {
        private Long id;
        private Medico medico;
        private String data;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public Medico getMedico() {
            return medico;
        }

        public void setMedico(Medico medico) {
            this.medico = medico;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }


    }
