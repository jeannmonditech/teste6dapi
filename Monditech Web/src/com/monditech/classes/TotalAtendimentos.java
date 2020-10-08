package com.monditech.classes;

public class TotalAtendimentos {
	
	private String matriculaColaborador;	
	private String nomeColaborador;
	private int totalAtendimentos;
	private String dataAtendimento;
	
	
	public String getNomeColaborador() {
		return nomeColaborador;
	}
	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}
	public int getTotalAtendimentos() {
		return totalAtendimentos;
	}
	public void setTotalAtendimentos(int totalAtendimentos) {
		this.totalAtendimentos = totalAtendimentos;
	}
	public String getMatriculaColaborador() {
		return matriculaColaborador;
	}
	public void setMatriculaColaborador(String matriculaColaborador) {
		this.matriculaColaborador = matriculaColaborador;
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

}
