package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Mock
    private ProductService service;



    @Test
    public void createProductPageTest() throws Exception {
        mvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }

    @Test
    public void createProductPostTest() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(999);

        Mockito.when(service.create(product)).thenReturn(product);
        mvc.perform(post("/product/create").flashAttr("product", product))
                .andExpect(status().is3xxRedirection());

        Mockito.when(service.findAll()).thenReturn(List.of(product));
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("Sampo Cap Bambang")))
                .andExpect(content().string(containsString("999")));
    }

    @Test
    public void editProductPageTest() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(999);
        productRepository.create(product);

        mvc.perform(get("/product/edit/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit Product")));
    }

    @Test
    public void editProductPageIfEmpty() throws Exception {
        String nonExistentId = UUID.randomUUID().toString(); // ID yang tidak ada

        when(service.findById(nonExistentId)).thenReturn(null);

        mvc.perform(get("/product/edit/" + nonExistentId))
                .andExpect(status().is3xxRedirection()) // Pastikan terjadi redirect
                .andExpect(redirectedUrl("/product/list")); // Redirect ke daftar produk
    }

    @Test
    public void editProductPostTest() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(999);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(100);

        Mockito.when(service.edit(editedProduct)).thenReturn(editedProduct);
        mvc.perform(post("/product/edit/" + editedProduct.getProductId()).flashAttr("product", editedProduct))
                .andExpect(status().is3xxRedirection());

        Mockito.when(service.findAll()).thenReturn(List.of(product));
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("Sampo Cap Bango")))
                .andExpect(content().string(containsString("100")));
    }

    @Test
    public void deleteProductTest() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(999);
        productRepository.create(product);

        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(containsString("Sampo Cap Bambang")))
                .andExpect(content().string(containsString("999")));

        Mockito.when(service.delete(product.getProductId())).thenReturn(true);
        mvc.perform(get("/product/delete/" + product.getProductId()))
                .andExpect(status().is3xxRedirection());

        Mockito.when(service.findAll()).thenReturn(List.of(product));
        mvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product' List")))
                .andExpect(content().string(not(containsString("Sampo Cap Bambang"))))
                .andExpect(content().string(not(containsString("999"))));
    }
}