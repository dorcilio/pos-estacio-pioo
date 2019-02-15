package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.infra.repositorios.AnuncianteRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class DeletarAnunciante implements Command {

	private AnuncianteRepositorio anuncianteRepositorio;

	public DeletarAnunciante() {
		if (anuncianteRepositorio == null) {
			this.anuncianteRepositorio = new AnuncianteRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			Anunciante anunciante = new Anunciante("", "", "", "", "");

			anunciante.setId(request.getParameter("id"));

			this.anuncianteRepositorio.excluir(anunciante);

			RequestDispatcher d = request.getRequestDispatcher("Controller?command=ListarAnunciante");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}