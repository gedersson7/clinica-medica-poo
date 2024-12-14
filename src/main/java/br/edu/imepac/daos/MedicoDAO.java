package br.edu.imepac.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.imepac.entidades.Medico;
import br.edu.imepac.utilitario.ConectarBanco;

import javax.swing.*;


public class MedicoDAO {

    private static final Logger LOGGER = Logger.getLogger(MedicoDAO.class.getName());

    public void cadastrar(Medico m) {
        String sql = "INSERT INTO medico (nome, email, crm, telefone, especializacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql)) {
            smt.setString(1, m.getNome_medico());
            smt.setString(2, m.getEmail());
            smt.setString(3, m.getCrm());
            smt.setString(4, m.getTelefone());
            smt.setString(5, m.getEspecializacao());
            smt.executeUpdate();
            LOGGER.info("Médico cadastrado com sucesso: " + m.getNome_medico());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar o médico: {0}", ex.getMessage());
        }
    }

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
            LOGGER.info("Médico atualizado com sucesso: " + m.getNome_medico());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar o médico: {0}", ex.getMessage());
        }
    }

    public void excluir(Medico m) {
        String sql = "DELETE FROM medico WHERE id_medico = ?";
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o médico " + m.getNome_medico() + "?", "Excluir Médico", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            try (Connection con = ConectarBanco.getConectar(); PreparedStatement smt = con.prepareStatement(sql)) {
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                LOGGER.info("Médico excluído com sucesso: " + m.getNome_medico());
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Erro ao excluir o médico: {0}", ex.getMessage());
            }
        }
    }

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
            LOGGER.info("Lista de médicos obtida com sucesso.");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro ao listar os médicos: {0}", ex.getMessage());
        }
        return listaMedico;
    }
}