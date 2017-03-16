package br.com.quicksale.dao;

import java.util.List;

public interface GenericDAOInterface<T> {
    
    public List<T> find();
    
    public Object findByID(Long id);
    
    public void create(T object);
    
    public void edit(T object);
    
    public void remove(T object);
    
}
