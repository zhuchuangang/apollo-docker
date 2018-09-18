#!/bin/sh

set -e

# admin-server config
admin_server_config=/apollo-adminservice/config/application-github.properties
cp ${admin_server_config} ${admin_server_config}.cp

sed -i -E "s#SPRING_DATASOURCE_URL#${SPRING_DATASOURCE_URL}#g" ${admin_server_config}.cp
sed -i -E "s#SPRING_DATASOURCE_USERNAME#${SPRING_DATASOURCE_USERNAME}#g" ${admin_server_config}.cp
sed -i -E "s#SPRING_DATASOURCE_PASSWORD#${SPRING_DATASOURCE_PASSWORD}#g" ${admin_server_config}.cp

cat ${admin_server_config}.cp > ${admin_server_config}
rm -rf ${admin_server_config}.cp

exec $@
