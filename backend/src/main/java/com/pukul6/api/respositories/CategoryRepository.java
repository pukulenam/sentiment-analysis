package com.pukul6.api.respositories;

import java.util.Optional;

import com.pukul6.api.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    public Optional<Category> findByNameEqualsIgnoreCase(String name);
}
