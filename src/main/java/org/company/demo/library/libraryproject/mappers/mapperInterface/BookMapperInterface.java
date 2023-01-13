package org.company.demo.library.libraryproject.mappers.mapperInterface;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.models.Book;

public interface BookMapperInterface {

    BookDTO bookToDTO(Book book);

    Book DTOToBook(BookDTO bookDTO);


}
