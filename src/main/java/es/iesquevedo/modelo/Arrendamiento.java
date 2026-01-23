package es.iesquevedo.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Arrendamiento {
    private String id;
    private String apartamentoId;
    private String clienteId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Arrendamiento() {
    }

    public Arrendamiento(String id, String apartamentoId, String clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.apartamentoId = apartamentoId;
        this.clienteId = clienteId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApartamentoId() {
        return apartamentoId;
    }

    public void setApartamentoId(String apartamentoId) {
        this.apartamentoId = apartamentoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Arrendamiento{" +
                "id='" + id + '\'' +
                ", apartamentoId='" + apartamentoId + '\'' +
                ", clienteId='" + clienteId + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrendamiento)) return false;
        Arrendamiento prestamo = (Arrendamiento) o;
        return Objects.equals(id, prestamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
