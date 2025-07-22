package focandlol.relastic.service;

import focandlol.relastic.domain.Product;
import focandlol.relastic.dto.CreateProductRequestDto;
import focandlol.relastic.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    return productRepository.findAll(pageable).getContent();
  }

  public Product createProduct(CreateProductRequestDto createProductRequestDto) {
    Product save = productRepository.save(
        Product.fromCreateProductRequestDto(createProductRequestDto));

    return save;
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }
}
