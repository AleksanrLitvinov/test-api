package ru.maxima.rest_api.testapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class PersonDTO {

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of name should be min 2 symbols, max 50 symbols")
    @Column(name = "name")
    public String name;

    @Min(value = 0, message = "Age should be min 0 year")
    @Column(name = "age")
    public int age;

    @NotEmpty(message = "email should not to be empty")
    @Column(name = "email")
    public String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonDTO() {
    }

    public PersonDTO(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
