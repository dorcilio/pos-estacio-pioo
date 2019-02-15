package br.estacio.pos.pioo.infra.repositorios;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.domain.Anuncio;
import br.estacio.pos.pioo.domain.Categoria;

public class AnuncioRepositorio extends RepositorioBase
		implements br.estacio.pos.pioo.respositorios.AnuncioRepositorio {

	@Override
	public void inserir(Anuncio objeto) throws SQLException {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			con = getConnection();
			ps = con.prepareStatement(
					"INSERT INTO\n"
					+"    anuncio(\n"
					+"        guid,\n"
					+"        guidanunciante,\n"
					+"        guidcategoria,\n"
					+"        datavalidade\n"
					+"    )\n"
					+"VALUES\n"
					+"    (?, ?, ?, ?);"
					);
			ps.setString(1, objeto.getId());
			ps.setString(2, objeto.getAnunciante().getId());
			ps.setString(3, objeto.getCategoria().getId());
			ps.setString(4, sdf.format(objeto.getDataValidade()));
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir anuncio");
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
	public void alterar(Anuncio objeto) throws SQLException {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"UPDATE\n"
					+"    anuncio\n"
					+"SET\n"
					+"    guidanunciante = ?,\n"
					+"    guidcategoria = ?,\n"
					+"    datavalidade = ?\n"
					+"WHERE\n"
					+"    guid = ?;"
					);

			ps.setString(1, objeto.getAnunciante().getId());
			ps.setString(2, objeto.getCategoria().getId());
			ps.setDate(3, (Date) objeto.getDataValidade());
			ps.setString(4, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao alterar anuncio");
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
	public void excluir(Anuncio objeto) throws SQLException {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"DELETE FROM\n"
					+"    anuncio\n"
					+"WHERE\n"
					+"    guid = ?;"
					);

			ps.setString(1, objeto.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir anuncio");
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
	public List<Anuncio> listarConsulta(String filtro) throws SQLException {
		ArrayList<Anuncio> objetos = new ArrayList<Anuncio>();

		try {

			String query = "SELECT\n"
							+"    ANUNCIO.GUID,\n"
							+"    ANUNCIO.GUIDANUNCIANTE,\n"
							+"    ANUNCIO.GUIDCATEGORIA,\n"
							+"    ANUNCIO.DATAVALIDADE,\n"
							+"    ANUNCIO.DATACRIACAO,\n"
							+"    ANUNCIANTE.NOME AS ANUNCIANTENOME,\n"
							+"    ANUNCIANTE.DOCUMENTO,\n"
							+"    ANUNCIANTE.ENDERECO,\n"
							+"    ANUNCIANTE.CIDADE,\n"
							+"    ANUNCIANTE.TELEFONE,\n"
							+"    CATEGORIA.NOME AS CATEGORIANOME\n"
							+"FROM\n"
							+"    ANUNCIO\n"
							+"    INNER JOIN + ANUNCIANTE ON ANUNCIANTE.GUID = ANUNCIO.GUIDANUNCIANTE\n"
							+"    INNER JOIN + CATEGORIA ON CATEGORIA.GUID = ANUNCIO.GUIDCATEGORIA";

			if (!filtro.isEmpty()) {
				query += filtro;
			}

			con = getConnection();
			ps = con.prepareStatement(query);

			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				Categoria categoria = new Categoria(resultado.getString("CATEGORIANOME"));
				categoria.setId(resultado.getString("GUIDCATEGORIA"));
				Anunciante anunciante = new Anunciante(resultado.getString("ANUNCIANTENOME"),
						resultado.getString("documento"), resultado.getString("endereco"),
						resultado.getString("cidade"), resultado.getString("telefone"));
				anunciante.setId(resultado.getString("GUIDANUNCIANTE"));
				Anuncio objeto = new Anuncio(categoria, anunciante, (Date) resultado.getDate("DATAVALIDADE"));
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
	public Anuncio selecionarConsulta(String codigo) throws SQLException {
		Anuncio objeto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"SELECT\n"
					+"    ANUNCIO.GUID,\n"
					+"    ANUNCIO.GUIDANUNCIANTE,\n"
					+"    ANUNCIO.GUIDCATEGORIA,\n"
					+"    ANUNCIO.DATAVALIDADE,\n"
					+"    ANUNCIO.DATACRIACAO,\n"
					+"    ANUNCIANTE.NOME AS ANUNCIANTENOME,\n"
					+"    ANUNCIANTE.DOCUMENTO,\n"
					+"    ANUNCIANTE.ENDERECO,\n"
					+"    ANUNCIANTE.TELEFONE,\n"
					+"    CATEGORIA.NOME AS CATEGORIANOME\n"
					+"FROM\n"
					+"    ANUNCIO\n"
					+"    INNER JOIN + ANUNCIANTE ON ANUNCIANTE.GUID = ANUNCIO.GUIDANUNCIANTE\n"
					+"    INNER JOIN + CATEGORIA ON CATEGORIA.GUID = ANUNCIO.GUIDCATEGORIA +\n"
					+"WHERE\n"
					+"    ANUNCIO.GUID = ?;"
					);
			ps.setString(1, codigo);
			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				Categoria categoria = new Categoria(resultado.getString("CATEGORIANOME"));
				categoria.setId(resultado.getString("GUIDCATEGORIA"));
				Anunciante anunciante = new Anunciante(resultado.getString("ANUNCIANTENOME"),
						resultado.getString("documento"), resultado.getString("endereco"),
						resultado.getString("cidade"), resultado.getString("telefone"));
				anunciante.setId(resultado.getString("GUIDANUNCIANTE"));
				objeto = new Anuncio(categoria, anunciante, (Date) resultado.getDate("DATAVALIDADE"));
				objeto.setId(resultado.getString("guid"));
				objeto.setDataCriacao((Date) resultado.getDate("datacriacao"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao selecionar anuncio");
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
