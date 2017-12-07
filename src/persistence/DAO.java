package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

	protected Connection connection;

	public final T salvar(T T) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getInsertQuery(T);
			preparedStatement.execute();
			T = setGeneratedKeys(preparedStatement, T);
			ConexaoBD.getInstance().closeConnection();
			return T;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public final boolean deletar(T T) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getDeleteQuery(T);
			preparedStatement.execute();
			ConexaoBD.getInstance().closeConnection();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public final T alterar(T T) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = getUpdateQuery(T);
			preparedStatement.execute();
			ConexaoBD.getInstance().closeConnection();
			return T;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public final List<T> getLista() {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		List<T> list = null;
		try {
			preparedStatement = getListQuery();
			resultSet = preparedStatement.executeQuery();
			list = buildListEntity(resultSet);
			ConexaoBD.getInstance().closeConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}

	public final T getById(int id) {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		T t = null;
		try {
			preparedStatement = getEntityByIdQuery(id);
			resultSet = preparedStatement.executeQuery();
			t = buildEntity(resultSet);
			ConexaoBD.getInstance().closeConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			return t;
		}
	}
	
	public final T getLast() {
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		T t = null;
		try {
			preparedStatement = getLastEntity();
			resultSet = preparedStatement.executeQuery();
			t = buildEntity(resultSet);
			ConexaoBD.getInstance().closeConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			return t;
		}
	}

	protected final List<T>  buildListEntity(ResultSet resultSet) throws SQLException {
		List<T> list = new ArrayList<>();

		while(!resultSet.isLast()) {
			list.add(buildEntity(resultSet));
		}

		return list;
	};

	protected abstract PreparedStatement getInsertQuery(T t) throws SQLException;
	protected abstract PreparedStatement getDeleteQuery(T t) throws SQLException;
	protected abstract PreparedStatement getUpdateQuery(T t) throws SQLException;
	protected abstract PreparedStatement getListQuery() throws SQLException;
	protected abstract PreparedStatement getEntityByIdQuery(int id) throws SQLException;
	protected abstract PreparedStatement getLastEntity() throws SQLException;
	protected abstract T setGeneratedKeys(PreparedStatement preparedStatement, T t) throws SQLException;

	protected abstract T buildEntity(ResultSet resultSet) throws SQLException;


}
