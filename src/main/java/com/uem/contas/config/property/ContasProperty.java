package com.uem.contas.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties("contas")
@Component
public class ContasProperty {

    private String permittedOrigin = "http://localhost:8080";

}
