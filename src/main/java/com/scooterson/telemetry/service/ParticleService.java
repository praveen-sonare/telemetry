package com.scooterson.telemetry.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.scooterson.telemetry.model.Particle;
import com.scooterson.telemetry.repository.ParticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class ParticleService {
    private final ParticleRepository repository;
    HashMap<String, String> particleTelemetry = new HashMap<String, String>();
    @Autowired
    public ParticleService(ParticleRepository repository) {
        this.repository = repository;
        particleTelemetry.clear();
    }

    public boolean addParticleData(String payload) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(payload);
            String particleId = (String) json.getOrDefault("coreid", "");
            if(particleId != null && !particleId.isEmpty()) {
                Particle particle = new Particle();
                particle.setParticleId(particleId);
                particle.setPayload(payload);
                repository.save(particle);
                particleTelemetry.put(particleId, payload);
                return true;
            }
            return false;
        } catch (DataAccessException | ParseException ex)
        {
            log.info("EXCEPTION: " + ex.toString());
            return false;
        }
    }

    public String lookUp(String particleId) {
        return particleTelemetry.get(particleId);
    }
}
