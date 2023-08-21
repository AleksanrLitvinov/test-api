package ru.maxima.rest_api.testapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.rest_api.testapi.models.Person;
import ru.maxima.rest_api.testapi.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public List<Person> findAll(){
       return peopleRepository.findAll();
    }

    public Optional<Person> findById(Long id){
        return peopleRepository.findById(id);
    }
}
