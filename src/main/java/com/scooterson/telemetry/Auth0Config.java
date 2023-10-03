package com.scooterson.telemetry;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "auth0")
public class Auth0Config {
    private String domain = "https://fleetscooterson.us.auth0.com";//"https://scooterson.us.auth0.com";
    private String clientId = "re3QZwDbPClKkPR2tZhFkT6Zx6aFzsfm" ;//"G4aT2pHWGVregk8NID4tZVe2wgZyjZwF";
    private String clientSecret = "kF4ow07jXmtiLekXZDPhDR4V2st7SOLfMjDgMXd9-fVEc7ZL2XWuGz1UhKtGStnk";//"YPieyHqoyanGGb24qxNmuljLGBPi4Za0X9GJ9XBMvP8bijgrS615j-aWMhO-OMFA";
    private String connection = "Username-Password-Authentication";
}
