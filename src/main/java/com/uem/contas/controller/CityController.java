package com.uem.contas.controller;

import com.uem.contas.model.City;
import com.uem.contas.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {

    private CityRepository repository;

    @GetMapping
    public List<City> search(@RequestParam Long state_id) {
        return repository.findByState_Id(state_id);
    }

}
