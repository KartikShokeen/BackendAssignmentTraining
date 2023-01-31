package com.BackendMiniProject.demo.mapper;

import com.BackendMiniProject.demo.dto.Productdto;
import com.BackendMiniProject.demo.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    Productdto toProductDto(Product product);
    List<Productdto> toProductDtoList(List<Product> product);
    Product toProduct(Productdto productDto);
    List<Product> toProductList(List<Productdto> productDto);

}