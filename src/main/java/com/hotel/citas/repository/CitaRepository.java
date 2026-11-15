package com.hotel.citas.repository;

import com.hotel.citas.model.Cita;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CitaRepository extends ReactiveMongoRepository<Cita, String> {

    Flux<Cita> findByEstado(String estado);
    Flux<Cita> findByEmail(String email);
    Flux<Cita> findByTipoServicio(String tipoServicio);
    Flux<Cita> findByNumeroHabitacion(String numeroHabitacion);
}