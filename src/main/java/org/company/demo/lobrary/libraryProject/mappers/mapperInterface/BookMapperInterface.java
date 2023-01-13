package org.company.demo.lobrary.libraryProject.mappers.mapperInterface;

import org.company.demo.lobrary.libraryProject.DTO.BookDTO;
import org.company.demo.lobrary.libraryProject.models.Book;

public interface BookMapperInterface {

    BookDTO bookToDTO(Book book);

    Book DTOToBook(BookDTO bookDTO);


}
