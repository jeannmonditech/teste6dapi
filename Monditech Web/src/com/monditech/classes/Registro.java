package com.monditech.classes;

public class Registro {
	
	private String colaborador;
	private int setor;
	private int equipe;
	private int codErro;
	private String matricula;
	private int totalErro; 
	private int totalDivergencia;
	private String totalAtendimento;
	private String nomeErro; 
	private double mediaPonderada;
	private double mediaAdp;
	private String dataInicio;
	private String dataFim;
	private double erroXPesoXcem;
	private String total;
	private String percentual;
	

	public Registro() {
		// TODO Auto-generated constructor stub
	}
	
	  
	public Registro(String colaborador, int setor, int equipe, int codErro,
			String matricula, int totalErro, int totalDivergencia,
			String totalAtendimento, String nomeErro, double mediaPonderada,
			double mediaAdp, String dataInicio, String dataFim,
			double erroXPesoXcem, String total, String percentual) {
		super();
		this.colaborador = colaborador;
		this.setor = setor;
		this.equipe = equipe;
		this.codErro = codErro;
		this.matricula = matricula;
		this.totalErro = totalErro;
		this.totalDivergencia = totalDivergencia;
		this.totalAtendimento = totalAtendimento;
		this.nomeErro = nomeErro;
		this.mediaPonderada = mediaPonderada;
		this.mediaAdp = mediaAdp;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.erroXPesoXcem = erroXPesoXcem;
		this.total = total;
		this.percentual = percentual;
	}


	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
	}



	public String getPercentual() {
		return percentual;
	}



	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}



	public double getErroXPesoXcem() {
		return erroXPesoXcem;
	}



	public void setErroXPesoXcem(double erroXPesoXcem) {
		this.erroXPesoXcem = erroXPesoXcem;
	}



	public String getTotalAtendimento() {
		return totalAtendimento;
	}



	public void setTotalAtendimento(String totalAtendimento) {
		this.totalAtendimento = totalAtendimento;
	}



	public String getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}



	public String getDataFim() {
		return dataFim;
	}



	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}



	public double getMediaPonderada() {
		return mediaPonderada;
	}
	public void setMediaPonderada(double mediaPonderada) {
		this.mediaPonderada = mediaPonderada;
	}
	public double getMediaAdp() {
		return mediaAdp;
	}
	public void setMediaAdp(double mediaAdp) {
		this.mediaAdp = mediaAdp;
	}
	public String getNomeErro() {
		return nomeErro;
	}
	public void setNomeErro(String nomeErro) {
		this.nomeErro = nomeErro;
	}
	 
	public String getColaborador() {
		return colaborador;
	}
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}
	public int getSetor() {
		return setor;
	}
	public void setSetor(int setor) {
		this.setor = setor;
	}
	public int getEquipe() {
		return equipe;
	}
	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}
	public int getCodErro() {
		return codErro;
	}
	public void setCodErro(int codErro) {
		this.codErro = codErro;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getTotalErro() {
		return totalErro;
	}
	public void setTotalErro(int totalErro) {
		this.totalErro = totalErro;
	}
 
	public int getTotalDivergencia() {
		return totalDivergencia;
	}
	public void setTotalDivergencia(int totalDivergencia) {
		this.totalDivergencia = totalDivergencia;
	}

}
