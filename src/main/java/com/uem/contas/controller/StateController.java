package com.uem.contas.controller;

import com.uem.contas.model.City;
import com.uem.contas.model.State;
import com.uem.contas.repository.CityRepository;
import com.uem.contas.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {

    private StateRepository repository;

    @GetMapping
    public List<State> search() {
        return repository.findAll();
    }

}
