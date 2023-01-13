package org.company.demo.lobrary.libraryProject.services.interfaces;

import org.company.demo.lobrary.libraryProject.DTO.BookDTO;

import java.util.List;

public interface BookService {

    public BookDTO addBook(BookDTO book);

    public List<BookDTO> getBooks();
}
