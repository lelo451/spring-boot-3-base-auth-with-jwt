package com.uem.contas.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    private String publicPlace;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
