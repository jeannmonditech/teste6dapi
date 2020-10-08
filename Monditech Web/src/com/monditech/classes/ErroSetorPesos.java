package com.monditech.classes;

public class ErroSetorPesos {
	
	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	public int getErro() {
		return erro;
	}

	public void setErro(int erro) {
		this.erro = erro;
	}

	public float getPesoSetorNovo() {
		return pesoSetorNovo;
	}

	public void setPesoSetorNovo(float pesoSetorNovo) {
		this.pesoSetorNovo = pesoSetorNovo;
	}

	private int setor;
	private int erro;

	private float pesoSetorNovo;
}
