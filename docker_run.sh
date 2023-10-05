#!/bin/bash
sh Docker/clean.sh
rm -rf Docker/*/*.jar
mvn clean -DskipTests package
cp src/CalculatorAPI/target/*.jar Docker/CalculatorAPI/CalculatorApi.jar
cp src/CreditCalculatorAPI/target/*.jar Docker/CreditCalculatorAPI/CreditCalculatorAPI.jar
cp src/DiscoveryServer/target/*.jar Docker/DiscoveryServer/DiscoveryServer.jar
cp src/SmartCalculatorServer/target/*.jar Docker/SmartCalculatorServer/SmartCalculatorServer.jar
cp src/UserHistoryServer/target/*.jar Docker/UserHistoryServer/UserHistoryServer.jar
cp src/WebServer/target/*.jar Docker/WebServer/WebServer.jar
cd Docker && docker-compose up