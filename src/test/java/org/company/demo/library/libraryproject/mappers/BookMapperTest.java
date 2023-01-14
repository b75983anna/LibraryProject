package org.company.demo.library.libraryproject.mappers;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.company.demo.library.libraryproject.TestUtil.book;
import static org.company.demo.library.libraryproject.TestUtil.bookDTO;
import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    public void setUp(){
        bookMapper = new BookMapper();
    }

    @Test
    void bookToDTOTest() {
        Book book = book();
        BookDTO bookDTO = bookMapper.bookToDTO(book);
        assertEquals(bookDTO.getBookCode(), book.getBookCode());
        assertEquals(bookDTO.getBookName(), book.getBookName());
        assertEquals(bookDTO.getAuthor(), book.getAuthor());
        assertTrue(bookDTO.getIsRead(), String.valueOf(true));
        assertEquals(bookDTO.getLocation(), book.getLocation());
    }

    @Test
    void DTOToBook() {
        BookDTO bookDTO = bookDTO();
        Book book = bookMapper.dtoToBook(bookDTO);
        assertEquals(book.getBookCode(), bookDTO.getBookCode());
        assertEquals(book.getBookName(),bookDTO.getBookName());
        assertTrue(bookDTO.getIsRead(), String.valueOf(true));
        assertEquals(book.getAuthor(),bookDTO.getAuthor());
        assertEquals(book.getLocation(),bookDTO.getLocation());
    }
}