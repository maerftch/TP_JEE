package com.cine.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    private int codePayement;
    private boolean reserve;
    @ManyToOne
    private Place place;
    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Projection projection;
}
