package focandlol.elastic.service;


import focandlol.elastic.domain.Product;
import focandlol.elastic.domain.ProductDocument;
import focandlol.elastic.dto.CreateProductRequestDto;
import focandlol.elastic.repository.ProductDocumentRepository;
import focandlol.elastic.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductDocumentRepository productDocumentRepository;

  public List<Product> getProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    return productRepository.findAll(pageable).getContent();
  }

  public Product createProduct(CreateProductRequestDto createProductRequestDto) {
    Product save = productRepository.save(
        Product.fromCreateProductRequestDto(createProductRequestDto));

    productDocumentRepository.save(ProductDocument.fromEntity(save));

    return save;
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);

    productDocumentRepository.deleteById(id.toString());
  }
}
