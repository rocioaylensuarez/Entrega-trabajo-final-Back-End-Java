package com.suarez.ropa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suarez.ropa.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
} 