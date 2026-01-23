package es.iesquevedo.service;

import es.iesquevedo.modelo.Cliente;
import es.iesquevedo.dao.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService() {
        this.repo = new ClienteRepository();
    }

    public boolean altaCliente(Cliente s) {
        if (s.getId() == null || s.getId().isBlank()) return false;
        if (s.getNombre() == null || s.getNombre().isBlank()) return false;
        return repo.create(s);
    }

    public boolean bajaCliente(String id) {
        return repo.deleteById(id);
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return repo.findById(id);
    }
}
