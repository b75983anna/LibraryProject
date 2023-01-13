package org.company.demo.library.libraryproject.mappers.mapperinterface;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.models.Book;

public interface BookMapperInterface {

    BookDTO bookToDTO(Book book);

    Book dtoToBook(BookDTO bookDTO);


}
