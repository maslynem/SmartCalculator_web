#!/bin/bash
sh Docker/clean.sh
rm -rf Docker/*/*.jar
cd CalculatorAPI && mvn clean && mvn package && cp target/*.jar ../Docker/CalculatorAPI/CalculatorApi.jar
cd ..
cd DiscoveryServer && mvn clean && mvn package && cp target/*.jar ../Docker/DiscoveryServer/DiscoveryServer.jar
cd ..
cd SmartCalculatorServer && mvn clean && mvn package && cp target/*.jar ../Docker/SmartCalculatorServer/DiscoveryServer.jar
cd ..
cd UserHistoryServer && mvn clean && mvn package && cp target/*.jar ../Docker/UserHistoryServer/DiscoveryServer.jar
cd ..
cd WebServer && mvn clean && mvn package && cp target/*.jar ../Docker/WebServer/DiscoveryServer.jar
cd ..
cd Docker && docker-compose up