package org.company.demo.library.libraryproject;

import org.company.demo.library.libraryproject.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LibraryProjectTest {
    @Autowired
    BookController bookController;
    @Test
    void contextLoads() {  assertThat(bookController).isNotNull();}

}