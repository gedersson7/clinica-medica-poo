package br.edu.imepac.daos;


import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.utilitario.ConectarBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO {

        private static final Logger LOGGER = Logger.getLogger(FuncionarioDAO.class.getName());

        // Método para cadastrar um funcionário
        public void cadastrar(Funcionario funcionario) {
            String sql = "INSERT INTO funcionario (nome, cpf, email, telefone, dataadmissao, senha) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCpf());
                stmt.setString(3, funcionario.getEmail());
                stmt.setString(4, funcionario.getTelefone());
                stmt.setString(5, funcionario.getDataadmissao());
                stmt.setString(6, funcionario.getSenha());

                stmt.executeUpdate();
                LOGGER.log(Level.INFO, "Funcionário cadastrado com sucesso: {0}", funcionario);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Erro ao cadastrar funcionário: {0}", ex.getMessage());
            }
        }

        // Método para atualizar as informações de um funcionário
        public void atualizar(Funcionario funcionario) {
            String sql = "UPDATE funcionario SET nome = ?, cpf = ?, email = ?, telefone = ?, dataadmissao = ?, senha = ? WHERE id_funcionario = ?";
            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCpf());
                stmt.setString(3, funcionario.getEmail());
                stmt.setString(4, funcionario.getTelefone());
                stmt.setString(5, funcionario.getDataadmissao());
                stmt.setString(6, funcionario.getSenha());
                stmt.setInt(7, funcionario.getId_funcionario());

                stmt.executeUpdate();
                LOGGER.log(Level.INFO, "Funcionário atualizado com sucesso: {0}", funcionario);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Erro ao atualizar funcionário: {0}", ex.getMessage());
            }
        }

        // Método para excluir um funcionário
        public void excluir(int idFuncionario) {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, idFuncionario);
                stmt.executeUpdate();
                LOGGER.log(Level.INFO, "Funcionário excluído com sucesso. ID: {0}", idFuncionario);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Erro ao excluir funcionário: {0}", ex.getMessage());
            }
        }

        // Método para listar todos os funcionários
        public List<Funcionario> listarTodos() {
            String sql = "SELECT * FROM funcionario";
            List<Funcionario> lista = new ArrayList<>();

            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.setId_funcionario(resultado.getInt("id_funcionario"));
                    funcionario.setNome(resultado.getString("nome"));
                    funcionario.setCpf(resultado.getString("cpf"));
                    funcionario.setEmail(resultado.getString("email"));
                    funcionario.setTelefone(resultado.getString("telefone"));
                    funcionario.setDataadmissao(resultado.getString("dataadmissao"));
                    funcionario.setSenha(resultado.getString("senha"));
                    lista.add(funcionario);
                }

                LOGGER.log(Level.INFO, "Funcionários listados com sucesso. Total: {0}", lista.size());

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Erro ao listar funcionários: {0}", ex.getMessage());
            }

            return lista;
        }

        // Método para buscar um funcionário por ID
        public Funcionario buscarPorID(int idFuncionario) {
            String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
            Funcionario funcionario = null;

            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, idFuncionario);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        funcionario = new Funcionario();
                        funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                        funcionario.setNome(rs.getString("nome"));
                        funcionario.setCpf(rs.getString("cpf"));
                        funcionario.setEmail(rs.getString("email"));
                        funcionario.setTelefone(rs.getString("telefone"));
                        funcionario.setDataadmissao(rs.getString("dataadmissao"));
                        funcionario.setSenha(rs.getString("senha"));
                    }
                }

                LOGGER.log(Level.INFO, "Funcionário buscado com sucesso: {0}", funcionario);

            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Erro ao buscar funcionário por ID: {0}", ex.getMessage());
            }

            return funcionario;
        }
}