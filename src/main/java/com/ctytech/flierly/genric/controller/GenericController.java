package com.ctytech.flierly.genric.controller;

import com.ctytech.flierly.genric.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{entity}")
public class GenericController<T, ID> {
    @Autowired
    private GenericService<T, ID> genericService;

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T savedEntity = genericService.save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        Optional<T> entity = genericService.findById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = genericService.findAll();
        return ResponseEntity.ok(entities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

