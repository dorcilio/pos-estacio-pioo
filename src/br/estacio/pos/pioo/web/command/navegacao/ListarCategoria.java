package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Categoria;
import br.estacio.pos.pioo.infra.repositorios.CategoriaRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class ListarCategoria implements Command {

	private CategoriaRepositorio categoriaRepositorio;

	public ListarCategoria() {
		if (categoriaRepositorio == null) {
			this.categoriaRepositorio = new CategoriaRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<Categoria> categorias = this.categoriaRepositorio.listarConsulta("");

			request.setAttribute("categorias", categorias);

			RequestDispatcher d = request.getRequestDispatcher("/Categoria/ListarCategoria.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}

	}

}
