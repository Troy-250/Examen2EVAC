package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Arrendamiento;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrendamientoRepository {
    private final Path file;
    private final Type listType = new TypeToken<List<Arrendamiento>>(){}.getType();
    private List<Arrendamiento> arrendamientos = new ArrayList<>();

    public ArrendamientoRepository() {
        this.file = Path.of("data", "arrendamientos.json");
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Arrendamiento> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) arrendamientos = list;
        } catch (IOException e) {
            System.err.println("Error cargando arrendamientos: " + e.getMessage());
        }
    }

    private void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(arrendamientos, listType));
        } catch (IOException e) {
            System.err.println("Error guardando arrendamientos: " + e.getMessage());
        }
    }

    public boolean create(Arrendamiento arrendamiento) {
        load();
        if (findById(arrendamiento.getId()).isPresent()) return false;
        arrendamientos.add(arrendamiento);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        load();
        boolean removed = arrendamientos.removeIf(a -> a.getId().equals(id));
        if (removed) save();
        return removed;
    }

    public List<Arrendamiento> findAll() {
        load();
        return arrendamientos.stream().collect(Collectors.toList());
    }

    public Optional<Arrendamiento> findById(String id) {
        load();
        return arrendamientos.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}
