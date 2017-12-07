package model;

public enum TipoMovimentacao {
	SAIDA("Saida"),
	ENTRADA("Entrada"), 
	REALIZACAO_SONHO("Realização de sonho");
	
	String descricao;
	
	private TipoMovimentacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
