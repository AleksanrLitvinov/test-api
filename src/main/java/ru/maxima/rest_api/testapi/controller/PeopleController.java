package ru.maxima.rest_api.testapi.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maxima.rest_api.testapi.dto.PersonDTO;
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
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from HelloController";
    }


    @GetMapping()
    public List<PersonDTO> getAllPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return peopleService.findById(id);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
            throw new PersonNotSuccessCreatedException(builder.toString());
        }
        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotSuccessCreatedException ex) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        peopleService.delete(id);
    }

    @GetMapping ("/update/{id}")
    public PersonDTO update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return peopleService.update(id, personDTO);
    }

}














