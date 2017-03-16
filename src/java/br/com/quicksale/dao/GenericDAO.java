package br.com.quicksale.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.quicksale.infrastructure.HibernateUtil;

public abstract class GenericDAO<T> implements GenericDAOInterface {

    protected Session session;

    private final Class<T> persistentClass;

    public GenericDAO() {        
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
       
    }

    @Override
    public Object findByID(Long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Object obj = session.get(persistentClass.getName(), id);
        session.getTransaction().commit();
        return obj;
    }
    
    @Override
    public List<T> find() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query select = session.createQuery("from " + persistentClass.getName());
        List objetos = select.list();
        session.getTransaction().commit();
        return objetos;
    }

    @Override
    public void create(Object object) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    @Override
    public void edit(Object object) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
    }

    @Override
    public void remove(Object object) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
    }

}
