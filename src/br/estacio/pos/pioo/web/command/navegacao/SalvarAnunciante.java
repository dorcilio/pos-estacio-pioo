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

public class SalvarAnunciante implements Command {

	private AnuncianteRepositorio anuncianteRepositorio;

	public SalvarAnunciante() {
		if (anuncianteRepositorio == null) {
			this.anuncianteRepositorio = new AnuncianteRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isNovo = true;
			Anunciante anunciante = new Anunciante(request.getParameter("nome"), request.getParameter("documento"),
					request.getParameter("endereco"), request.getParameter("cidade"), request.getParameter("telefone"));

			if (!request.getParameter("isnew").equals("true")) {
				anunciante.setId(request.getParameter("id"));
				isNovo = false;
			}

			if (anunciante.EValido()) {
				if (isNovo) {
					this.anuncianteRepositorio.inserir(anunciante);
				} else {
					this.anuncianteRepositorio.alterar(anunciante);
				}
			}

			RequestDispatcher d = request.getRequestDispatcher("Controller?command=ListarAnunciante");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}

	}

}