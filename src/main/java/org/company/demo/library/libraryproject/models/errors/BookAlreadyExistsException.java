package org.company.demo.library.libraryproject.models.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BookAlreadyExistsException extends RuntimeException {

    final HttpStatus errorCode;
    final String message;

}
