package com.BackendMiniProject.demo.mapper;

import com.BackendMiniProject.demo.dto.Productdto;
import com.BackendMiniProject.demo.entity.Product;
import com.BackendMiniProject.demo.entity.Product.ProductBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-31T10:03:38+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Ubuntu)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Productdto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Productdto productdto = new Productdto();

        productdto.setId( product.getId() );
        productdto.setProductName( product.getProductName() );
        productdto.setProductDescription( product.getProductDescription() );
        productdto.setProductCategory( product.getProductCategory() );
        productdto.setProductPrice( product.getProductPrice() );
        productdto.setProductQuantity( product.getProductQuantity() );
        productdto.setProductCode( product.getProductCode() );

        return productdto;
    }

    @Override
    public List<Productdto> toProductDtoList(List<Product> product) {
        if ( product == null ) {
            return null;
        }

        List<Productdto> list = new ArrayList<Productdto>( product.size() );
        for ( Product product1 : product ) {
            list.add( toProductDto( product1 ) );
        }

        return list;
    }

    @Override
    public Product toProduct(Productdto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        product.id( productDto.getId() );
        product.productName( productDto.getProductName() );
        product.productDescription( productDto.getProductDescription() );
        product.productCategory( productDto.getProductCategory() );
        product.productPrice( productDto.getProductPrice() );
        product.productQuantity( productDto.getProductQuantity() );
        product.productCode( productDto.getProductCode() );

        return product.build();
    }

    @Override
    public List<Product> toProductList(List<Productdto> productDto) {
        if ( productDto == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDto.size() );
        for ( Productdto productdto : productDto ) {
            list.add( toProduct( productdto ) );
        }

        return list;
    }
}
