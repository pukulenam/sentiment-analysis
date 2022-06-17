package com.pukul6.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.pukul6.api.entities.Category;
import com.pukul6.api.entities.dtos.CategoryDto;
import com.pukul6.api.respositories.CategoryRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements BaseService<Category, CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> save(Category entity) {
        var category = Optional.of(Optional.of(categoryRepository.save(entity))).orElse(Optional.empty());
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> update(Category entity) {
        return Optional.of(Optional.of(categoryRepository.save(entity))).orElse(Optional.empty());
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category DtoToEntity(CategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public List<Category> DtosToEntities(List<CategoryDto> dtos) {
        return dtos.stream().map((dto) -> this.DtoToEntity(dto)).collect(Collectors.toList());
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByNameEqualsIgnoreCase(name);
    }

}
