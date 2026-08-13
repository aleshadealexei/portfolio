package org.example.portfolio.controller;

import org.example.portfolio.entity.Client;
import org.example.portfolio.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    // Создать клиента
    @PostMapping
    public Client create(@RequestBody Client client) {
        return repository.save(client);
    }

    @GetMapping
    public List<Client> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }


}