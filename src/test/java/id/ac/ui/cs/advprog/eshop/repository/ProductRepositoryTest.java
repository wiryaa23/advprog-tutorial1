package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }


    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(savedProduct.getProductId(), product.getProductId());
        assertEquals(savedProduct.getProductName(), product.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de45-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId(product.getProductId());
        newProduct.setProductName("Sampo Cap Bango");
        newProduct.setProductQuantity(999);
        Product editedProduct = productRepository.edit(newProduct);

        assertNotNull(editedProduct);
        assertEquals("Sampo Cap Bango", editedProduct.getProductName());
        assertEquals(999, editedProduct.getProductQuantity());
    }

    @Test
    void testEditIfEmpty() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        product.setProductId("0");

        Product editedProduct = productRepository.edit(product);
        assertNull(editedProduct);
    }

    @Test
    void testEditWhenProductIdDoesNotExistButListNotEmpty() {
        Product existingProduct = new Product();
        existingProduct.setProductName("Sampo Cap Bambang");
        existingProduct.setProductQuantity(100);
        productRepository.create(existingProduct);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("999");
        updatedProduct.setProductName("Sampo Cap Bango");
        updatedProduct.setProductQuantity(999);

        Product result = productRepository.edit(updatedProduct);
        assertNull(result);
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product toBeDeletedProduct = productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertNotNull(productIterator.next());

        boolean isDeleted = productRepository.delete(toBeDeletedProduct.getProductId());
        assertTrue(isDeleted);
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteIfEmpty() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());

        boolean isDeleted = productRepository.delete(product.getProductId());
        assertFalse(isDeleted);
    }

    @Test
    void testDeleteWhenProductIdDoesNotExistButListNotEmpty() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        // Coba hapus ID yang tidak ada (UUID acak)
        boolean isDeleted = productRepository.delete(UUID.randomUUID().toString());

        assertFalse(isDeleted);

        // Pastikan produk masih ada
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEachRemaining(products::add);

        assertEquals(2, products.size());
        assertTrue(products.stream().anyMatch(p -> p.getProductId().equals(product1.getProductId())));
        assertTrue(products.stream().anyMatch(p -> p.getProductId().equals(product2.getProductId())));
    }


    @Test
    void testFindById() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(111);
        productRepository.create(product1);
        Product findProduct1 = productRepository.findById(product1.getProductId());

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(999);
        productRepository.create(product2);
        Product findProduct2 = productRepository.findById(product2.getProductId());

        assertNotNull(findProduct1);
        assertNotNull(findProduct2);
        assertEquals("Sampo Cap Bambang", findProduct1.getProductName());
        assertEquals("Sampo Cap Bango", findProduct2.getProductName());
    }

    @Test
    void testFindByIdIfEmpty() {
        Product findProduct1 = productRepository.findById("0");
        assertNull(findProduct1);
    }
}