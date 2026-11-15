package com.hotel.citas.controller;

import com.hotel.citas.model.Cita;
import com.hotel.citas.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cita> crearCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Cita> obtenerTodas() {
        return citaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Mono<Cita> obtenerPorId(@PathVariable String id) {
        return citaService.obtenerPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public Flux<Cita> obtenerPorEstado(@PathVariable String estado) {
        return citaService.obtenerPorEstado(estado);
    }

    @GetMapping("/email/{email}")
    public Flux<Cita> obtenerPorEmail(@PathVariable String email) {
        return citaService.obtenerPorEmail(email);
    }

    @GetMapping("/servicio/{tipoServicio}")
    public Flux<Cita> obtenerPorServicio(@PathVariable String tipoServicio) {
        return citaService.obtenerPorServicio(tipoServicio);
    }

    @PutMapping("/{id}")
    public Mono<Cita> actualizarCita(@PathVariable String id, @RequestBody Cita cita) {
        return citaService.actualizarCita(id, cita);
    }

    @PatchMapping("/{id}/estado")
    public Mono<Cita> cambiarEstado(@PathVariable String id, @RequestParam String nuevoEstado) {
        return citaService.cambiarEstado(id, nuevoEstado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminarCita(@PathVariable String id) {
        return citaService.eliminarCita(id);
    }
}