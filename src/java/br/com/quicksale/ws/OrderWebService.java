package br.com.quicksale.ws;

import br.com.quicksale.beans.Client;
import br.com.quicksale.beans.Order;
import br.com.quicksale.beans.OrderAggregate;
import br.com.quicksale.beans.OrderItem;
import br.com.quicksale.facade.ItemFacade;
import br.com.quicksale.facade.OrderFacade;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.collection.internal.PersistentBag;

/**
 * REST Web Service
 *
 * @author jean.souza
 */
@Path("order")
public class OrderWebService {

    private final OrderFacade orderFacade;
    private final ItemFacade itemFacade;

    public OrderWebService() {
        this.orderFacade = new OrderFacade();
        this.itemFacade = new ItemFacade();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response create(OrderAggregate orderAggregate) {     
        Order order = orderAggregate.getOrder();
        this.orderFacade.createOrder(order);
        orderAggregate.getOrderItem().stream().forEach((item) -> {
            item.setOrder(order);
            itemFacade.createItem(item);
        });
        return Response.ok().build();
    }

    @GET
    @Path("findClientOrders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findClientOrdersByID(@PathParam("id") Long id) {
        List<Order> orders = orderFacade.getClientOrders(id);

        if (orders.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Orders not found\"").build();
        }
        Response r = null;
        try {

            GenericEntity<List<Order>> responseOrders;
            responseOrders = new GenericEntity<List<Order>>(orders) {

            };

            r = Response.
                    ok().
                    entity(responseOrders).
                    build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

}
