package br.edu.imepac.daos;

import br.edu.imepac.entidades.Convenio;
import br.edu.imepac.utilitario.ConectarBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvenioDAO {

    private static final Logger LOGGER = Logger.getLogger(ConvenioDAO.class.getName());

    // Método para cadastrar um novo convenio
    public void cadastrar(Convenio convenio) {
        String sql = "INSERT INTO convenio (nome, codigo, validade, cobertura) VALUES (?, ?, ?, ?)";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, convenio.getNome());
            stmt.setString(2, convenio.getCodigo());
            stmt.setString(3, convenio.getValidade());
            stmt.setDouble(4, convenio.getCobertura());

            stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Convênio cadastrado com sucesso: {0}", convenio);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar convênio: {0}", ex.getMessage());
        }
    }

    // Método para atualizar um convenio
    public void atualizar(Convenio convenio) {
        String sql = "UPDATE convenio SET nome = ?, codigo = ?, validade = ?, cobertura = ? WHERE id = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, convenio.getNome());
            stmt.setString(2, convenio.getCodigo());
            stmt.setString(3, convenio.getValidade());
            stmt.setDouble(4, convenio.getCobertura());
            stmt.setInt(5, convenio.getId());

            stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Convênio atualizado com sucesso: {0}", convenio);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar convênio: {0}", ex.getMessage());
        }
    }

    // Método para excluir um convenio
    public void excluir(int id) {
        String sql = "DELETE FROM convenio WHERE id = ?";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            LOGGER.log(Level.INFO, "Convênio excluído com sucesso. ID: {0}", id);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir convênio: {0}", ex.getMessage());
        }
    }

    // Método para listar todos os convenios
    public List<Convenio> listarTodos() {
        List<Convenio> convenios = new ArrayList<>();
        String sql = "SELECT * FROM convenio";
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Convenio convenio = new Convenio();
                convenio.setId(rs.getInt("id"));
                convenio.setNome(rs.getString("nome"));
                convenio.setCodigo(rs.getString("codigo"));
                convenio.setValidade(rs.getString("validade"));
                convenio.setCobertura(rs.getDouble("cobertura"));

                convenios.add(convenio);
            }

            LOGGER.log(Level.INFO, "Convenios listados com sucesso. Total: {0}", convenios.size());

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao listar convênios: {0}", ex.getMessage());
        }
        return convenios;
    }

    // Método para buscar um convenio por ID
    public Convenio buscarPorId(int id) {
        String sql = "SELECT * FROM convenio WHERE id = ?";
        Convenio convenio = null;
        try (Connection con = ConectarBanco.getConectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    convenio = new Convenio();
                    convenio.setId(rs.getInt("id"));
                    convenio.setNome(rs.getString("nome"));
                    convenio.setCodigo(rs.getString("codigo"));
                    convenio.setValidade(rs.getString("validade"));
                    convenio.setCobertura(rs.getDouble("cobertura"));
                }
            }

            LOGGER.log(Level.INFO, "Convênio buscado com sucesso: {0}", convenio);

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar convênio por ID: {0}", ex.getMessage());
        }
        return convenio;
    }
}