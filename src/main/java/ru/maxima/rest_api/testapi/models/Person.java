package ru.maxima.rest_api.testapi.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of name should be min 2 symbols, max 50 symbols")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be min 0 year")
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "email should not to be empty")
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "update_at")
    private LocalDateTime update_at;

    @Column(name = "removed")
    private Boolean removed;


}