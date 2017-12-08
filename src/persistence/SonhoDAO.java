package persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Poupanca;
import model.Sonho;

public class SonhoDAO extends DAO<Sonho> {

	@Override
	protected PreparedStatement getInsertQuery(Sonho sonho) throws SQLException {
		final String INSERT = "INSERT INTO `savingsdreams`.`sonho`(`id_poupanca`,`descricao`,`valor`,`dataCriacao`,`dataPrevisao`,`realizado`)VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setInt(1, sonho.getPoupanca().getId());
		preparedStatement.setString(2, sonho.getDescricao());
		preparedStatement.setBigDecimal(3, sonho.getValor());
		preparedStatement.setDate(4, Date.valueOf(sonho.getDataCriacao()));
		preparedStatement.setDate(5, Date.valueOf(sonho.getDataPrevisao()));
		preparedStatement.setBoolean(6, sonho.getRealizado());
		
		return preparedStatement;
	}

	@Override
	protected PreparedStatement getDeleteQuery(Sonho sonho) throws SQLException {
		final String DELETE = "DELETE FROM `savingsdreams`.`sonho` WHERE ID = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(DELETE);
		
		preparedStatement.setInt(1, sonho.getId());
		
		return preparedStatement;	
	}

	@Override
	protected PreparedStatement getUpdateQuery(Sonho sonho) throws SQLException {
		final String UPDATE = "UPDATE `savingsdreams`.`sonho` SET `id_poupanca` = ?,`descricao` = ?,`valor` = ?,`dataCriacao` = ?,`dataPrevisao` = ?,`dataRealizacao` = ?,`realizado` = ? WHERE `id` = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(UPDATE);
		
		preparedStatement.setInt(1, sonho.getPoupanca().getId());
		preparedStatement.setString(2, sonho.getDescricao());
		preparedStatement.setBigDecimal(3, sonho.getValor());
		preparedStatement.setDate(4, Date.valueOf(sonho.getDataCriacao()));
		preparedStatement.setDate(5, Date.valueOf(sonho.getDataPrevisao()));
		preparedStatement.setDate(6, Date.valueOf(sonho.getDataRealizacao()));
		preparedStatement.setBoolean(7, sonho.getRealizado());
		preparedStatement.setInt(8, sonho.getId());
		
		return preparedStatement;	
	}

	@Override
	protected PreparedStatement getListQuery() throws SQLException {
		final String LISTALL = "SELECT * FROM `savingsdreams`.`sonho` ORDER BY `realizado`, `dataRealizacao` desc, `dataCriacao` desc";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTALL);
		return preparedStatement;	
	}

	@Override
	protected PreparedStatement getEntityByIdQuery(int id) throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`sonho` WHERE `id` = ?";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		preparedStatement.setInt(1, id);
		return preparedStatement;
	}
	
	@Override
	protected PreparedStatement getLastEntity() throws SQLException {
		final String LISTPORID = "SELECT * FROM `savingsdreams`.`sonho` ORDER BY `id` DESC LIMIT 1;";
		PreparedStatement preparedStatement = ConexaoBD.getInstance().getConnection().prepareStatement(LISTPORID);
		return preparedStatement;
	}

	@Override
	protected Sonho buildEntity(ResultSet resultSet) throws SQLException {
		resultSet.next();

		Sonho sonho = new Sonho();

		sonho.setId(resultSet.getInt("id"));
		sonho.setPoupanca(new Poupanca(resultSet.getInt("id_poupanca")));
		sonho.setDescricao(resultSet.getString("descricao"));
		sonho.setValor(resultSet.getBigDecimal("valor"));
		sonho.setDataCriacao(resultSet.getDate("dataCriacao").toLocalDate());
		sonho.setDataPrevisao(resultSet.getDate("dataPrevisao").toLocalDate());
		sonho.setDataRealizacao(resultSet.getDate("dataRealizacao") != null ? resultSet.getDate("dataRealizacao").toLocalDate() :  null);
		sonho.setRealizado(resultSet.getBoolean("realizado"));

		return sonho;
	}

	@Override
	protected Sonho setGeneratedKeys(PreparedStatement preparedStatement, Sonho sonho) throws SQLException {
		sonho.setId(preparedStatement.getGeneratedKeys().getConcurrency());
		return sonho;
	}
	
}
