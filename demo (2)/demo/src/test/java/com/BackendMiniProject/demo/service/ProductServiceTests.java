package com.BackendMiniProject.demo.service;

import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.Exceptions.ResourceAlreadyExistsException;
import com.BackendMiniProject.demo.Exceptions.ResourceNotFoundException;
import com.BackendMiniProject.demo.repository.ProductRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    private static final int id=1;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void getAllProductsTest(){
        Product product = givenProduct();
        List<Product> products = Collections.singletonList(product);
        when(productRepositoryMock.findAll()).thenReturn(products);
        assertEquals(product,productService.getAllProducts().get(0));
    }
    //positive case
    @Test
    public void findProductById(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(id)).thenReturn(Optional.of(product));
        assertEquals(product,productService.getProductById(id).get());
    }
    //negative post case
    @Test
    public void findProductByIdNullCase(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(id)).thenReturn(Optional.of(product));
        assertThrows(ResourceNotFoundException.class,()->{
            productService.getAllProducts().get(0);
        },"No Products found");
    }

    //positive case
    @Test
    public void createProduct(){
        Product product = givenProduct();
        when(productRepositoryMock.save(product)).thenReturn(product);
        assertEquals(product,productService.addProduct(product));
    }
    //negative post case
    @Test
    public void createProductNullCase(){
        Product product = givenProduct();
        when(productRepositoryMock.save(product)).thenReturn(product);
        assertThrows(ResourceAlreadyExistsException.class,()->{
            productService.addProduct(product);
        },"Product already present cannot be added again");
    }
    //positive case
    @Test
    public void updateProduct(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepositoryMock.save(product)).thenReturn(product);
        assertEquals(product,productService.updateProduct(id,product));
    }
    //negative case
    @Test
    public void updateProductNullCase(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(product.getId())).thenReturn(Optional.empty());
        when(productRepositoryMock.save(product)).thenReturn(product);
        assertThrows(ResourceNotFoundException.class,()->{
            productService.updateProduct(id,product);
        },"Product not found with id :" +id);
    }
    //positiveCase
    @Test
    public void deleteProductById(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(product.getId())).thenReturn(Optional.of(product));
        doNothing().when(productRepositoryMock).deleteById(id);
        assertEquals(product,productService.deleteProductById(id));
    }
    //negativeCase
    @Test
    public void deleteProductByIdReturnNull(){
        Product product = givenProduct();
        when(productRepositoryMock.findById(product.getId())).thenReturn(Optional.empty());
        doNothing().when(productRepositoryMock).deleteById(id);
        assertThrows(ResourceNotFoundException.class,()->{
            productService.deleteProductById(id);
        },"Product not found with id :" +id);
    }
    private Product givenProduct() {
        Product product =Product.builder()
                .id(1)
                .productName("Milk Packet")
                .productCategory("Dairy")
                .productQuantity(10.0).
                productDescription("Packet Of Milk")
                .productCode("001")
                .productPrice(100.0)
                .build();
        return product;
    }
}
