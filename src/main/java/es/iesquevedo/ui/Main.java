package es.iesquevedo.ui;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ConsoleController controller = new ConsoleController(sc);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("--- Gestión de Apartamentos y Arrendamientos ---");
            System.out.println("1) Gestionar apartamentos");
            System.out.println("2) Gestionar clientes");
            System.out.println("3) Gestionar arrendamientos");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> controller.menuApartamentos();
                case "2" -> controller.menuClientes();
                case "3" -> controller.menuArrendamientos();
                case "0" -> running = false;
                default -> System.out.println("Opción no válida");
            }
        }
        System.out.println("Adiós");
    }
}
