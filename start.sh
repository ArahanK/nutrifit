#!/bin/bash

#Build and install
cd Services/nutrition/
mvn clean install
cd -

cd Services/exercise/
mvn clean install
cd -

cd Services/user/
mvn clean install

cd -

#Start Services
echo "Starting Services"
open --background -a Docker
docker-compose up --build -d

#Start UI
echo "Starting UI"
cd Client/client
npm start