package com.uem.contas.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ReleaseFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateTo;

}
