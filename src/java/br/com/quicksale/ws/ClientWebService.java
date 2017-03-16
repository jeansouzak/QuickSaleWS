package br.com.quicksale.ws;

import br.com.quicksale.beans.Client;
import br.com.quicksale.facade.ClientFacade;
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
@Path("client")
public class ClientWebService {

    private final ClientFacade clientFacade;

    public ClientWebService() {
        this.clientFacade = new ClientFacade();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        List clients = clientFacade.listClients();
        GenericEntity<List<Client>> responseClients;
        responseClients = new GenericEntity<List<Client>>(clients) {
        };

        return Response.
                ok().
                entity(responseClients).
                build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Client client) {
        clientFacade.createClient(client);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {

        Client c = clientFacade.getClientByID(id);

        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Client not found\"").build();
        }
        if (!clientFacade.removeClient(c)) {
            return Response.serverError().entity("\"Client can not be deleted because have orders.\"").build();
        }
        return Response.ok().entity(c).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Client update(Client client) {
        this.clientFacade.editClient(client);
        return client;
    }

    @GET
    @Path("findOneByCPF/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findClientByCPF(@PathParam("cpf") String cpf) {
        Client c = clientFacade.getClientByCPF(cpf);

        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("\"Client not found\"").build();
        }
        return Response.ok().entity(c).build();
    }

}
