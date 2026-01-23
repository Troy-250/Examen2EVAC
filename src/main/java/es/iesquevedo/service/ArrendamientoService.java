package es.iesquevedo.service;

import es.iesquevedo.modelo.Arrendamiento;
import es.iesquevedo.modelo.Apartamento;
import es.iesquevedo.modelo.Cliente;
import es.iesquevedo.dao.ArrendamientoRepository;
import es.iesquevedo.dao.ApartamentoRepository;
import es.iesquevedo.dao.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ArrendamientoService {
    private final ArrendamientoRepository arrendamientoRepo;
    private final ApartamentoRepository apartamentoRepo;
    private final ClienteRepository clienteRepo;

    public ArrendamientoService() {
        this.arrendamientoRepo = new ArrendamientoRepository();
        this.apartamentoRepo = new ApartamentoRepository();
        this.clienteRepo = new ClienteRepository();
    }

    public boolean crearArrendamiento(Arrendamiento a) {
        if (a.getId() == null || a.getId().isBlank()) return false;
        Optional<Apartamento> apartamento = apartamentoRepo.findById(a.getApartamentoId());
        if (apartamento.isEmpty()) return false;
        if (!apartamento.get().isDisponible()) return false;
        Optional<Cliente> cliente = clienteRepo.findById(a.getClienteId());
        if (cliente.isEmpty()) return false;
        Apartamento c = apartamento.get();
        c.setDisponible(false);
        apartamentoRepo.update(c);
        return arrendamientoRepo.create(a);
    }

    public boolean finalizarArrendamiento(String id) {
        Optional<Arrendamiento> a = arrendamientoRepo.findById(id);
        if (a.isEmpty()) return false;
        arrendamientoRepo.deleteById(id);
        Optional<Apartamento> c = apartamentoRepo.findById(a.get().getApartamentoId());
        c.ifPresent(co -> { co.setDisponible(true); apartamentoRepo.update(co); });
        return true;
    }

    public List<Arrendamiento> listar() {
        return arrendamientoRepo.findAll();
    }

    public Optional<Arrendamiento> buscarPorId(String id) {
        return arrendamientoRepo.findById(id);
    }
}
