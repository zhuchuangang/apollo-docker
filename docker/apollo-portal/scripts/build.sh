#!/bin/sh

echo "==== starting to build portal ===="
mvn clean package -DskipTests -pl apollo-portal \
-am -Dapollo_profile=github,auth \
-Dspring_datasource_url=SPRING_DATASOURCE_URL \
-Dspring_datasource_username=SPRING_DATASOURCE_USERNAME \
-Dspring_datasource_password=SPRING_DATASOURCE_PASSWORD
