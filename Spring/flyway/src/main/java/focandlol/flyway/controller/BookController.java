package focandlol.flyway.controller;

import focandlol.flyway.model.Book;
import focandlol.flyway.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;

  @GetMapping("/create")
  public Book create(){
    Book book = new Book(1L, "Book 1", "Author 1");
    return bookRepository.save(book);
  }

}
