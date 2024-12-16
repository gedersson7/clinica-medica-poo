package br.edu.imepac.daos;

import br.edu.imepac.entidades.Consulta;
import br.edu.imepac.entidades.Funcionario;
import br.edu.imepac.entidades.Paciente;
import br.edu.imepac.entidades.Medico;
import br.edu.imepac.utilitario.ConectarBanco;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    // Método para cadastrar uma nova consulta
    public void cadastrar(Consulta consulta) {
        String sql = "INSERT INTO consulta (dataAtendimento, horario, id_paciente, id_medico, id_funcionario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(consulta.getData()));
            stmt.setString(2, consulta.getHoras());
            stmt.setInt(3, consulta.getPaciente().getId_paciente());
            stmt.setInt(4, consulta.getMedico().getId_medico());
            stmt.setInt(5, consulta.getFuncionario().getId_funcionario());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar consulta: " + ex.getMessage());
        }
    }

    // Método para atualizar uma consulta
    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consulta SET dataAtendimento = ?, horario = ?, id_paciente = ?, id_medico = ?, id_funcionario = ? WHERE id_consulta = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getHoras());
            stmt.setInt(3, consulta.getPaciente().getId_paciente());
            stmt.setInt(4, consulta.getMedico().getId_medico());
            stmt.setInt(5, consulta.getFuncionario().getId_funcionario());
            stmt.setInt(6, consulta.getId_consulta());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta atualizada com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar consulta: " + ex.getMessage());
        }
    }

    // Método para excluir uma consulta
    public void excluir(Consulta consulta) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getId_consulta());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta excluída com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir consulta: " + ex.getMessage());
        }
    }

    // Método para listar todas as consultas
    public List<Consulta> listarTodas() {
        String sql = "SELECT * FROM consulta " +
                "INNER JOIN paciente ON paciente.id_paciente = consulta.id_paciente " +
                "INNER JOIN medico ON medico.id_medico = consulta.id_medico " +
                "INNER JOIN funcionario ON funcionario.id_funcionario = consulta.id_funcionario";

        List<Consulta> lista = new ArrayList<>();

        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Consulta consulta = new Consulta();
                consulta.setId_consulta(resultado.getInt("consulta.id_consulta"));
                consulta.setData(resultado.getString("consulta.dataAtendimento"));
                consulta.setHoras(resultado.getString("consulta.horario"));

                Paciente paciente = new Paciente();
                paciente.setId_paciente(resultado.getInt("paciente.id_paciente"));
                paciente.setNome(resultado.getString("paciente.nome"));
                consulta.setPaciente(paciente);

                Medico medico = new Medico();
                medico.setId_medico(resultado.getInt("medico.id_medico"));
                medico.setNome_medico(resultado.getString("medico.nome"));
                consulta.setMedico(medico);

                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(resultado.getInt("funcionario.id_funcionario"));
                funcionario.setNome(resultado.getString("funcionario.nome"));
                consulta.setFuncionario(funcionario);

                lista.add(consulta);
            }

            JOptionPane.showMessageDialog(null, "Consultas listadas com sucesso. Total: " + lista.size());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar consultas: " + ex.getMessage());
        }

        return lista;
    }

    // Método para buscar uma consulta por ID
    public Consulta buscarPorID(int idConsulta) {
        String sql = "SELECT * FROM consulta " +
                "INNER JOIN paciente ON paciente.id_paciente = consulta.id_paciente " +
                "INNER JOIN medico ON medico.id_medico = consulta.id_medico " +
                "INNER JOIN funcionario ON funcionario.id_funcionario = consulta.id_funcionario " +
                "WHERE consulta.id_consulta = ?";

        Consulta consulta = null;

        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idConsulta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consulta = new Consulta();
                    consulta.setId_consulta(rs.getInt("consulta.id_consulta"));
                    consulta.setData(rs.getString("consulta.dataAtendimento"));
                    consulta.setHoras(rs.getString("consulta.horario"));

                    Paciente paciente = new Paciente();
                    paciente.setId_paciente(rs.getInt("paciente.id_paciente"));
                    paciente.setNome(rs.getString("paciente.nome"));
                    consulta.setPaciente(paciente);

                    Medico medico = new Medico();
                    medico.setId_medico(rs.getInt("medico.id_medico"));
                    medico.setNome_medico(rs.getString("medico.nome"));
                    consulta.setMedico(medico);

                    Funcionario funcionario = new Funcionario();
                    funcionario.setId_funcionario(rs.getInt("funcionario.id_funcionario"));
                    funcionario.setNome(rs.getString("funcionario.nome"));
                    consulta.setFuncionario(funcionario);
                }
            }

            JOptionPane.showMessageDialog(null, "Consulta buscada com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar consulta por ID: " + ex.getMessage());
        }

        return consulta;
    }
}
