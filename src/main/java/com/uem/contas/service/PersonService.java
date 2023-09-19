package com.uem.contas.service;

import com.uem.contas.model.Person;
import com.uem.contas.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public Person save(Person person) {
        person.getContacts().forEach(c -> c.setPerson(person));
        return repository.save(person);
    }

    public Person update(Long id, Person person) {
        Person savedPerson = searchById(id);

        savedPerson.getContacts().clear();
        savedPerson.getContacts().addAll(person.getContacts());
        savedPerson.getContacts().forEach(c -> c.setPerson(savedPerson));

        BeanUtils.copyProperties(person, savedPerson, "id", "contacts");
        return repository.save(savedPerson);
    }

    public void updateActiveProperty(Long id, Boolean active) {
        Person person = searchById(id);
        person.setActive(active);
        repository.save(person);
    }

    public Person searchById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

}
