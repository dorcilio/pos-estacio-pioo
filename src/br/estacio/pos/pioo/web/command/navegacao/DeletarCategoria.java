package br.estacio.pos.pioo.web.command.navegacao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.estacio.pos.pioo.domain.Categoria;
import br.estacio.pos.pioo.infra.repositorios.CategoriaRepositorio;
import br.estacio.pos.pioo.web.command.Command;

public class DeletarCategoria   implements Command {

	private CategoriaRepositorio categoriaRepositorio;
	public DeletarCategoria() {
		if(categoriaRepositorio == null)
		{
			this.categoriaRepositorio = new CategoriaRepositorio();
		}
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			Categoria categoria = new Categoria("");
			categoria.setId(request.getParameter("id"));
			
			this.categoriaRepositorio.excluir(categoria);
			
			RequestDispatcher d = request.getRequestDispatcher("Controller?command=ListarCategoria");
			d.forward(request,response);

		} catch (IOException | ServletException | SQLException e) {
			e.printStackTrace();
		}
	}

}