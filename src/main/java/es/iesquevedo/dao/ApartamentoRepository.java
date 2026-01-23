package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Apartamento;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApartamentoRepository {
    private final Path file = Path.of("data", "Apartamentos.json");
    private final Type listType = new TypeToken<List<Apartamento>>(){}.getType();
    private List<Apartamento> apartamentos = new ArrayList<>();

    public ApartamentoRepository() {
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) {
                Files.createDirectories(file.getParent());
            }
            if (Files.notExists(file)) {
                Files.writeString(file, "[]");
            }
            String json = Files.readString(file);
            List<Apartamento> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) apartamentos = list;
        } catch (IOException e) {
            System.err.println("Error cargando coches: " + e.getMessage());
        }
    }

    private void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(apartamentos, listType));
        } catch (IOException e) {
            System.err.println("Error guardando Apartamentos: " + e.getMessage());
        }
    }

    public boolean create(Apartamento Apartamento) {
        // recargar estado antes de modificar
        load();
        if (findById(Apartamento.getId()).isPresent()) return false;
        apartamentos.add(Apartamento);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        // recargar
        load();
        boolean removed = apartamentos.removeIf(c -> c.getId().equals(id));
        if (removed) save();
        return removed;
    }

    public List<Apartamento> findAll() {
        load();
        return apartamentos.stream().collect(Collectors.toList());
    }

    public Optional<Apartamento> findById(String id) {
        load();
        return apartamentos.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void update(Apartamento updated) {
        load();
        for (int i = 0; i < apartamentos.size(); i++) {
            if (apartamentos.get(i).getId().equals(updated.getId())) {
                apartamentos.set(i, updated);
                save();
                return;
            }
        }
    }
}
