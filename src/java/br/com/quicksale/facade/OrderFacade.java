package br.com.quicksale.facade;

import br.com.quicksale.beans.Client;
import java.util.List;
import br.com.quicksale.dao.OrderDAO;
import br.com.quicksale.beans.Order;

/**
 *
 * @author jean.souza
 */
public class OrderFacade {

    private final OrderDAO orderDao;

    public OrderFacade() {
        orderDao = new OrderDAO();
    }

    public List<Order> listOrders() {        
        return orderDao.find();
    }

    public boolean removeOrder(Order order) {
       orderDao.remove(order);
       return true;
    }
    
    public void createOrder(Order order)
    {
        orderDao.create(order);
    }
    
    public void editOrder(Order order)
    {
        orderDao.edit(order);
    }
    
    public List<Order> getClientOrders(Long clientID) {
        return orderDao.findClientOrdersByClient(clientID);
    }

}
