package org.company.demo.lobrary.libraryProject.services.interfaces;

import org.company.demo.lobrary.libraryProject.DTO.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public BookDTO addBook(BookDTO book);

    public List<BookDTO> getBooks();
    public Optional<BookDTO> getBookByCode(Integer bookCode);

    public BookDTO updateBook(BookDTO book, Integer id);

    public void deleteBook(Integer id);

    public List<BookDTO> getBooksByAuthor(String name);


}
