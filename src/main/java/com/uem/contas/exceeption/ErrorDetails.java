package com.uem.contas.exceeption;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonSerialize(using = ErrorDetailsSerializer.class)
public class ErrorDetails {
    private Integer errorCode;
    private String errorMessage;
    private String referenceUrl;
}
