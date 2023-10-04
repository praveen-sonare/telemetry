package com.scooterson.telemetry.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Simple domain object for our API to return a message.
 */
@Getter
@Setter
@Entity
@Table(name = "particle_info")
public class Particle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "particle_id")
    private String particleId;
    @Column(name = "payload", columnDefinition = "text")
    private String payload;
}
