package org.example.portfolio.controller;

import jakarta.validation.Valid;
import org.example.portfolio.dto.AmlStatusRequest;
import org.example.portfolio.dto.ClientRequest;
import org.example.portfolio.entity.Client;
import org.example.portfolio.entity.Region;
import org.example.portfolio.repository.ClientRepository;
import org.example.portfolio.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RegionRepository regionRepository;

    // Создать клиента
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClientRequest request) {
        Optional<Region> regionOptional = regionRepository.findById(request.regionId());

        if (regionOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Регион не найден"
                    ));
        }

        Client client = new Client();

        client.setLastName(request.lastName());
        client.setFirstName(request.firstName());
        client.setPatronymic(request.patronymic());
        client.setInn(request.inn());
        client.setSnils(request.snils());
        client.setPhone(request.phone());
        client.setRegion(regionOptional.get());

        return ResponseEntity.ok(clientRepository.save(client));
    }

    @GetMapping
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Клиент не найден"
                    ));
        }

        return ResponseEntity.ok(clientOptional.get());
    }

    @PatchMapping("/{id}/aml-status")
    public ResponseEntity<?> updateAmlStatus(
            @PathVariable Long id,
            @RequestBody AmlStatusRequest request
    ) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Клиент не найден"
                    ));
        }

        Client client = clientOptional.get();

        client.setAmlStatus(request.amlStatus());
        clientRepository.save(client);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/aml-check-at")
    public ResponseEntity<?> markAmlCheckAttempt(
            @PathVariable Long id
    ) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Клиент не найден"
                    ));
        }

        Client client = clientOptional.get();
        client.setLastAmlCheck(LocalDateTime.now());
        clientRepository.save(client);

        return ResponseEntity.noContent().build();
    }
}