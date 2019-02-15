package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.infra.repositorios.UsuarioRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class LogoutUsuario implements Command {

	private UsuarioRepositorio usuarioRepositorio;

	public LogoutUsuario() {
		if (usuarioRepositorio == null) {
			this.usuarioRepositorio = new UsuarioRepositorio();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().setAttribute("usuarioLogado", null);
			request.getRequestDispatcher("/index.jsp").forward(request, response);

		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}
