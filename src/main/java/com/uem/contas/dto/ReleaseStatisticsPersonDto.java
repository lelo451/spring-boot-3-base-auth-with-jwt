package com.uem.contas.dto;

import com.uem.contas.model.Category;
import com.uem.contas.model.Person;
import com.uem.contas.model.ReleaseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ReleaseStatisticsPersonDto {

    private ReleaseType type;
    private Person person;
    private BigDecimal total;

}
