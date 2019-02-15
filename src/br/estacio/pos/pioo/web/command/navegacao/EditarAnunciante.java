package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.infra.repositorios.AnuncianteRepositorio;
import br.estacio.pos.pioo.infra.repositorios.AnuncioRepositorio;
import br.estacio.pos.pioo.infra.repositorios.CategoriaRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class EditarAnunciante implements Command {

	private CategoriaRepositorio categoriaRepositorio;
	private AnuncianteRepositorio anuncianteRepositorio;
	private AnuncioRepositorio anuncioRepositorio;

	public EditarAnunciante() {
		if (categoriaRepositorio == null) {
			this.categoriaRepositorio = new CategoriaRepositorio();
		}

		if (anuncianteRepositorio == null) {
			this.anuncianteRepositorio = new AnuncianteRepositorio();
		}

		if (anuncioRepositorio == null) {
			this.anuncioRepositorio = new AnuncioRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			Anunciante anunciante = null;
			if (request.getParameter("id") != null) {
				anunciante = this.anuncianteRepositorio.selecionarConsulta(request.getParameter("id"));
			} else {
				anunciante = new Anunciante("", "", "", "", "");
			}
			request.setAttribute("anunciante", anunciante);

			RequestDispatcher d = request.getRequestDispatcher("/Anunciantes/EditarAnunciante.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}