package ru.maxima.rest_api.testapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.maxima.rest_api.testapi.dto.PersonDTO;
import ru.maxima.rest_api.testapi.models.Person;
import ru.maxima.rest_api.testapi.repositories.PeopleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, ModelMapper modelMapper) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
    }

    public void save(Person person) {
        enrich(person);
        peopleRepository.save(person);
    }
    private void enrich(Person person) {
        person.setCreated_at(LocalDateTime.now());
        person.setUpdate_at(LocalDateTime.now());
        person.setRemoved(false);

    }

    public List<PersonDTO> findAll(){
       return peopleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
               .stream()
               .map(this::convertToPersonDTO)
               .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id){
        return convertToPersonDTO(peopleRepository.findById(id).orElse(null));
    }

    public PersonDTO convertToPersonDTO(Person person) {
       return modelMapper.map(person, PersonDTO.class);
     }

     public void delete(Long id) {
        Person person = peopleRepository.findById(id).orElse(null);
        person.setRemoved(true);
        peopleRepository.save(person);
     }

     public PersonDTO update(Long id, PersonDTO personDTO){
        Person person = peopleRepository.findById(id).orElse(null);
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.setEmail(personDTO.getEmail());
        person.setCreated_at(LocalDateTime.now());
        peopleRepository.save(person);
         return modelMapper.map(person, PersonDTO.class);
     }
}
