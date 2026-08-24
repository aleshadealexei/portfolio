package org.example.portfolio.controller;

import jakarta.validation.Valid;
import org.example.portfolio.dto.RegionRequest;
import org.example.portfolio.entity.Region;
import org.example.portfolio.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {
    @Autowired
    private RegionRepository repository;

    @PostMapping
    public ResponseEntity<Region> create(@Valid @RequestBody RegionRequest request) {
        Region region = new Region();

        region.setName(request.name());
        region.setType(request.type());
        region.setFederalDistrict(request.federalDistrict());

        return ResponseEntity.ok(repository.save(region));
    }

    @GetMapping
    public List<Region> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Region> regionOptional = repository.findById(id);

        if (regionOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Регион не найден"
                    ));
        }

        return ResponseEntity.ok(regionOptional.get());
    }
}