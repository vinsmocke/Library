package com.myapp.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class DeliveryInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 40, message = "First name should be between 2 and 40 characters")
    private String firstName;
    @Column(name = "surname")
    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 40, message = "Surname should be between 2 and 40 characters")
    private String surname;
    @NotEmpty(message = "Phone shouldn't be empty")
    @Column
    @Size(min = 10, max = 13, message = "Phone should be between 10 and 13 symbols")
    private String phone;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    @Size(max = 200, message = "Comment should be between 2 and 200 characters")
    private String comment;
}
