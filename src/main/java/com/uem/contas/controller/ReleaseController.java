package com.uem.contas.controller;

import com.uem.contas.dto.ReleaseStatisticsCategoryDto;
import com.uem.contas.dto.ReleaseStatisticsDayDto;
import com.uem.contas.event.ResourceCreatedEvent;
import com.uem.contas.model.Release;
import com.uem.contas.repository.ReleaseRepository;
import com.uem.contas.repository.filter.ReleaseFilter;
import com.uem.contas.repository.projection.ReleaseSummary;
import com.uem.contas.service.ReleaseService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("releases")
public class ReleaseController {

    private ReleaseRepository repository;
    private ReleaseService service;
    private ApplicationEventPublisher publisher;

    @GetMapping("/statics/by-day")
    public List<ReleaseStatisticsDayDto> byDay() {
        return repository.byDay(LocalDate.now());
    }

    @GetMapping("/statics/by-category")
    public List<ReleaseStatisticsCategoryDto> byCategory() {
        return repository.byCategory(LocalDate.now());
    }

    @GetMapping
    public Page<Release> search(ReleaseFilter filter, Pageable pageable) {
        return repository.filter(filter, pageable);
    }

    @GetMapping(params = "summary")
    public Page<ReleaseSummary> summary(ReleaseFilter filter, Pageable pageable) {
        return repository.summarize(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> searchById(@PathVariable Long id) {
        Optional<Release> release = repository.findById(id);
        return release.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Release> create(@Valid @RequestBody Release release, HttpServletResponse response) {
        Release savedRelease = service.save(release);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedRelease.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRelease);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Release> update(@PathVariable Long id, @Valid @RequestBody Release release) {
        try {
            Release savedRelease = service.update(id, release);
            return ResponseEntity.ok(savedRelease);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
