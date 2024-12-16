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
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        }
    }

    public void atualizar(Paciente p) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, email = ?, dataNascimento = ?, telefone = ?, sexo = ? WHERE id_paciente = ?";
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
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o registro!");
        }
    }

    public void excluir(Paciente p) {
        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement smt = con.prepareStatement(sql)) {

            smt.setInt(1, p.getId_paciente());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o registro "+ex.getMessage());
        }
    }

    public List<Paciente> listarTodos () {
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
            smt.close();
            con.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar os registros");
        }
        return lista;
    }
}


