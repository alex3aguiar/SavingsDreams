package controller;

import java.util.List;

import persistence.DAO;

public abstract class Controller <T>{
	
	protected DAO<T> dao;
	
	public abstract T salvar(T t);
	public abstract T alterar(T t);
	public abstract Boolean deletar(T t);
	public abstract List<T> listar();
	public abstract T buscarPorId(Integer id);
	
}
