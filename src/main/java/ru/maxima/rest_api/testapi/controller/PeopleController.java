package ru.maxima.rest_api.testapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maxima.rest_api.testapi.exception.PersonErrorResponse;
import ru.maxima.rest_api.testapi.exception.PersonNotSuccessCreatedException;
import ru.maxima.rest_api.testapi.models.Person;
import ru.maxima.rest_api.testapi.service.PeopleService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from HelloController";
    }


    @GetMapping()
    public List<Person> getAllPeople() {
      return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
      return peopleService.findById(id);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
            throw new PersonNotSuccessCreatedException(builder.toString());
        }
        peopleService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotSuccessCreatedException ex) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



















}
