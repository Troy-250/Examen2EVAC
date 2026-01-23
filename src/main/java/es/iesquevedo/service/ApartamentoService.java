package es.iesquevedo.service;

import es.iesquevedo.modelo.Apartamento;
import es.iesquevedo.dao.ApartamentoRepository;

import java.util.List;
import java.util.Optional;

public class ApartamentoService {
    private final ApartamentoRepository repo;

    public ApartamentoService() {
        this.repo = new ApartamentoRepository();
    }

    public boolean altaApartamento(Apartamento apartamento) {
        if (apartamento.getId() == null || apartamento.getId().isBlank()) return false;
        if (apartamento.getPrecio() == null || apartamento.getPrecio().isBlank()) return false;
        return repo.create(apartamento);
    }

    public boolean bajaApartamento(String id) {
        return repo.deleteById(id);
    }

    public List<Apartamento> listar() {
        return repo.findAll();
    }

    public Optional<Apartamento> buscarPorId(String id) {
        return repo.findById(id);
    }

    public void actualizar(Apartamento apartamento) {
        repo.update(apartamento);
    }
}
