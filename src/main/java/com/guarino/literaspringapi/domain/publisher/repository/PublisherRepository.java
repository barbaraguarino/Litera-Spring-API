package com.guarino.literaspringapi.domain.publisher.repository;

import com.guarino.literaspringapi.domain.publisher.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    boolean existsByEmail(String email);

    boolean existsByTaxId(String taxId);

    boolean existsByWebsite(String website);

    boolean existsByTelephone(String telephone);
}
