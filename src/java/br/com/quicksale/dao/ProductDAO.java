package br.com.quicksale.dao;

import java.util.List;
import org.hibernate.Query;
import br.com.quicksale.beans.Product;
import br.com.quicksale.infraestrutura.HibernateUtil;

public class ProductDAO extends GenericDAO<Product> {

    
    public List<Product> findProductsByDescription(String description) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query select = session.createQuery("from Product where description LIKE ?");
        select.setString(0, "%"+description+"%");
        List<Product> products = select.list();
        session.getTransaction().commit();
        return products;
    }

}
