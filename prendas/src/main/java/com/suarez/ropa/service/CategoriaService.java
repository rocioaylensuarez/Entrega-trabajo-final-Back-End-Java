package com.suarez.ropa.service;

import com.suarez.ropa.exception.CategoriaNoEncontradaException;
import com.suarez.ropa.exception.CategoriaNombreInvalidoException;
import com.suarez.ropa.model.Categoria;
import com.suarez.ropa.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria guardar(Categoria c) {
        return repository.save(c);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("No se encontró una categoría con id " + id));
    }

    public Categoria actualizar(int id, Categoria datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new CategoriaNombreInvalidoException("El nombre de la categoría no puede estar vacío.");
        }
        Categoria c = obtenerPorId(id);
        c.setNombre(datos.getNombre());
        c.setDescripcion(datos.getDescripcion());
        return repository.save(c);
    }

    public void eliminar(int id) {
        Categoria c = obtenerPorId(id);
        repository.delete(c);
    }
}