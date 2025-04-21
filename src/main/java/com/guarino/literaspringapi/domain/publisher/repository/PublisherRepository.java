package com.guarino.literaspringapi.domain.publisher.repository;

import com.guarino.literaspringapi.domain.publisher.entity.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    boolean existsByEmail(String email);

    boolean existsByTaxId(String taxId);

    boolean existsByWebsite(String website);

    boolean existsByTelephone(String telephone);
}
