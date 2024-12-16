package br.edu.imepac.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.edu.imepac.entidades.Medico;
import br.edu.imepac.utilitario.ConectarBanco;

import javax.swing.*;

public class MedicoDAO {

    // Método para cadastrar um médico
    public void cadastrar(Medico m) {
        String sql = "INSERT INTO medico (nome, email, crm, telefone, especializacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome_medico());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o médico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar os dados de um médico
    public void atualizar(Medico m) {
        String sql = "UPDATE medico SET nome = ?, email = ?, crm = ?, telefone = ?, especializacao = ? WHERE id_medico = ?";
        try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome_medico());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.setInt(6, m.getId_medico());
            smt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o médico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para excluir um médico
    public void excluir(Medico m) {
        String sql = "DELETE FROM medico WHERE id_medico = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o médico " + m.getNome_medico() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Médico excluído com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o médico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para listar todos os médicos
    public List<Medico> listarTodos() {
        String sql = "SELECT * FROM medico ORDER BY nome";
        List<Medico> listaMedico = new ArrayList<>();
        try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql); ResultSet resultado = smt.executeQuery()) {
            while (resultado.next()) {
                Medico m = new Medico();
                m.setId_medico(resultado.getInt("id_medico"));
                m.setNome_medico(resultado.getString("nome"));
                m.setEmail(resultado.getString("email"));
                m.setCrm(resultado.getString("crm"));
                m.setTelefone(resultado.getString("telefone"));
                m.setEspecializacao(resultado.getString("especializacao"));
                listaMedico.add(m);
            }
            JOptionPane.showMessageDialog(null, "Lista de médicos obtida com sucesso! Total: " + listaMedico.size());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os médicos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaMedico;
    }
}
