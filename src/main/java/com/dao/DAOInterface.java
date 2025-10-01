package main.java.com.dao;

import java.util.List;

public interface DAOInterface <T,ID>{

    public T create(T t);
    public int delete(ID id);
    public T update(ID id,T t);
    public T finById(ID id);
    public List<T> finAll(ID id);

}
