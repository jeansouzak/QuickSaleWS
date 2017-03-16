package br.com.quicksale.facade;

import java.util.List;
import br.com.quicksale.dao.ProductDAO;
import br.com.quicksale.beans.Product;
/**
 *
 * @author jean.souza
 */
public class ProductFacade {

    private final ProductDAO productDao;

    public ProductFacade() {
        productDao = new ProductDAO();
    }

    public List<Product> listProducts() {        
        return productDao.find();
    }

    public boolean removeProduct(Product product) {
       productDao.remove(product);
       return true;
    }
    
    public void createProduct(Product product)
    {
        productDao.create(product);
    }
    
    public void editProduct(Product product)
    {
        productDao.edit(product);
    }
    
    public List<Product> getProductsByDescription(String description)
    {
        return productDao.findProductsByDescription(description);
    }
    
    public Product getProductByID(Long id)
    {
        Product p = (Product) productDao.findByID(id);
        
        return p;
    }

}
