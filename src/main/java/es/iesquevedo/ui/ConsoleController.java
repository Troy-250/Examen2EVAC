package es.iesquevedo.ui;

import es.iesquevedo.modelo.Arrendamiento;
import es.iesquevedo.modelo.Apartamento;
import es.iesquevedo.modelo.Cliente;
import es.iesquevedo.service.ArrendamientoService;
import es.iesquevedo.service.ApartamentoService;
import es.iesquevedo.service.ClienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleController {
    private final Scanner sc;
    private final ApartamentoService apartamentoService;
    private final ClienteService clienteService;
    private final ArrendamientoService arrendamientoService;

    public ConsoleController(Scanner sc) {
        this.sc = sc;
        this.apartamentoService = new ApartamentoService();
        this.clienteService = new ClienteService();
        this.arrendamientoService = new ArrendamientoService();
    }

    // Menús delegados desde Main
    public void menuApartamentos() {
        System.out.println("-- Apartamentos --");
        System.out.println("1) Alta apartamento");
        System.out.println("2) Baja apartamento");
        System.out.println("3) Listar apartamentos");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaApartamento();
            case "2" -> bajaApartamento();
            case "3" -> listarApartamentos();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaApartamento() {
        System.out.print("Título: ");
        String direccion = sc.nextLine().trim();
        System.out.print("propietario: ");
        String propietario = sc.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Apartamento l = new Apartamento(id, direccion, propietario, isbn);
        if (apartamentoService.altaApartamento(l)) System.out.println("Apartamento dado de alta con id=" + id);
        else System.out.println("No se pudo crear el apartamento (id duplicado o datos inválidos)");
    }

    public void bajaApartamento() {
        System.out.print("Id apartamento a eliminar: ");
        String id = sc.nextLine().trim();
        if (apartamentoService.bajaApartamento(id)) System.out.println("Apartamento eliminado");
        else System.out.println("No existe apartamento con ese id");
    }

    public void listarApartamentos() {
        List<Apartamento> ls = apartamentoService.listar();
        if (ls.isEmpty()) System.out.println("No hay apartamentos");
        else ls.forEach(System.out::println);
    }

    public void menuClientes() {
        System.out.println("-- Clientes --");
        System.out.println("1) Alta cliente");
        System.out.println("2) Baja cliente");
        System.out.println("3) Listar clientes");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaCliente();
            case "2" -> bajaCliente();
            case "3" -> listarClientes();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaCliente() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Documento (DNI/email): ");
        String doc = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Cliente s = new Cliente(id, nombre, doc);
        if (clienteService.altaCliente(s)) System.out.println("Cliente creado con id=" + id);
        else System.out.println("No se pudo crear el cliente (id duplicado o datos inválidos)");
    }

    public void bajaCliente() {
        System.out.print("Id cliente a eliminar: ");
        String id = sc.nextLine().trim();
        if (clienteService.bajaCliente(id)) System.out.println("Cliente eliminado");
        else System.out.println("No existe cliente con ese id");
    }

    public void listarClientes() {
        List<Cliente> ls = clienteService.listar();
        if (ls.isEmpty()) System.out.println("No hay clientes");
        else ls.forEach(System.out::println);
    }

    public void menuArrendamientos() {
        System.out.println("-- Arrendamientos --");
        System.out.println("1) Crear arrendamiento");
        System.out.println("2) Finalizar arrendamiento");
        System.out.println("3) Listar arrendamientos");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> crearArrendamiento();
            case "2" -> finalizarArrendamiento();
            case "3" -> listarArrendamientos();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void crearArrendamiento() {
        System.out.print("Id apartamento: ");
        String apartamentoId = sc.nextLine().trim();
        System.out.print("Id cliente: ");
        String clienteId = sc.nextLine().trim();
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        String fi = sc.nextLine().trim();
        System.out.print("Fecha fin (yyyy-MM-dd): ");
        String ff = sc.nextLine().trim();
        try {
            LocalDate inicio = LocalDate.parse(fi);
            LocalDate fin = LocalDate.parse(ff);
            if (fin.isBefore(inicio)) { System.out.println("Fecha fin anterior a inicio"); return; }
            String id = UUID.randomUUID().toString();
            Arrendamiento p = new Arrendamiento(id, apartamentoId, clienteId, inicio, fin);
            if (arrendamientoService.crearArrendamiento(p)) System.out.println("Arrendamiento creado con id=" + id);
            else System.out.println("No se pudo crear el arrendamiento (apartamento no existe/disponible o cliente no existe)");
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido, usa yyyy-MM-dd");
        }
    }

    public void finalizarArrendamiento() {
        System.out.print("Id arrendamiento a finalizar: ");
        String id = sc.nextLine().trim();
        if (arrendamientoService.finalizarArrendamiento(id)) System.out.println("Arrendamiento finalizado");
        else System.out.println("No existe arrendamiento con ese id");
    }

    public void listarArrendamientos() {
        List<Arrendamiento> ls = arrendamientoService.listar();
        if (ls.isEmpty()) System.out.println("No hay arrendamientos");
        else ls.forEach(System.out::println);
    }
}
