package org.company.demo.library.libraryproject.services.implementation;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.mappers.BookMapper;
import org.company.demo.library.libraryproject.models.Book;
import org.company.demo.library.libraryproject.models.constants.Constants;
import org.company.demo.library.libraryproject.models.errors.BookAlreadyExistsException;
import org.company.demo.library.libraryproject.models.errors.BookNotFoundException;
import org.company.demo.library.libraryproject.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.company.demo.library.libraryproject.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookServiceImpl.class})
class BookServiceTest {


    @Autowired
    BookServiceImpl bookService;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    BookMapper mapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addBookAlreadyExistsTest() {
        BookDTO bookDTO = bookDTO();

        when(bookRepository.existsByBookCode(anyInt())).thenReturn(true);
        assertThrows(BookAlreadyExistsException.class, () -> bookService.addBook(bookDTO));
        verify(bookRepository, times(1)).existsByBookCode(anyInt());
    }

    @Test
    void addBookTest() {
        Book book = book();
        BookDTO bookDTO = bookDTO();

        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.existsByBookCode(anyInt())).thenReturn(false);
        when(mapper.bookToDTO((Book) any())).thenReturn(bookDTO);
        when(mapper.dtoToBook((BookDTO) any())).thenReturn(book);
        assertSame(bookDTO, bookService.addBook(new BookDTO()));
        verify(bookRepository).save((Book) any());
        verify(mapper).bookToDTO((Book) any());
        verify(mapper).dtoToBook((BookDTO) any());
    }

    @Test
    void getBooksTest() {
        List<Book> bookList = bookList();
        BookDTO bookDTO = bookDTO();

        assertTrue(bookService.getBooks().isEmpty());
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());
        when(bookRepository.findAll()).thenReturn(bookList);
        when(mapper.bookToDTO((Book) any())).thenReturn(bookDTO);
        verify(bookRepository).findAll();
    }

    @Test
    void getBookByCode() {
        Book book = book();
        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertTrue(bookService.getBookByCode(100).isPresent());
        verify(bookRepository, times(1)).getBookByCode(anyInt());
        verify(mapper).bookToDTO((Book) any());
    }

    @Test
    void getBookByCode_BookAlreadyExists() {
        Book book = book();
        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenThrow(new BookAlreadyExistsException(HttpStatus.CONTINUE, Constants.CODE_ALREADY_EXISTS));
        assertThrows(BookAlreadyExistsException.class, () -> bookService.getBookByCode(100));
        verify(bookRepository, times(1)).getBookByCode(anyInt());
        verify(mapper).bookToDTO((Book) any());
    }

    @Test
    void getBookByCode_NotFound() {

        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertThrows(BookNotFoundException.class, () -> bookService.getBookByCode(100));
        verify(bookRepository, times(1)).getBookByCode(anyInt());
    }

    @Test
    void getBookByCode_InvalidCodeNotFound() {
        Book book = book();
        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertThrows(BookNotFoundException.class, () -> bookService.getBookByCode(0));

    }


    @Test
    void updateBookTest() {

        Book book = book();
        BookDTO bookDTO = bookDTO();

        Optional<Book> result = Optional.of(book);
        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenReturn(bookDTO);
        assertSame(bookDTO, bookService.updateBook(new BookDTO(), 1));
        verify(bookRepository).save((Book) any());
        verify(bookRepository).getBookByCode(anyInt());
        verify(mapper).bookToDTO((Book) any());
    }

    @Test
    void updateBook_NoBookFoundTest() {
        Book book = book();
        BookDTO bookDTO = bookDTO();

        when(bookRepository.save((Book) any())).thenReturn(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookDTO, 1));
        verify(bookRepository).getBookByCode(anyInt());
    }

    @Test
    void deleteBookTest() {

        Book book = book();

        doNothing().when(bookRepository).delete((Book) any());
        Optional<Book> result = Optional.of(book);
        when(bookRepository.getBookByCode(anyInt())).thenReturn(result);
        bookService.deleteBook(1);
        verify(bookRepository).getBookByCode(anyInt());
        verify(bookRepository).delete((Book) any());
        assertTrue(bookService.getBooks().isEmpty());
    }

    @Test
    void deleteBook_BookNotFoundTest() {
        doNothing().when(bookRepository).delete((Book) any());
        when(bookRepository.getBookByCode(anyInt())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1));
        verify(bookRepository).getBookByCode(anyInt());
    }

    @Test
        //????
    void getBooksByNameTest() {
        when(bookRepository.getBooksByAuthor(anyString())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(bookService.getBooksByAuthor("Name").isEmpty());
        verify(bookRepository).getBooksByAuthor(anyString());

    }

    @Test
    void getBooksByName_OneBookTest() {

        Book book = book();

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Optional<List<Book>> result = Optional.of(bookList);
        when(bookRepository.getBooksByAuthor(anyString())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertEquals(1, bookService.getBooksByAuthor("Name1").size());
        verify(bookRepository).getBooksByAuthor(anyString());
        verify(mapper).bookToDTO((Book) any());

    }

    @Test
    void getBooksByName_TwoBookTest() {

        Book book = book();
        Book anotherBook = book();
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);

        bookList.add(anotherBook);
        Optional<List<Book>> result = Optional.of(bookList);
        when(bookRepository.getBooksByAuthor(anyString())).thenReturn(result);
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertEquals(2, bookService.getBooksByAuthor("Name1").size());
        verify(bookRepository).getBooksByAuthor(anyString());
        verify(mapper, atLeast(1)).bookToDTO((Book) any());

    }

    @Test
    void getBooksByName_BookNotFoundTest() {

        when(bookRepository.getBooksByAuthor(anyString())).thenReturn(Optional.empty());
        when(mapper.bookToDTO((Book) any())).thenReturn(new BookDTO());
        assertThrows(BookNotFoundException.class, () -> bookService.getBooksByAuthor("Name1"));
        verify(bookRepository).getBooksByAuthor(anyString());
    }

}