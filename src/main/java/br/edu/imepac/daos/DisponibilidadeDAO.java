package br.edu.imepac.daos;

import br.edu.imepac.entidades.Disponibilidade;
import br.edu.imepac.entidades.Medico;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisponibilidadeDAO {

    private Connection connection;

    public DisponibilidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Disponibilidade disponibilidade) {
        String sql = "INSERT INTO disponibilidade (id_medico, data) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, disponibilidade.getMedico().getId_medico());
            statement.setString(2, disponibilidade.getData());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        disponibilidade.setId(generatedKeys.getLong(1));
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Disponibilidade salva com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar disponibilidade: " + e.getMessage());
        }
    }

    public Disponibilidade buscarPorId(Long id) {
        String sql = "SELECT d.id, d.data, m.id AS id_medico, m.nome " +
                "FROM disponibilidade d " +
                "INNER JOIN medico m ON d.id_medico = m.id " +
                "WHERE d.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Disponibilidade disponibilidade = new Disponibilidade();
                    disponibilidade.setId(resultSet.getLong("id"));
                    disponibilidade.setData(resultSet.getString("data"));

                    Medico medico = new Medico();
                    medico.setId_medico(resultSet.getInt("id_medico"));
                    medico.setNome_medico(resultSet.getString("nome"));

                    disponibilidade.setMedico(medico);
                    JOptionPane.showMessageDialog(null, "Disponibilidade encontrada com sucesso!");
                    return disponibilidade;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar disponibilidade por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Disponibilidade> listarTodas() {
        List<Disponibilidade> disponibilidades = new ArrayList<>();
        String sql = "SELECT d.id, d.data, m.id AS id_medico, m.nome " +
                "FROM disponibilidade d " +
                "INNER JOIN medico m ON d.id_medico = m.id";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Disponibilidade disponibilidade = new Disponibilidade();
                disponibilidade.setId(resultSet.getLong("id"));
                disponibilidade.setData(resultSet.getString("data"));

                Medico medico = new Medico();
                medico.setId_medico(resultSet.getInt("id_medico"));
                medico.setNome_medico(resultSet.getString("nome"));

                disponibilidade.setMedico(medico);
                disponibilidades.add(disponibilidade);
            }
            JOptionPane.showMessageDialog(null, "Disponibilidades listadas com sucesso. Total: " + disponibilidades.size());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar disponibilidades: " + e.getMessage());
        }
        return disponibilidades;
    }

    public void atualizar(Disponibilidade disponibilidade) {
        String sql = "UPDATE disponibilidade SET id_medico = ?, data = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, disponibilidade.getMedico().getId_medico());
            statement.setString(2, disponibilidade.getData());
            statement.setLong(3, disponibilidade.getId());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disponibilidade atualizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar disponibilidade: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM disponibilidade WHERE id = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar esta disponibilidade?", "Deletar Disponibilidade", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Disponibilidade deletada com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar disponibilidade: " + e.getMessage());
            }
        }
    }
}
