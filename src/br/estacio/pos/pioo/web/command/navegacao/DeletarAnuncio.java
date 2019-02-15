package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.domain.Anuncio;
import br.estacio.pos.pioo.domain.Categoria;
import br.estacio.pos.pioo.infra.repositorios.AnuncioRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class DeletarAnuncio implements Command {

	private AnuncioRepositorio anuncioRepositorio;

	public DeletarAnuncio() {
		if (anuncioRepositorio == null) {
			this.anuncioRepositorio = new AnuncioRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			Anuncio anuncio = new Anuncio(new Categoria(""), new Anunciante("", "", "", "", ""), new Date());

			anuncio.setId(request.getParameter("id"));

			this.anuncioRepositorio.excluir(anuncio);

			RequestDispatcher d = request.getRequestDispatcher("Controller?command=ListarAnuncio");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}