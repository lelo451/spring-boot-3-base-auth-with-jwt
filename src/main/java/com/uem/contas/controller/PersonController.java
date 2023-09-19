package com.uem.contas.controller;

import com.uem.contas.event.ResourceCreatedEvent;
import com.uem.contas.model.Person;
import com.uem.contas.repository.PersonRepository;
import com.uem.contas.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonRepository repository;
    private PersonService service;
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person,
                                         HttpServletResponse response) {
        Person savedPerson = service.save(person);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> searchById(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        Person savedPerson = service.update(id, person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active) {
        service.updateActiveProperty(id, active);
    }

    @GetMapping
    public Page<Person> search(@RequestParam(required = false, defaultValue = "") String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }

}
