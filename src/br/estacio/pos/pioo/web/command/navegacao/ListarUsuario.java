package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Usuario;
import br.estacio.pos.pioo.infra.repositorios.UsuarioRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class ListarUsuario implements Command {

	private UsuarioRepositorio usuarioRepositorio;

	public ListarUsuario() {
		if (usuarioRepositorio == null) {
			this.usuarioRepositorio = new UsuarioRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<Usuario> usuarios = this.usuarioRepositorio.listarConsulta("");

			request.setAttribute("usuarios", usuarios);

			RequestDispatcher d = request.getRequestDispatcher("/Usuario/ListarUsuario.jsp");
			d.forward(request, response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}

	}

}
