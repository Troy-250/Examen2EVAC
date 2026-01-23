package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Cliente;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class ClienteRepository {
    private static final Logger logger = Logger.getLogger(ClienteRepository.class.getName());
    private final Path file;
    private final HashMap<String, Cliente> clientes;

    public ClienteRepository() {
        this.file = Path.of("data/clientes.json");
        this.clientes = new HashMap<>();
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Cliente> list = GsonFactory.getGson().fromJson(json, new TypeToken<List<Cliente>>() {}.getType());
            list.forEach(s -> clientes.put(s.getId(), s));
        } catch (IOException e) {
            System.err.println("Error cargando clientes: " + e.getMessage());
        }
    }

    public void save() {
        try {
            String json = GsonFactory.getGson().toJson(clientes.values());
            Files.writeString(file, json);
        } catch (IOException e) {
            System.err.println("Error guardando clientes: " + e.getMessage());
        }
    }

    public boolean create(Cliente cliente) {
        if (clientes.containsKey(cliente.getId())) return false;
        clientes.put(cliente.getId(), cliente);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        if (clientes.remove(id) != null) {
            save();
            return true;
        }
        return false;
    }

    public List<Cliente> findAll() {
        return new ArrayList<>(clientes.values());
    }

    public Optional<Cliente> findById(String id) {
        return Optional.ofNullable(clientes.get(id));
    }
}
