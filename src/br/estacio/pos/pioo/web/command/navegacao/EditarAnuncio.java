package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Anunciante;
import br.estacio.pos.pioo.domain.Anuncio;
import br.estacio.pos.pioo.domain.Categoria;
import br.estacio.pos.pioo.infra.repositorios.AnuncianteRepositorio;
import br.estacio.pos.pioo.infra.repositorios.AnuncioRepositorio;
import br.estacio.pos.pioo.infra.repositorios.CategoriaRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class EditarAnuncio implements Command {

	private CategoriaRepositorio categoriaRepositorio;
	private AnuncianteRepositorio anuncianteRepositorio;
	private AnuncioRepositorio anuncioRepositorio;

	public EditarAnuncio() {
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
			Anuncio anuncio = null;

			if (request.getAttribute("id") != null) {
				anuncio = this.anuncioRepositorio.selecionarConsulta(request.getAttribute("id").toString());
			} else {
				anuncio = new Anuncio(new Categoria(""), new Anunciante("", "", "", "", ""), new Date());
			}
			List<Categoria> categorias = this.categoriaRepositorio.listarConsulta("");
			List<Anunciante> anunciantes = this.anuncianteRepositorio.listarConsulta("");

			request.setAttribute("categorias", categorias);
			request.setAttribute("anunciantes", anunciantes);
			request.setAttribute("anuncio", anuncio);

			RequestDispatcher d = request.getRequestDispatcher("/Anuncios/EditarAnuncio.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}