package com.guarino.literaspringapi.domain.author.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAuthor;
    private String name;
    private String surname;
    private String pseudonym;
    private String email;
    private String birthDate;
    private String deathDate;
    private String website;
    private String description;
    private String imageUrl;
    private String nationality;

    public Author(String name,
                  String surname,
                  String pseudonym,
                  String email,
                  String birthDate,
                  String deathDate,
                  String website,
                  String description) {
        this.name = name;
        this.surname = surname;
        this.pseudonym = pseudonym;
        this.email = email;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.website = website;
        this.description = description;
        this.nationality = "BRASILEIRO";
    }
}
