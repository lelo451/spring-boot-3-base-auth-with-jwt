package com.uem.contas.dto;

import com.uem.contas.model.Category;
import com.uem.contas.model.ReleaseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReleaseStatisticsDayDto {

    private ReleaseType type;
    private LocalDate day;
    private BigDecimal total;

}
