#!/usr/bin/env bash
docker build -t telemetry .
docker run -p 9000:9000 -it telemetry
