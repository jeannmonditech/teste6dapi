package com.monditech.classes;

public class Notas {
	public int getSetor() {
		return setor;
	}
	public void setSetor(int setor) {
		this.setor = setor;
	}
	public String getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public String getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(String fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public float getPontuacaoAceitavel() {
		return pontuacaoAceitavel;
	}
	public void setPontuacaoAceitavel(float pontuacaoAceitavel) {
		this.pontuacaoAceitavel = pontuacaoAceitavel;
	}
	public float getPontuacaoMedia() {
		return pontuacaoMedia;
	}
	public void setPontuacaoMedia(float pontuacaoMedia) {
		this.pontuacaoMedia = pontuacaoMedia;
	}
	public float getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(float notaMedia) {
		this.notaMedia = notaMedia;
	}
	private int setor;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String inicioVigencia;
	private String fimVigencia;
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(String idSetor) {
		this.idSetor = idSetor;
	}
	private int ativo;
	private float pontuacaoAceitavel;
	private float pontuacaoMedia;
	private float notaMedia;
	private String descricao;
	private String idSetor;
}
