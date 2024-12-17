package br.edu.imepac.daos;

import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.utilitario.ConectarBanco;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

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
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar funcionário: " + ex.getMessage());
        }
    }

    // Método para excluir um funcionário
    public void excluir(Funcionario r) {
        Connection con = ConectarBanco.getConectar();
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o funcionário " + r.getNome(), "Exclusão", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, r.getId_funcionario());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionário");
            }
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

            JOptionPane.showMessageDialog(null, "Funcionários listados com sucesso. Total: " + lista.size());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + ex.getMessage());
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

            JOptionPane.showMessageDialog(null, "Funcionário buscado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar funcionário por ID: " + ex.getMessage());
        }

        return funcionario;
    }
}
