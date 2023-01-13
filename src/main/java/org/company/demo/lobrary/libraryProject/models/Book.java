package org.company.demo.lobrary.libraryProject.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_details")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="book_code")
    private Integer bookCode;

    @Column(name = "book_name")
    private String bookName;

    @CreationTimestamp
    @Column(name = "added_on", nullable = false, updatable = false)
    private Date addedOn;

    @Column(name = "author_of_book")
    private String author;

    @Column(name="is_read")
    private Boolean isRead;

    @Column(name="location")
    private String location;

}
