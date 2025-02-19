package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product findById(String id);
    Product edit(Product updatedProduct);
    boolean delete(String id);
    public List<Product> findAll();
}