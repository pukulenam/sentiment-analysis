package com.pukul6.api.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.pukul6.api.entities.dtos.CategoryDto;
import com.pukul6.api.services.CategoryService;
import com.pukul6.api.utilities.ApiValidation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = { "/api/v1/categories" })
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CategoryDto request, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ApiValidation.getErrorMessages(errors));
        }

        try {
            var category = categoryService.DtoToEntity(request);
            var saved = categoryService.save(category);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
