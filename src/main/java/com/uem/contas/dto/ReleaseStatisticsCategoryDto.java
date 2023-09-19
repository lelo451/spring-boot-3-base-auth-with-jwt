package com.uem.contas.dto;

import com.uem.contas.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ReleaseStatisticsCategoryDto {

    private Category category;
    private BigDecimal total;

}
