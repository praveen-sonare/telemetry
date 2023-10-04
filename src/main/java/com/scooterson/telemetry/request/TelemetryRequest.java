package com.scooterson.telemetry.request;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelemetryRequest {
    String event;
    JSONObject data;
    String published_at;
    String coreid;
    String userid;
    String fw_version;
    String isPublic;
}
