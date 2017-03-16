package br.com.quicksale.ws;

import br.com.quicksale.beans.Product;
import br.com.quicksale.facade.ProductFacade;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jean.souza
 */
@Path("product")
public class ProductWebService {

    private final ProductFacade productFacade;

    /**
     * Creates a new instance of ClienteService
     */
    public ProductWebService() {
        this.productFacade = new ProductFacade();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        List products = productFacade.listProducts();
        GenericEntity<List<Product>> responseProducts;
        responseProducts = new GenericEntity<List<Product>>(products) {
        };

        return Response.
                ok().
                entity(responseProducts).
                build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        productFacade.createProduct(product);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
        Product p = productFacade.getProductByID(id);

        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Client not found\"").build();
        }
        productFacade.removeProduct(p);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Product update(Product product) {
        this.productFacade.editProduct(product);
        return product;
    }

    @POST
    @Path("findProductsByDescription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProductsByDescription(String descriptionData) {
        JsonObject jobj = new Gson().fromJson(descriptionData, JsonObject.class);
        String description = jobj.get("description").getAsString();
        List<Product> products = this.productFacade.getProductsByDescription(description);
        
        if (products.size() < 1) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Products not found\"").build();
        }
        
        GenericEntity<List<Product>> responseProducts;
        responseProducts = new GenericEntity<List<Product>>(products) {
        };

        return Response.
                ok().
                entity(responseProducts).
                build();
    }
    
    @GET
    @Path("findOneByID/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProductByID(@PathParam("id") Long id) {
        Product p = productFacade.getProductByID(id);

        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Product not found\"").build();
        }
        return Response.ok().entity(p).build();
    }

}
