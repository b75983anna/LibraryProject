package org.company.demo.lobrary.libraryProject.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BookDTO {
    @NotNull
    private Integer bookCode;

    private String bookName;

    private String author;

    private String location;

    private Boolean isRead;

}
