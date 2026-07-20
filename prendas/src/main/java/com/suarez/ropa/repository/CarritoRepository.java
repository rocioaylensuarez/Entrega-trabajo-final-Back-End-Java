package com.suarez.ropa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.suarez.ropa.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}