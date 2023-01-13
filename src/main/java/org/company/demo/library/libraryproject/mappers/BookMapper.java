package org.company.demo.library.libraryproject.mappers;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.mappers.mapperinterface.BookMapperInterface;
import org.company.demo.library.libraryproject.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper implements BookMapperInterface {

    public BookDTO bookToDTO(Book book)  {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookCode(book.getBookCode());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsRead(book.getIsRead());
        bookDTO.setLocation(book.getLocation());
        return bookDTO;
    }


    public Book dtoToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setBookCode(bookDTO.getBookCode());
        book.setBookName(bookDTO.getBookName());
        book.setIsRead(bookDTO.getIsRead());
        book.setAuthor(bookDTO.getAuthor());
        book.setLocation(bookDTO.getLocation());
        return book;
    }
}
