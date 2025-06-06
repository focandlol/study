package focandlol.elastic.controller;

import focandlol.elastic.domain.Product;
import focandlol.elastic.domain.ProductDocument;
import focandlol.elastic.dto.CreateProductRequestDto;
import focandlol.elastic.service.ProductService;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping()
  public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Product> products = productService.getProducts(page, size);
    return ResponseEntity.ok(products);
  }

  @GetMapping("/auto_complete")
  public ResponseEntity<List<String>> getSuggestions(@RequestParam String query) {
    return ResponseEntity.ok(productService.getSuggestions(query));
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductDocument>> searchProducts(
      @RequestParam String query,
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "0") double minPrice,
      @RequestParam(defaultValue = "100000000") double maxPrice,
      Pageable pageable) {

    List<ProductDocument> products = productService.searchProducts(query, category, minPrice,
        maxPrice, pageable);

    return ResponseEntity.ok(products);
  }


  @PostMapping()
  public ResponseEntity<Product> createProduct(
      @RequestBody CreateProductRequestDto createProductRequestDto) {
    Product product = productService.createProduct(createProductRequestDto);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
