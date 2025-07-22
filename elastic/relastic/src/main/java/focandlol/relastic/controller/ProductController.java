package focandlol.relastic.controller;

import focandlol.relastic.domain.Product;
import focandlol.relastic.dto.CreateProductRequestDto;
import focandlol.relastic.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping()
  public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Product> products = productService.getProducts(page, size);
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
