#!/usr/bin/env bash
#docker run --name db_telemetry -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=telemetry -d postgres
docker build -t telemetry .
docker run -p 9000:9000 -it telemetry
