FROM maven:3.5.4-jdk-8-alpine AS build
LABEL Author="zhuchuangang <zhuchuangang20@qq.com>"
#设置版本号
ARG version=1.0.0
#设置源代码压缩包名称
ARG package_name=apollo-${version}.tar.gz
#下载源码
RUN wget -c https://github.com/ctripcorp/apollo/archive/v${version}.tar.gz -O ${package_name}
#解压源代码压缩包
RUN tar zxvf ${package_name}
#添加编译脚本build.sh
COPY scripts/build.sh /apollo-${version}/build.sh
#修改脚本的执行权限
RUN  chmod 777 /apollo-${version}/build.sh
#设置工作目录
WORKDIR /apollo-${version}
#执行编译脚本
RUN ./build.sh
#修改工作目录
WORKDIR ..
#创建/apollo-portal目录,解压安装包
RUN mkdir /apollo-portal \
    && unzip -d /apollo-portal /apollo-${version}/apollo-portal/target/apollo-portal-${version}-github.zip
RUN  cp /apollo-portal/apollo-portal-${version}.jar /apollo-portal/apollo-portal.jar

FROM java:8-jre-alpine
#设置时区
ENV TZ Asia/Shanghai
#设置服务名称
ENV APOLLO_PORTAL_SERVICE_NAME="service-apollo-portal"
#设置数据库
ENV SPRING_DATASOURCE_URL="jdbc:mysql://service-mysql-for-apollo.sre:3306/ApolloConfigDB?characterEncoding=utf8"
ENV SPRING_DATASOURCE_USERNAME="FillInCorrectUser"
ENV SPRING_DATASOURCE_PASSWORD="FillInCorrectPassword"
#设置eureka集群
ENV EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE="http://service-apollo-configservice:8080/eureka/"
#设置各环节的meta服务地址
ENV DEV_META="http://service-apollo-configservice:8080"
ENV FAT_META="http://service-apollo-configservice:8080"
ENV UAT_META="http://service-apollo-configservice:8080"
ENV LPT_META="http://service-apollo-configservice:8080"
ENV PRO_META="http://service-apollo-configservice:8080"
#创建/apollo-configservice目录
RUN mkdir /apollo-portal
#将编译完成的内容复制到/apollo-portal目录下
COPY --from=build /apollo-portal /apollo-portal
#添加修改配置文件的脚本
COPY scripts/entrypoint.sh /apollo-portal/scripts/entrypoint.sh
#添加运行脚本
COPY scripts/startup-kubernetes.sh /apollo-portal/scripts/startup-kubernetes.sh
#修改脚本的执行权限
RUN  chmod 777 /apollo-portal/scripts/entrypoint.sh
#修改脚本的执行权限
RUN  chmod 777 /apollo-portal/scripts/startup-kubernetes.sh
# 日志目录
VOLUME /opt/logs/apollo-portal
#暴露端口
EXPOSE 8070
ENTRYPOINT ["/apollo-portal/scripts/entrypoint.sh"]
CMD ["/apollo-portal/scripts/startup-kubernetes.sh"]