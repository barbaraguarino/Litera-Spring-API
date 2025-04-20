package com.guarino.literaspringapi.domain.publisher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPublisher;
    private String name;
    private String foundationDate;
    private String description;
    private String email;
    private String website;
    private String telephone;
    private String taxId;

    public Publisher(String name,
                     String foundationDate,
                     String description,
                     String email,
                     String website,
                     String telephone,
                     String taxId) {
        this.name = name;
        this.foundationDate = foundationDate;
        this.description = description;
        this.email = email;
        this.website = website;
        this.telephone = telephone;
        this.taxId = taxId;
    }
}
