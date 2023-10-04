package com.scooterson.telemetry.web;

import com.scooterson.telemetry.Auth0Service;
import com.scooterson.telemetry.UserRequest;
import com.scooterson.telemetry.model.Message;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.scooterson.telemetry.request.TelemetryRequest;
import com.scooterson.telemetry.security.SecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Autowired
    Controller(Auth0Service auth0Service){
        this.auth0Service = auth0Service;
    }

    @PostMapping(value = "/telemetry")
    public void saveParticleData(@RequestBody TelemetryRequest body) {
        log.info("All good. You DO NOT need to be authenticated to call. BODY : [" + body + "]");
        log.info(body.getEvent());
        log.info(body.getData().toString());
        log.info(body.getPublished_at());
        log.info(body.getCoreid());
        log.info(body.getUserid());
        log.info(body.getFw_version());
        log.info(body.getIsPublic());

    }

    @GetMapping(value = "/getTelemetry/{uuid}")
    public ResponseEntity<JSONObject> getTelemetry(JwtAuthenticationToken principal, @PathVariable String uuid) {
        log.info("All good. You can see this because you are Authenticated.");
        log.info("***** start fetching telemetry for vehicle : " + uuid);
        try {;
            String telemetry = "{\"odo\": 11}";
            JSONParser parser = new JSONParser();
            return new ResponseEntity<>((JSONObject) parser.parse(telemetry), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
