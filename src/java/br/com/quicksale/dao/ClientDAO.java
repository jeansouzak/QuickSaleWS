package br.com.quicksale.dao;

import org.hibernate.Query;
import br.com.quicksale.beans.Client;
import br.com.quicksale.infrastructure.HibernateUtil;


public class ClientDAO extends GenericDAO<Client>
{   
    
    public Client findClientByCPF(String cpf)
    {
      
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query select = session.createQuery("from Client where cpf = ?");
        select.setString(0, cpf);
        Client client = (Client) select.uniqueResult();
        session.getTransaction().commit();
        return client;
    }

    
   
    
}
