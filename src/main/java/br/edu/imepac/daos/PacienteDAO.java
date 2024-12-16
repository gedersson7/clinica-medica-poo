package br.edu.imepac.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.imepac.entidades.Paciente;
import br.edu.imepac.utilitario.ConectarBanco;

import javax.swing.*;

public class PacienteDAO {

    // Método para cadastrar um paciente
    public void cadastrar(Paciente p) {
        String sql = "INSERT INTO paciente (nome, cpf, email, dataNascimento, telefone, sexo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement smt = con.prepareStatement(sql)) {

            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setDate(4, Date.valueOf(p.getDatanasc())); // Usando java.sql.Date
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getSexo());
            smt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar paciente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar os dados de um paciente
    public void atualizar(Paciente p) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, dataNasciemnto = ?, telefone = ?, sexo = ? WHERE id_paciente = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement smt = con.prepareStatement(sql)) {

            smt.setString(1, p.getNome());
            smt.setString(2, p.getCpf());
            smt.setString(3, p.getEmail());
            smt.setDate(4, Date.valueOf(p.getDatanasc()));
            smt.setString(5, p.getTelefone());
            smt.setString(6, p.getSexo());
            smt.setInt(7, p.getId_paciente());
            smt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para excluir um paciente
    public void excluir(Paciente p) {
        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o paciente " + p.getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (Connection con = ConectarBanco.getConectar();
                 PreparedStatement smt = con.prepareStatement(sql)) {

                smt.setInt(1, p.getId_paciente());
                smt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Paciente excluído com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir paciente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para listar todos os pacientes
    public List<Paciente> listarTodos() {
        String sql = "SELECT * FROM paciente ORDER BY nome";
        List<Paciente> lista = new ArrayList<>();

        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement smt = con.prepareStatement(sql);
             ResultSet resultado = smt.executeQuery()) {

            while (resultado.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setCpf(resultado.getString("cpf"));
                p.setEmail(resultado.getString("email"));
                p.setDatanasc(resultado.getDate("dataNascimento").toLocalDate()); // Usando LocalDate
                p.setTelefone(resultado.getString("telefone"));
                p.setSexo(resultado.getString("sexo"));
                lista.add(p);
            }

            JOptionPane.showMessageDialog(null, "Lista de pacientes obtida com sucesso! Total: " + lista.size());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pacientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
}
