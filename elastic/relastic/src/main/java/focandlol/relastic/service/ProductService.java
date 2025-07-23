package focandlol.relastic.service;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NumberRangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import focandlol.relastic.domain.Product;
import focandlol.relastic.domain.ProductDocument;
import focandlol.relastic.dto.CreateProductRequestDto;
import focandlol.relastic.repository.ProductDocumentRepository;
import focandlol.relastic.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductDocumentRepository productDocumentRepository;
  private final ElasticsearchOperations elasticsearchOperations;

  public List<Product> getProducts(int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    return productRepository.findAll(pageable).getContent();
  }

  public List<String> getSuggestions(String query) {
    Query multiMatchQuery = MultiMatchQuery.of(m -> m
        .query(query)
        .type(TextQueryType.BoolPrefix)
        .fields("name.auto_complete", "name.auto_complete._2gram", "name.auto_complete._3gram")
    )._toQuery();

    NativeQuery nativeQuery = NativeQuery.builder()
        .withQuery(multiMatchQuery)
        .withPageable(PageRequest.of(0, 5)).build();

    SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(nativeQuery,
        ProductDocument.class);

    return searchHits.getSearchHits()
        .stream()
        .map(a -> a.getContent().getName())
        .collect(Collectors.toList());
  }

  public List<ProductDocument> searchProducts(String query, String category, double minPrice,
      double maxPrice, int page, int size) {
    Query multiMatchQuery = MultiMatchQuery.of(m -> m
        .query(query)
        .fields("name^3", "description", "category^3")
        .fuzziness("AUTO")
    )._toQuery();

    Query categoryFilter = TermQuery.of(t -> t
        .field("category.raw")
        .value(category))._toQuery();

    Query priceRangeFilter = NumberRangeQuery.of(r -> r
        .field("price")
        .gte(minPrice)
        .lte(maxPrice))._toRangeQuery()._toQuery();

    Query ratingRangeFilter = NumberRangeQuery.of(r -> r
        .field("rating")
        .gt(4.0))._toRangeQuery()._toQuery();

    Query boolQuery = BoolQuery.of(b -> b
        .must(multiMatchQuery)
        .filter(categoryFilter, priceRangeFilter)
        .should(ratingRangeFilter))._toQuery();

    HighlightParameters high = HighlightParameters.builder()
        .withPreTags("<b>")
        .withPostTags("</b>")
        .build();

    Highlight highlight = new Highlight(high, List.of(new HighlightField("name")));
    HighlightQuery highlightQuery = new HighlightQuery(highlight, ProductDocument.class);

    NativeQuery nativeQuery = NativeQuery.builder()
        .withQuery(boolQuery)
        .withHighlightQuery(highlightQuery)
        .withPageable(PageRequest.of(0, 5)).build();

    SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(nativeQuery,
        ProductDocument.class);

    return searchHits.getSearchHits()
        .stream()
        .map(a -> {
          ProductDocument content = a.getContent();
          content.setName(a.getHighlightField("name").get(0));
          return content;
        }).collect(Collectors.toList());
  }

  public Product createProduct(CreateProductRequestDto createProductRequestDto) {
    Product save = productRepository.save(
        Product.fromCreateProductRequestDto(createProductRequestDto));

    productDocumentRepository.save(ProductDocument.from(save));

    return save;
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);

    productDocumentRepository.deleteById(id.toString());
  }
}
