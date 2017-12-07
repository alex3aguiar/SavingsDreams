package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.PrimitiveIterator;

public class Movimentacao {
	
	private int id;
	private Poupanca poupanca;
	private Sonho sonho;
	private String descricao;
	private LocalDateTime ultimaAtualizacao;
	private BigDecimal valor;
	private TipoMovimentacao tipo;
	
	public Movimentacao() {
		ultimaAtualizacao = LocalDateTime.now();
	}

	public Movimentacao(int id, Poupanca poupanca, Sonho sonho, LocalDateTime ultimaAtualizacao,
			BigDecimal valor) {
		this.id = id;
		this.poupanca = poupanca;
		this.sonho = sonho;
		this.ultimaAtualizacao = ultimaAtualizacao;
		this.valor = valor;
		
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
	
	public Sonho getSonho() {
		return sonho;
	}
	
	public void setSonho(Sonho sonho) {
		this.sonho = sonho;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}
	
	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}
	
	
	
}
