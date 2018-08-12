FROM maven:3.5-jdk-8-alpine AS build
LABEL Author="zhuchuangang <zhuchuangang20@qq.com>"
ARG version=1.0.0
ARG package_name=apollo-${version}.tar.gz
COPY settings.xml /root/.m2
COPY build.sh /scripts/
WORKDIR /src
RUN wget -c https://github.com/ctripcorp/apollo/archive/v${version}.tar.gz -O ${package_name} \
    && tar zxf ${package_name} --strip-components=1 \
    && cp /scripts/build.sh scripts/ \
    && chmod 777 scripts/build.sh
RUN scripts/build.sh
RUN mkdir /app \
    && cp apollo-configservice/target/apollo-configservice-${version}.jar /app/configservice.jar \
    && cp apollo-adminservice/target/apollo-adminservice-${version}.jar /app/adminservice.jar \
    && cp apollo-portal/target/apollo-portal-${version}.jar /app/portal.jar
RUN apk add -U tzdata \
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

FROM java:8-jre-alpine
WORKDIR /app
COPY --from=build /app .
COPY --from=build /etc/localtime /etc/localtime
EXPOSE 8070
EXPOSE 8080
EXPOSE 8090
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom", "-jar" ]
CMD [ "configservice.jar" ]