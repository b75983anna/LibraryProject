package org.company.demo.library.libraryproject;

import org.company.demo.library.libraryproject.dto.BookDTO;
import org.company.demo.library.libraryproject.models.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtil {

    public static List<Book> bookList() {
        List<Book> testList = new ArrayList<>();
        Date date = new Date();
        testList.add(new Book(1, 100, "Name1", "Author1", true, "shelf1", date));
        testList.add(new Book(2, 200, "Name2", "Author2", false, "shelf2", date));
        return testList;
    }

    static Date date = new Date();

    public static Book book() {
        return new Book(1, 100, "Name1", "Author1", true, "shelf1", date);

    }


    public static BookDTO bookDTO() {
        return new BookDTO(100, "Name1", "Author1", "shelf2", true);

    }

    public static List<BookDTO> dtoList() {
        List<BookDTO> testListDto = new ArrayList<>();
        Date date1 = new Date();
        testListDto.add(new BookDTO(100, "Name1", "Author1", "shelf1", true));
        testListDto.add(new BookDTO(100, "Name1", "Author1", "shelf1", true));
        return testListDto;
    }
}
