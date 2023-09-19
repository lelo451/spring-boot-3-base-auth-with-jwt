package com.uem.contas.repository.projection;

import com.uem.contas.model.ReleaseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReleaseSummary {

    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate dayOfPayment;
    private BigDecimal value;
    private ReleaseType type;
    private String category;
    private String person;

}
