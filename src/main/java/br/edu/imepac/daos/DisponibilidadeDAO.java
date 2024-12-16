package br.edu.imepac.daos;

import br.edu.imepac.entidades.Disponibilidade;
import br.edu.imepac.entidades.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisponibilidadeDAO {

        private static final Logger logger = Logger.getLogger(DisponibilidadeDAO.class.getName());
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
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao salvar disponibilidade", e);
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
                        return disponibilidade;
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao buscar disponibilidade por ID", e);
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
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao listar disponibilidades", e);
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
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao atualizar disponibilidade", e);
            }
        }

        public void deletar(Long id) {
            String sql = "DELETE FROM disponibilidade WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao deletar disponibilidade", e);
            }
        }
    }
