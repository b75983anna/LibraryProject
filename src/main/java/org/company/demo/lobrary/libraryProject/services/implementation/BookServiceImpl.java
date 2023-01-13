package org.company.demo.lobrary.libraryProject.services.implementation;

import org.company.demo.lobrary.libraryProject.DTO.BookDTO;
import org.company.demo.lobrary.libraryProject.mappers.BookMapper;
import org.company.demo.lobrary.libraryProject.models.Book;
import org.company.demo.lobrary.libraryProject.models.constants.Constants;
import org.company.demo.lobrary.libraryProject.models.errors.BookAlreadyExistsException;
import org.company.demo.lobrary.libraryProject.repositories.BookRepository;
import org.company.demo.lobrary.libraryProject.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookMapper mapper;


    @Override
    public BookDTO addBook(BookDTO book) {

        if (bookRepository.existsByBookCode(book.getBookCode())) {
            throw new BookAlreadyExistsException(HttpStatus.CONFLICT, Constants.CODE_ALREADY_EXISTS);
        }
        Book bookToSave = mapper.DTOToBook(book);
        Date date = new Date();
        bookToSave.setAddedOn(date);
        bookRepository.save(bookToSave);
        return mapper.bookToDTO(bookToSave);

    }

    @Override
    public List<BookDTO> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(mapper::bookToDTO)
                .collect(Collectors.toList());

    }
}
