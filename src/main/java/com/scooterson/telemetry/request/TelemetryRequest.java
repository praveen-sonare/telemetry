package com.scooterson.telemetry.request;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelemetryRequest {
    JSONObject event;
    JSONObject data;
    JSONObject published_at;
    JSONObject coreid;
    JSONObject userid;
    JSONObject fw_version;
    JSONObject isPublic;
}
