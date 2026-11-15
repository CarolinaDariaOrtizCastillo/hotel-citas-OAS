package com.hotel.citas.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "citas")
public class Cita {

    @Id
    private String id;
    private String nombreHuesped;
    private String email;
    private String telefono;
    private String tipoServicio;
    private String numeroHabitacion;
    private LocalDateTime fechaHora;
    private Integer duracionMinutos;
    private String estado;
    private String notas;
    private LocalDateTime creadaEn;

    public Cita() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreHuesped() { return nombreHuesped; }
    public void setNombreHuesped(String nombreHuesped) { this.nombreHuesped = nombreHuesped; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(String numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public LocalDateTime getCreadaEn() { return creadaEn; }
    public void setCreadaEn(LocalDateTime creadaEn) { this.creadaEn = creadaEn; }
}