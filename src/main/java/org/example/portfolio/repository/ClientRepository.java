package org.example.portfolio.repository;

import org.example.portfolio.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByInn(String inn);
    boolean existsBySnils(String snils);
}