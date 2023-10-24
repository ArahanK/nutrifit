#!/bin/bash

#Start Services
echo "Starting Services"
open --background -a Docker
docker-compose up --build -d

#Start UI
echo "Starting UI"
cd view/src/main/java/com/hotel
java Main.java