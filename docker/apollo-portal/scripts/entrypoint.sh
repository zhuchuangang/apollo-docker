#!/bin/sh

set -e
# apollo-portal env config
#apollo_portal_env_config=/apollo-portal/config/apollo-env.properties
#
#cat ${apollo_portal_env_config}
#
#cp ${apollo_portal_env_config} ${apollo_portal_env_config}.cp
#
#sed -i -E "s#DEV_META#${DEV_META}#g" ${apollo_portal_env_config}.cp
#sed -i -E "s#FAT_META#${FAT_META}#g" ${apollo_portal_env_config}.cp
#sed -i -E "s#UAT_META#${UAT_META}#g" ${apollo_portal_env_config}.cp
#sed -i -E "s#LPT_META#${LPT_META}#g" ${apollo_portal_env_config}.cp
#sed -i -E "s#PRO_META#${PRO_META}#g" ${apollo_portal_env_config}.cp
#
#
#cat ${apollo_portal_env_config}.cp > ${apollo_portal_env_config}
#rm -rf ${apollo_portal_env_config}.cp

# apollo-portal config
apollo_portal_config=/apollo-portal/config/application-github.properties
cp ${apollo_portal_config} ${apollo_portal_config}.cp

sed -i -E "s#SPRING_DATASOURCE_URL#${SPRING_DATASOURCE_URL}#g" ${apollo_portal_config}.cp
sed -i -E "s#SPRING_DATASOURCE_USERNAME#${SPRING_DATASOURCE_USERNAME}#g" ${apollo_portal_config}.cp
sed -i -E "s#SPRING_DATASOURCE_PASSWORD#${SPRING_DATASOURCE_PASSWORD}#g" ${apollo_portal_config}.cp

cat ${apollo_portal_config}.cp > ${apollo_portal_config}
rm -rf ${apollo_portal_config}.cp

exec $@

