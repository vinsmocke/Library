package com.myapp.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 150, message = "Book name should be between 2 and 150 characters")
    private String name;
    @NotEmpty(message = "Author name shouldn't be empty")
    @Size(min = 2, max = 70, message = "Book name should be between 2 and 70 characters")
    private String author;
    @NotEmpty(message = "Genre shouldn't be empty")
    @Size(min = 3, max = 100, message = "Genre should be between 3 and 100 characters")
    private String genre;
    @NotEmpty(message = "Content shouldn't be empty")
    @Size(min = 200, max = 700, message = "Content should be between 3 and 700 characters")
    private String content;
    @Digits(integer = 4, fraction = 0, message = "The field must contain no more than 4 characters")
    @Column(name = "year_of_publication")
    private int yearOfPublication;
    private double price;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", content='" + content + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", price=" + price +
                '}';
    }
}
