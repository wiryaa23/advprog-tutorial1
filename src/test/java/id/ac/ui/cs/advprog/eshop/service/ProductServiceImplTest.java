package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Product sampleProduct = new Product();
        sampleProduct.setProductId("123");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);

        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(sampleProduct);

        when(productRepository.findAll()).thenReturn(mockProducts.iterator());

        List<Product> allProducts = productService.findAll();

        assertEquals(1, allProducts.size());
        assertEquals("123", allProducts.get(0).getProductId());
        verify(productRepository).findAll();
    }

    @Test
    void testDelete() {
        when(productRepository.delete("123")).thenReturn(true);

        boolean result = productService.delete("123");

        assertTrue(result);
        verify(productRepository).delete("123");
    }

    @Test
    void testFindById() {
        Product sampleProduct = new Product();
        sampleProduct.setProductId("123");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);
        when(productRepository.findById("123")).thenReturn(sampleProduct);

        Product foundProduct = productService.findById("123");

        assertNotNull(foundProduct);
        assertEquals("123", foundProduct.getProductId());
        verify(productRepository).findById("123");
    }

    @Test
    void testEdit() {
        Product sampleProduct = new Product();
        sampleProduct.setProductId("123");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);
        when(productRepository.edit(any(Product.class))).thenReturn(sampleProduct);

        Product editedProduct = productService.edit(sampleProduct);

        assertNotNull(editedProduct);
        assertEquals("123", editedProduct.getProductId());
        verify(productRepository).edit(any(Product.class));
    }
}
