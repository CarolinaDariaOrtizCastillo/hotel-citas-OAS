package com.hotel.citas.service;

import com.hotel.citas.model.Cita;
import com.hotel.citas.repository.CitaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
public class CitaService {

    private static final Logger log = LoggerFactory.getLogger(CitaService.class);

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public Mono<Cita> crearCita(Cita cita) {
        cita.setEstado("PENDIENTE");
        cita.setCreadaEn(LocalDateTime.now());
        log.info("Creando cita para huesped: {}", cita.getNombreHuesped());
        return citaRepository.save(cita);
    }

    public Flux<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Mono<Cita> obtenerPorId(String id) {
        return citaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Cita no encontrada con id: " + id)));
    }

    public Flux<Cita> obtenerPorEstado(String estado) {
        return citaRepository.findByEstado(estado.toUpperCase());
    }

    public Flux<Cita> obtenerPorEmail(String email) {
        return citaRepository.findByEmail(email);
    }

    public Flux<Cita> obtenerPorServicio(String tipoServicio) {
        return citaRepository.findByTipoServicio(tipoServicio.toUpperCase());
    }

    public Mono<Cita> actualizarCita(String id, Cita citaActualizada) {
        return citaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Cita no encontrada con id: " + id)))
                .flatMap(citaExistente -> {
                    citaExistente.setNombreHuesped(citaActualizada.getNombreHuesped());
                    citaExistente.setEmail(citaActualizada.getEmail());
                    citaExistente.setTelefono(citaActualizada.getTelefono());
                    citaExistente.setTipoServicio(citaActualizada.getTipoServicio());
                    citaExistente.setNumeroHabitacion(citaActualizada.getNumeroHabitacion());
                    citaExistente.setFechaHora(citaActualizada.getFechaHora());
                    citaExistente.setDuracionMinutos(citaActualizada.getDuracionMinutos());
                    citaExistente.setEstado(citaActualizada.getEstado());
                    citaExistente.setNotas(citaActualizada.getNotas());
                    return citaRepository.save(citaExistente);
                });
    }

    public Mono<Cita> cambiarEstado(String id, String nuevoEstado) {
        return citaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Cita no encontrada con id: " + id)))
                .flatMap(cita -> {
                    cita.setEstado(nuevoEstado.toUpperCase());
                    return citaRepository.save(cita);
                });
    }

    public Mono<Void> eliminarCita(String id) {
        return citaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Cita no encontrada con id: " + id)))
                .flatMap(cita -> citaRepository.delete(cita));
    }
}