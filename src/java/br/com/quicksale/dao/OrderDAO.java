package br.com.quicksale.dao;

import br.com.quicksale.beans.Client;
import br.com.quicksale.beans.Order;
import br.com.quicksale.infrastructure.HibernateUtil;
import java.util.List;
import org.hibernate.Query;

public class OrderDAO extends GenericDAO<Order> {

    public List<Order> findClientOrdersByClient(Long clientID) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query select = session.createQuery("from Order o where client.id = ? ORDER BY o.id DESC");
        
        select.setLong(0, clientID);        
        List<Order> orders = select.list();
        session.getTransaction().commit();
        return orders;

    }

}
