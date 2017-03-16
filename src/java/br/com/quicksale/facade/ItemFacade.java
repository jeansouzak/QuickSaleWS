package br.com.quicksale.facade;

import br.com.quicksale.dao.ItemDAO;
import br.com.quicksale.beans.OrderItem;



/**
 *
 * @author jean.souza
 */
public class ItemFacade {

    private final ItemDAO itemDao;

    public ItemFacade() {
        itemDao = new ItemDAO();
    }

  
    public void createItem(OrderItem item)
    {
        itemDao.create(item);
    }
  

}
