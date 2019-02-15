package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.infra.repositorios.AnuncianteRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class ListarAnunciante implements Command {

	private AnuncianteRepositorio anuncianteRepositorio;

	public ListarAnunciante() {
		if (anuncianteRepositorio == null) {
			this.anuncianteRepositorio = new AnuncianteRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Anunciante> anunciantes = this.anuncianteRepositorio.listarConsulta("");

			request.setAttribute("anunciantes", anunciantes);
			RequestDispatcher d = request.getRequestDispatcher("/Anunciantes/ListarAnunciante.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}