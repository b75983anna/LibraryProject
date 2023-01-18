package org.company.demo.library.libraryproject.controllers;


import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.models.Book;
import org.company.demo.library.libraryproject.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

   @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        try {
            List<BookDTO> books = bookService.getBooks();

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{bookCode}")
    public ResponseEntity<Optional<BookDTO>> getBook(@Valid @PathVariable Integer bookCode) {
        Optional<BookDTO> bookDTO = bookService.getBookByCode(bookCode);

        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("/book-create")
    public ResponseEntity<BookDTO> createNewBook(@Valid @RequestBody BookDTO bookDTO) {
        try {
            BookDTO bookSaved = bookService.addBook(bookDTO);

            if (bookSaved != null) {
                return new ResponseEntity<>(bookSaved, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping("/book/{bookCode}")
    public ResponseEntity<Book> deleteBook(@PathVariable @NotNull Integer bookCode) {
        try {
            bookService.deleteBook(bookCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books-by-author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable @NotNull String author) {
        try {
            List<BookDTO> books = bookService.getBooksByAuthor(author);
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/book/{bookCode}")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable @NotNull Integer bookCode) {
        try {
            BookDTO updatedBookDTO = bookService.updateBook(bookDTO, bookCode);
            if (updatedBookDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
