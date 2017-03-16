package br.com.quicksale.facade;

import java.util.List;
import br.com.quicksale.dao.ClientDAO;
import br.com.quicksale.beans.Client;
import br.com.quicksale.beans.Order;
import br.com.quicksale.dao.OrderDAO;
import br.com.quicksale.service.ClientService;

/**
 *
 * @author jean.souza
 */
public class ClientFacade {
    
    private final ClientDAO clientDAO;
    private final OrderDAO orderDAO;
    
    public ClientFacade() {
        clientDAO = new ClientDAO();
        orderDAO = new OrderDAO();
    }
    
    public List<Client> listClients() {       
        return clientDAO.find();
    }
    
    public boolean removeClient(Client client) {
        ClientService clienteService = new ClientService();
        if (clienteService.clientCanBeDeleted(client)) {
            clientDAO.remove(client);
            return true;
        }
        return false;
        
    }
    
    public void createClient(Client client) {
        clientDAO.create(client);
    }
    
    public void editClient(Client client) {
        clientDAO.edit(client);
    }
    
    public Client getClientByCPF(String cpf) {
        return clientDAO.findClientByCPF(cpf);        
    }
    
    public Client getClientByID(Long id)
    {
        return (Client) clientDAO.findByID(id);
    }
    
    
    
}
