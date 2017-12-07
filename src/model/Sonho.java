package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Sonho {
	private int id;
	private Poupanca poupanca = new Poupanca();
	private String descricao;
	private BigDecimal valor;
	private LocalDate dataCriacao;
	private LocalDate dataPrevisao;
	private LocalDate dataRealizacao;
	private Boolean realizado;
	
	public Sonho() {
		dataCriacao = LocalDate.now();
		realizado = Boolean.FALSE;
	}
	
	public Sonho(int id) {
		this.id = id;
	}

	public Sonho(int id, Poupanca poupanca, String descricao, LocalDate dataCriacao, LocalDate dataPrevisao,
			LocalDate dataRealizacao, Boolean realizado) {
		this.id = id;
		this.poupanca = poupanca;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataPrevisao = dataPrevisao;
		this.dataRealizacao = dataRealizacao;
		this.realizado = realizado;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Poupanca getPoupanca() {
		return poupanca;
	}
	
	public void setPoupanca(Poupanca poupanca) {
		this.poupanca = poupanca;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public LocalDate getDataPrevisao() {
		return dataPrevisao;
	}
	
	public void setDataPrevisao(LocalDate dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
	
	public LocalDate getDataRealizacao() {
		return dataRealizacao;
	}
	
	public void setDataRealizacao(LocalDate dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	
	public Boolean getRealizado() {
		return realizado;
	}
	
	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}
	
	
}
