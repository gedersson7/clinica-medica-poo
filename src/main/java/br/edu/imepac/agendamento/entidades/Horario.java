package br.edu.imepac.agendamento.entidades;

import java.util.HashMap;
import java.util.Map;

public class Horario {
        private Long id;
        private String horarioSelecionado;
        private final Map<String, Boolean> disponibilidade;

        public Horario() {
            this.disponibilidade = new HashMap<>();
            inicializarDisponibilidade();
        }

        private void inicializarDisponibilidade() {
            disponibilidade.put("06:00 - 06:30", false);
            disponibilidade.put("06:30 - 07:00", false);
            disponibilidade.put("07:00 - 07:30", false);
            disponibilidade.put("07:30 - 08:00", false);
            disponibilidade.put("08:00 - 08:30", false);
            disponibilidade.put("08:30 - 09:00", false);
            disponibilidade.put("09:00 - 09:30", false);
            disponibilidade.put("09:30 - 10:00", false);
            disponibilidade.put("10:00 - 10:30", false);
            disponibilidade.put("10:30 - 11:00", false);
            disponibilidade.put("11:00 - 11:30", false);
            disponibilidade.put("11:30 - 12:00", false);
            disponibilidade.put("12:00 - 12:30", false);
            disponibilidade.put("12:30 - 13:00", false);
            disponibilidade.put("13:00 - 13:30", false);
            disponibilidade.put("13:30 - 14:00", false);
            disponibilidade.put("14:00 - 14:30", false);
            disponibilidade.put("14:30 - 15:00", false);
            disponibilidade.put("15:00 - 15:30", false);
            disponibilidade.put("15:30 - 16:00", false);
            disponibilidade.put("16:00 - 16:30", false);
            disponibilidade.put("16:30 - 17:00", false);
            disponibilidade.put("17:00 - 17:30", false);
            disponibilidade.put("17:30 - 18:00", false);
            disponibilidade.put("18:00 - 18:30", false);
            disponibilidade.put("18:30 - 19:00", false);
        }

        public Map<String, Boolean> getDisponibilidade() {
            return disponibilidade;
        }

        public void setDisponibilidade(String horario, boolean disponivel) {
            if (disponibilidade.containsKey(horario)) {
                disponibilidade.put(horario, disponivel);
            }
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getHorarioSelecionado() {
            return horarioSelecionado;
        }

        public void setHorarioSelecionado(String horarioSelecionado) {
            this.horarioSelecionado = horarioSelecionado;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Horario{");

            for (Map.Entry<String, Boolean> entry : disponibilidade.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue() ? "Disponível" : "Ocupado").append(", ");
            }

            sb.setLength(sb.length() - 2);
            sb.append("}");

            return sb.toString();
        }
    }

