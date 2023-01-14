package org.company.demo.library.libraryproject.services.interfaces;

import org.company.demo.library.libraryproject.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDTO addBook(BookDTO book);

    List<BookDTO> getBooks();

    Optional<BookDTO> getBookByCode(Integer bookCode);

    BookDTO updateBook(BookDTO book, Integer id);

    void deleteBook(Integer id);

    List<BookDTO> getBooksByAuthor(String name);


}
