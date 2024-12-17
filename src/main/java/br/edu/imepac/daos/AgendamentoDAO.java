package br.edu.imepac.daos;

import br.edu.imepac.entidades.Agendamento;
import br.edu.imepac.entidades.Disponibilidade;
import br.edu.imepac.entidades.Paciente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private Connection connection;

    public AgendamentoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para salvar um novo agendamento
    public void salvar(Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (id_paciente, id_disponibilidade, prontuario, finalizado, cancelado, confirmado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, agendamento.getPaciente().getId_paciente());
            statement.setLong(2, agendamento.getDisponibilidade().getId());
            statement.setString(3, agendamento.getProntuario());
            statement.setBoolean(4, agendamento.isFinalizado());
            statement.setBoolean(5, agendamento.isCancelado());
            statement.setBoolean(6, agendamento.isConfirmado());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        agendamento.setId(generatedKeys.getLong(1));
                    }
                }
                JOptionPane.showMessageDialog(null, "Agendamento salvo com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar agendamento: " + e.getMessage());
        }
    }

    // Método para buscar um agendamento por ID
    public Agendamento buscarPorId(Long id) {
        String sql = "SELECT a.id, a.prontuario, a.finalizado, a.cancelado, a.confirmado, " +
                "p.id AS id_paciente, p.nome AS nome_paciente, " +
                "d.id AS id_disponibilidade, d.data AS data_disponibilidade, " +
                "m.id AS id_medico, m.nome AS nome_medico " +
                "FROM agendamento a " +
                "INNER JOIN paciente p ON a.id_paciente = p.id " +
                "INNER JOIN disponibilidade d ON a.id_disponibilidade = d.id " +
                "INNER JOIN medico m ON d.id_medico = m.id " +
                "WHERE a.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(resultSet.getLong("id"));
                    agendamento.setProntuario(resultSet.getString("prontuario"));
                    agendamento.setFinalizado(resultSet.getBoolean("finalizado"));
                    agendamento.setCancelado(resultSet.getBoolean("cancelado"));
                    agendamento.setConfirmado(resultSet.getBoolean("confirmado"));

                    Paciente paciente = new Paciente();
                    paciente.setId_paciente(resultSet.getInt("id_paciente"));
                    paciente.setNome(resultSet.getString("nome_paciente"));
                    agendamento.setPaciente(paciente);

                    Disponibilidade disponibilidade = new Disponibilidade();
                    disponibilidade.setId(resultSet.getLong("id_disponibilidade"));
                    disponibilidade.setData(resultSet.getString("data_disponibilidade"));
                    disponibilidade.setMedico(null); // Carregar médico completo se necessário
                    agendamento.setDisponibilidade(disponibilidade);

                    return agendamento;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar agendamento por ID: " + e.getMessage());
        }
        return null;
    }

    // Método para listar todos os agendamentos
    public List<Agendamento> listarTodos() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.id, a.prontuario, a.finalizado, a.cancelado, a.confirmado, " +
                "p.id AS id_paciente, p.nome AS nome_paciente, " +
                "d.id AS id_disponibilidade, d.data AS data_disponibilidade, " +
                "m.id AS id_medico, m.nome AS nome_medico " +
                "FROM agendamento a " +
                "INNER JOIN paciente p ON a.id_paciente = p.id " +
                "INNER JOIN disponibilidade d ON a.id_disponibilidade = d.id " +
                "INNER JOIN medico m ON d.id_medico = m.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(resultSet.getLong("id"));
                agendamento.setProntuario(resultSet.getString("prontuario"));
                agendamento.setFinalizado(resultSet.getBoolean("finalizado"));
                agendamento.setCancelado(resultSet.getBoolean("cancelado"));
                agendamento.setConfirmado(resultSet.getBoolean("confirmado"));

                Paciente paciente = new Paciente();
                paciente.setId_paciente(resultSet.getInt("id_paciente"));
                paciente.setNome(resultSet.getString("nome_paciente"));
                agendamento.setPaciente(paciente);

                Disponibilidade disponibilidade = new Disponibilidade();
                disponibilidade.setId(resultSet.getLong("id_disponibilidade"));
                disponibilidade.setData(resultSet.getString("data_disponibilidade"));
                disponibilidade.setMedico(null); // Carregar médico completo se necessário
                agendamento.setDisponibilidade(disponibilidade);

                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }

    // Método para atualizar um agendamento
    public void atualizar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET id_paciente = ?, id_disponibilidade = ?, prontuario = ?, " +
                "finalizado = ?, cancelado = ?, confirmado = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, agendamento.getPaciente().getId_paciente());
            statement.setLong(2, agendamento.getDisponibilidade().getId());
            statement.setString(3, agendamento.getProntuario());
            statement.setBoolean(4, agendamento.isFinalizado());
            statement.setBoolean(5, agendamento.isCancelado());
            statement.setBoolean(6, agendamento.isConfirmado());
            statement.setLong(7, agendamento.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar agendamento: " + e.getMessage());
        }
    }

    // Método para deletar um agendamento
    public void deletar(Long id) {
        String sql = "DELETE FROM agendamento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Agendamento deletado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar agendamento: " + e.getMessage());
        }
    }
}
