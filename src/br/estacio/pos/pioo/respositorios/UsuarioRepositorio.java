package br.estacio.pos.pioo.respositorios;

import br.estacio.pos.pioo.domain.Usuario;

public interface UsuarioRepositorio extends RepositorioBase<Usuario> {

	Usuario AlterarSenha(Usuario objeto);

	Usuario Login(Usuario objeto);

}
