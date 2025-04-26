package com.guarino.literaspringapi.domain.author.repository;

import com.guarino.literaspringapi.domain.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    boolean existsByEmail(String email);
}
