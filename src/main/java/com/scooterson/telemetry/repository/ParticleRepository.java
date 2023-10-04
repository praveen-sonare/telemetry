package com.scooterson.telemetry.repository;

import com.scooterson.telemetry.model.Particle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticleRepository extends JpaRepository<Particle, Integer> {
    Optional<Particle> findByParticleId(String particleId);
    Optional<Particle> findById(String id);
}

