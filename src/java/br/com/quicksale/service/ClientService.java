package br.com.quicksale.service;

import br.com.quicksale.beans.Client;
import br.com.quicksale.beans.Order;
import br.com.quicksale.dao.OrderDAO;
import java.util.List;


/**
 *
 * @author jean.souza
 */
public class ClientService {
    
    public boolean clientCanBeDeleted(Client client)
    {
        OrderDAO orderDao = new OrderDAO();
        List<Order> orders = orderDao.findClientOrdersByClient(client.getId());
        if(orders != null && orders.size() > 0) {
            return false;
        }
        return true;
    }
    
}
