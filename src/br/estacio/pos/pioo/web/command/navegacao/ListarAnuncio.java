package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anuncio;
import br.estacio.pos.pioo.infra.repositorios.AnuncioRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class ListarAnuncio implements Command {

	private AnuncioRepositorio anuncioRepositorio;

	public ListarAnuncio() {
		if (anuncioRepositorio == null) {
			this.anuncioRepositorio = new AnuncioRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Anuncio> anuncios = this.anuncioRepositorio.listarConsulta("");
			request.setAttribute("anuncios", anuncios);
			RequestDispatcher d = request.getRequestDispatcher("/Anuncios/ListarAnuncio.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}