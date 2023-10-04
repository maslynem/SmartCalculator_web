#!/bin/bash
sh Docker/clean.sh
rm -rf Docker/*/*.jar
cd src/CalculatorAPI && mvn clean -DskipTests package && cp target/*.jar ../../Docker/CalculatorAPI/CalculatorApi.jar
cd ..
cd DiscoveryServer && mvn clean -DskipTests package && cp target/*.jar ../../Docker/DiscoveryServer/DiscoveryServer.jar
cd ..
cd SmartCalculatorServer && mvn clean -DskipTests package && cp target/*.jar ../../Docker/SmartCalculatorServer/SmartCalculatorServer.jar
cd ..
cd UserHistoryServer && mvn clean -DskipTests package && cp target/*.jar ../../Docker/UserHistoryServer/UserHistoryServer.jar
cd ..
cd WebServer && mvn clean -DskipTests package && cp target/*.jar ../../Docker/WebServer/WebServer.jar
cd ../..
cd Docker && docker-compose up