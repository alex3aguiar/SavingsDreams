package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import model.Poupanca;

public class PoupancaDAO extends DAO<Poupanca>{

	@Override
	protected PreparedStatement getInsertQuery(Poupanca poupanca) throws SQLException {
		final String INSERT = "INSERT INTO `savingsdreams`.`poupanca` (`saldo`, `ultimaAtualizacao`) VALUES (?, ?)";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setBigDecimal(1, poupanca.getSaldo());
		preparedStatement.setTimestamp(2, Timestamp.valueOf(poupanca.getUltimaAtualizacao()));
		
		return preparedStatement;	
	}

	@Override
	protected PreparedStatement getDeleteQuery(Poupanca poupanca) throws SQLException {
		final String DELETE = "DELETE FROM `savingsdreams`.`poupanca` WHERE `id` = ? ";
		
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(DELETE);
		preparedStatement.setInt(1, poupanca.getId());
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getUpdateQuery(Poupanca poupanca) throws SQLException {
		final String UPDATE = "UPDATE `savingsdreams`.`poupanca` SET `saldo` = ?, `ultimaAtualizacao` = ? WHERE `id` = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(UPDATE);
		
		preparedStatement.setBigDecimal(1, poupanca.getSaldo());
		preparedStatement.setTimestamp(2, Timestamp.valueOf(poupanca.getUltimaAtualizacao()));
		preparedStatement.setInt(3, poupanca.getId());
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getListQuery() throws SQLException {
		final String LISTALL = "SELECT * FROM `savingsdreams`.`poupanca`";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTALL);
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getEntityByIdQuery(int id) throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`poupanca` WHERE id = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		preparedStatement.setInt(1, id);
		return preparedStatement;
	}
	
	@Override
	protected PreparedStatement getLastEntity() throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`poupanca` ORDER BY `id` DESC LIMIT 1;";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		return preparedStatement;
	}

	@Override
	protected Poupanca buildEntity(ResultSet resultSet) throws SQLException {
		resultSet.next();
		
		Poupanca poupanca = new Poupanca();
		
		poupanca.setId(resultSet.getInt("id"));
		poupanca.setSaldo(resultSet.getBigDecimal("saldo"));
		poupanca.setUltimaAtualizacao(resultSet.getTimestamp("ultimaAtualizacao").toLocalDateTime());
		
		return poupanca;
	}
	
	@Override
	protected Poupanca setGeneratedKeys(PreparedStatement preparedStatement, Poupanca poupanca) throws SQLException {
		poupanca.setId(preparedStatement.getGeneratedKeys().getInt("id"));
		return poupanca;
	}
	
	

}
