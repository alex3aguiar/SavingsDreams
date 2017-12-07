package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import model.Movimentacao;
import model.Poupanca;
import model.Sonho;
import model.TipoMovimentacao;

public class MovimentacaoDAO extends DAO<Movimentacao> {

	@Override
	protected PreparedStatement getInsertQuery(Movimentacao movimentacao) throws SQLException {
		String INSERT = "INSERT INTO `savingsdreams`.`movimentacao` (`id_poupanca`, `ultimaAtualizacao`, `valor`, `descricao`, ##sonho## `tipo`) VALUES (?, ?, ?, ?,##sonhovalue## ?)";
		PreparedStatement preparedStatement = null;
		
		if(movimentacao.getSonho() != null) {
			INSERT = StringUtils.replace(INSERT, "##sonho##", "`id_sonho`,");
			INSERT = StringUtils.replace(INSERT, "##sonhovalue##", " ?,");
			
			preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, movimentacao.getPoupanca().getId());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(movimentacao.getUltimaAtualizacao()));
			preparedStatement.setBigDecimal(3, movimentacao.getValor());
			preparedStatement.setString(4, movimentacao.getDescricao());
			preparedStatement.setInt(5, movimentacao.getSonho() != null ? movimentacao.getSonho().getId() : -1 );
			preparedStatement.setString(6, movimentacao.getTipo().toString());
		} else {
			INSERT = StringUtils.replace(INSERT, "##sonho##", "");
			INSERT = StringUtils.replace(INSERT, "##sonhovalue##", "");
			
			preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, movimentacao.getPoupanca().getId());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(movimentacao.getUltimaAtualizacao()));
			preparedStatement.setBigDecimal(3, movimentacao.getValor());
			preparedStatement.setString(4, movimentacao.getDescricao());
			preparedStatement.setString(5, movimentacao.getTipo().toString());
		}
		
		
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getDeleteQuery(Movimentacao movimentacao) throws SQLException {
		final String DELETE = "DELETE FROM `savingsdreams`.`movimentacao` WHERE `id` = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(DELETE);
		
		preparedStatement.setInt(1, movimentacao.getId());
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getUpdateQuery(Movimentacao movimentacao) throws SQLException {
		final String UPDATE = "UPDATE `savingsdreams`.`movimentacao` SET `id_poupanca` = ?, `ultimaAtualizacao` = ?, `valor` = ?, `descricao` = ?, `id_sonho` = ?, `tipo` = ?  WHERE `id` = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(UPDATE);
		
		preparedStatement.setInt(1, movimentacao.getPoupanca().getId());
		preparedStatement.setTimestamp(2, Timestamp.valueOf(movimentacao.getUltimaAtualizacao()));
		preparedStatement.setBigDecimal(3, movimentacao.getValor());
		preparedStatement.setString(4, movimentacao.getDescricao());
		preparedStatement.setInt(5, movimentacao.getSonho().getId());
		preparedStatement.setString(6, movimentacao.getTipo().toString());
		preparedStatement.setInt(7, movimentacao.getId());
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getListQuery() throws SQLException {
		final String LISTALL = "SELECT * FROM `savingsdreams`.`movimentacao`";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTALL);
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getEntityByIdQuery(int id) throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`movimentacao` WHERE id = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		preparedStatement.setInt(1, id);
		return preparedStatement;
	}
	
	@Override
	protected PreparedStatement getLastEntity() throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`movimentacao` ORDER BY `id` DESC LIMIT 1;";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		return preparedStatement;
	}

	@Override
	protected Movimentacao buildEntity(ResultSet resultSet) throws SQLException {
		resultSet.next();
		Movimentacao movimentacao = new Movimentacao();
		
		movimentacao.setId(resultSet.getInt("id"));
		movimentacao.setPoupanca(new Poupanca(resultSet.getInt("id_poupanca")));
		movimentacao.setSonho(new Sonho(resultSet.getInt("id_sonho")));
		movimentacao.setUltimaAtualizacao(resultSet.getTimestamp("ultimaAtualizacao").toLocalDateTime());
		movimentacao.setDescricao(resultSet.getString("descricao"));
		movimentacao.setValor(resultSet.getBigDecimal("valor"));
		movimentacao.setTipo(TipoMovimentacao.valueOf(resultSet.getString("tipo")));
		
		return movimentacao;
	}
	
	@Override
	protected Movimentacao setGeneratedKeys(PreparedStatement preparedStatement, Movimentacao movimentacao) throws SQLException {
		movimentacao.setId(preparedStatement.getGeneratedKeys().getConcurrency());
		return movimentacao;
	}

}
