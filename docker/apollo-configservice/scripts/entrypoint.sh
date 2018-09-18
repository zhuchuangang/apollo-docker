#!/bin/sh

set -e

# config-server config
config_server_config=/apollo-configservice/config/application-github.properties
cp ${config_server_config} ${config_server_config}.cp

sed -i -E "s#SPRING_DATASOURCE_URL#${SPRING_DATASOURCE_URL}#g" ${config_server_config}.cp
sed -i -E "s#SPRING_DATASOURCE_USERNAME#${SPRING_DATASOURCE_USERNAME}#g" ${config_server_config}.cp
sed -i -E "s#SPRING_DATASOURCE_PASSWORD#${SPRING_DATASOURCE_PASSWORD}#g" ${config_server_config}.cp

cat ${config_server_config}.cp > ${config_server_config}
rm -rf ${config_server_config}.cp

exec $@
