package main.java.com.dao;

import java.util.List;

public interface DAOInterface <T,ID>{

    public int create(T t);
    public int delete(ID id);
    public int update(T t);
    public T finById(ID id);
    public List<T> findAll();

}
