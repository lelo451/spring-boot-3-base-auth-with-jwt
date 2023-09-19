package com.uem.contas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReleaseType {

    REVENUE("Revenue"),
    EXPENSE("Expense");

    private final String description;

}
