package com.uem.contas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    @JsonProperty("access_token")
    private String token;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
