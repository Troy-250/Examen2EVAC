package es.iesquevedo.modelo;

import java.util.Objects;

public class Apartamento {
    private String id;
    private String direccion;
    private String propietario;
    private String precio;
    private boolean disponible = true;

    public Apartamento() {
    }

    public Apartamento(String id, String direccion, String propietario, String precio) {
        this.id = id;
        this.direccion = direccion;
        this.propietario = propietario;
        this.precio = precio;
        this.disponible = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Apartamento{" +
                "id='" + id + '\'' +
                ", direccion='" + direccion + '\'' +
                ", propietario='" + propietario + '\'' +
                ", precio='" + precio + '\'' +
                ", disponible=" + disponible +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartamento)) return false;
        Apartamento Apartamento = (Apartamento) o;
        return Objects.equals(id, Apartamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
