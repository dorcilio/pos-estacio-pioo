package br.estacio.pos.pioo.infra.repositorios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.estacio.pos.pioo.domain.Anunciante;

public class AnuncianteRepositorio extends RepositorioBase
		implements br.estacio.pos.pioo.respositorios.AnuncianteRepositorio {

	@Override
	public void inserir(Anunciante objeto) throws SQLException {
		try {

			con = getConnection();
			ps = con.prepareStatement(
					"INSERT INTO\n"
					+"    anunciante(\n"
					+"        guid,\n"
					+"        nome,\n"
					+"        documento,\n"
					+"        endereco,\n"
					+"        cidade,\n"
					+"        telefone\n"
					+"    )\n"
					+"VALUES\n"
					+"    (?, ?, ?, ?, ?, ?);"
					);
			ps.setString(1, objeto.getId());
			ps.setString(2, objeto.getNome());
			ps.setString(3, objeto.getDocumento());
			ps.setString(4, objeto.getEndereco());
			ps.setString(5, objeto.getCidade());
			ps.setString(6, objeto.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir anunciante");
			objeto.adicionarMensagem("Erro ao inserir anunciante");
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void alterar(Anunciante objeto) throws SQLException {
		try {

			con = getConnection();
			ps = con.prepareStatement(
					"UPDATE\n"
					+"    anunciante\n"
					+"SET\n"
					+"    nome = ?,\n"
					+"    documento = ?,\n"
					+"    endereco = ?,\n"
					+"    cidade = ?,\n"
					+"    telefone = ?\n"
					+"WHERE\n"
					+"    guid = ?;"
					);

			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getDocumento());
			ps.setString(3, objeto.getEndereco());
			ps.setString(4, objeto.getCidade());
			ps.setString(5, objeto.getTelefone());
			ps.setString(6, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar anunciante");
			objeto.adicionarMensagem("Erro ao alterar anunciante");
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void excluir(Anunciante objeto) throws SQLException {
		try {

			con = getConnection();
			ps = con.prepareStatement(
					"DELETE FROM\n"
					+"    anunciante\n"
					+"WHERE\n"
					+"    guid = ?;"
					);

			ps.setString(1, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir anunciante");
			objeto.adicionarMensagem("Erro ao excluir anunciante");
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	public List<Anunciante> listarConsulta(String filtro) throws SQLException {

		ArrayList<Anunciante> objetos = new ArrayList<Anunciante>();

		try {

			String query = "SELECT * FROM anunciante";

			if (!filtro.isEmpty()) {
				query += filtro;
			}

			con = getConnection();
			ps = con.prepareStatement(query);

			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Anunciante objeto = new Anunciante(resultado.getString("nome"), resultado.getString("documento"),
						resultado.getString("endereco"), resultado.getString("cidade"),
						resultado.getString("telefone"));
				objeto.setId(resultado.getString("guid"));
				objeto.setDataCriacao((Date) resultado.getDate("datacriacao"));
				objetos.add(objeto);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao selecionar anunciante");
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return objetos;
	}

	@Override
	public Anunciante selecionarConsulta(String codigo) throws SQLException {
		Anunciante objeto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"SELECT\n"
					+"    *\n"
					+"FROM\n"
					+"    anunciante\n"
					+"WHERE\n"
					+"    guid = ?;");
			ps.setString(1, codigo);
			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				objeto = new Anunciante(resultado.getString("nome"), resultado.getString("documento"),
						resultado.getString("endereco"), resultado.getString("cidade"),
						resultado.getString("telefone"));
				objeto.setId(resultado.getString("guid"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao selecionar anunciante");
			System.out.println(e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return objeto;
	}

}
