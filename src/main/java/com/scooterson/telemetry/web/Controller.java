package com.scooterson.telemetry.web;

import com.scooterson.telemetry.Auth0Service;
import com.scooterson.telemetry.security.SecurityConfig;
import com.scooterson.telemetry.service.ParticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Handles requests to "/api" endpoints.
 * @see SecurityConfig to see how these endpoints are protected.
 */
@Slf4j
@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
// For simplicity of this sample, allow all origins. Real applications should configure CORS for their use case.
@CrossOrigin(origins = "*")
public class Controller {

    private final Auth0Service auth0Service;
    private final ParticleService particleService;
    @Autowired
    Controller(Auth0Service auth0Service, ParticleService particleService){
        this.auth0Service = auth0Service;
        this.particleService = particleService;
    }

    @PostMapping(value = "/telemetry")
    public void saveParticleData(@RequestBody String particleData) {
        particleService.addParticleData(particleData);
    }

    @GetMapping(value = "/getTelemetry/{particleId}")
    public ResponseEntity<String> getTelemetry(JwtAuthenticationToken principal, @PathVariable String particleId) {
        log.info("All good. You can see this because you are Authenticated.");
        log.info("***** start fetching telemetry for vehicle : " + particleId);
        String response = particleService.lookUp(particleId);
        if(response != null && !response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
