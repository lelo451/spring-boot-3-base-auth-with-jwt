package com.uem.contas.controller;

import com.uem.contas.event.ResourceCreatedEvent;
import com.uem.contas.model.Category;
import com.uem.contas.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category,
                                           HttpServletResponse response) {
        Category savedCategory = categoryRepository.save(category);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedCategory.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> searchById(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
