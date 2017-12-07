package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Poupanca {
	private int id;
	private BigDecimal saldo;
	private LocalDateTime ultimaAtualizacao;
	
	public Poupanca() {
		
	}

	public Poupanca(int id) {
		this.id = id;
	}
	
	public Poupanca(int id, BigDecimal saldo, LocalDateTime ultimaAtualizacao) {
		this.id = id;
		this.saldo = saldo;
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}
	
}

