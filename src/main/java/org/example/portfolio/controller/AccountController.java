package org.example.portfolio.controller;

import jakarta.validation.Valid;
import org.example.portfolio.dto.AccountRequest;
import org.example.portfolio.entity.Account;
import org.example.portfolio.entity.Client;
import org.example.portfolio.repository.AccountRepository;
import org.example.portfolio.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest request) {
        Optional<Client> clientOptional =
                clientRepository.findById(request.clientId());

        if (clientOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Клиент не найден"
                    ));
        }

        Account account = new Account();
        account.setAccountNumber(request.accountNumber());
        account.setBalance(request.balance());
        account.setClient(clientOptional.get());

        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "error", "Счет с таким номером уже существует"
                    ));
        }

        return ResponseEntity.ok(accountRepository.save(account));
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getAccountById(@PathVariable Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Счет не найден"
                    ));
        }

        return ResponseEntity.ok(accountOptional.get());
    }
}