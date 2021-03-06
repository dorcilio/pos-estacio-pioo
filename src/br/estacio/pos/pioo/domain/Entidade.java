package br.estacio.pos.pioo.domain;

import java.util.UUID;

import br.estacio.pos.pioo.shared.fluent.Validacao;

public abstract class Entidade extends Validacao {

	private UUID id;

	private boolean novoRegistro;

	public Entidade() {
		super();
		this.setId(UUID.randomUUID());
		this.novoRegistro = true;

	}

	private void setId(UUID valor) {
		this.novoRegistro = false;
		this.id = valor;
	}

	public void setId(String valor) {
		this.novoRegistro = false;
		this.id = UUID.fromString(valor);
	}

	public String getId() {
		return id.toString();
	}

	public boolean isNovoRegistro() {
		return novoRegistro;
	}
}
