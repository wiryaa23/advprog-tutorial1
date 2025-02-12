package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    Product findById(String id);
    Product edit(Product updatedProduct);
    public boolean delete(String id);
    public List<Product> findAll();
}