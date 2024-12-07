package ru.t1.product.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.t1.product.model.NewProductDto;
import ru.t1.product.model.Product;
import ru.t1.product.model.User;
import t1.dto.module.dto.ProductDto;

import java.util.List;

/**
 * @author YKozlova
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "userId", source = "user.id")
    ProductDto toProductDto(Product product);

    @Mapping(target = "user", source = "user")
    Product toProduct(NewProductDto newProductDto, User user);

    List<ProductDto> convertProductListToProductDtoList(List<Product> list);
}
