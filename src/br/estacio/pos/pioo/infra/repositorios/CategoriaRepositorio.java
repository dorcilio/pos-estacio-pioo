package br.estacio.pos.pioo.infra.repositorios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.estacio.pos.pioo.domain.Categoria;

public class CategoriaRepositorio extends RepositorioBase
		implements br.estacio.pos.pioo.respositorios.CategoriaRepositorio {

	@Override
	public void inserir(Categoria objeto) throws SQLException {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"INSERT INTO\n"
					+"    Categoria(guid, nome)\n"
					+"VALUES\n"
					+"    (?, ?)"
					);
			ps.setString(1, objeto.getId());
			ps.setString(2, objeto.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir categoria");
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
	public void alterar(Categoria objeto) throws SQLException {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"UPDATE\n"
					+"    categoria\n"
					+"SET\n"
					+"    nome = ?\n"
					+"WHERE\n"
					+"    guid = ?"
					);

			ps.setString(1, objeto.getNome());
			ps.setString(2, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar anunciante");
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
	public void excluir(Categoria objeto) throws SQLException {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"DELETE FROM\n"
					+"    categoria\n"
					+"WHERE\n"
					+"    guid = ?"
					);

			ps.setString(1, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir categoria");
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
	public List<Categoria> listarConsulta(String filtro) throws SQLException {
		ArrayList<Categoria> objetos = new ArrayList<Categoria>();

		try {

			String query = "SELECT * FROM categoria";

			if (!filtro.isEmpty()) {
				query += filtro;
			}

			con = getConnection();
			ps = con.prepareStatement(query);

			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Categoria objeto = new Categoria(resultado.getString("nome"));
				objeto.setId(resultado.getString("guid"));
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
	public Categoria selecionarConsulta(String codigo) throws SQLException {
		Categoria objeto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"SELECT\n"
					+"    *\n"
					+"FROM\n"
					+"    categoria\n"
					+"WHERE\n"
					+"    guid = ?;"
					);
			ps.setString(1, codigo);
			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				objeto = new Categoria(resultado.getString("nome"));
				objeto.setId(resultado.getString("guid"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao selecionar categoria");
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
