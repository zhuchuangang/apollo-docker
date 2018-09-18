FROM registry.cn-hangzhou.aliyuncs.com/acs/maven:3-jdk-8 AS build
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
#创建/apollo-adminservice,解压安装包
RUN mkdir /apollo-adminservice \
    && unzip -d /apollo-adminservice /apollo-${version}/apollo-adminservice/target/apollo-adminservice-${version}-github.zip
RUN  cp /apollo-adminservice/apollo-adminservice-${version}.jar /apollo-adminservice/apollo-adminservice.jar

FROM java:8-jre-alpine
#设置时区
ENV TZ Asia/Shanghai
#设置服务名称
ENV APOLLO_ADMIN_SERVICE_NAME="service-apollo-adminservice"
#设置数据库
ENV SPRING_DATASOURCE_URL="jdbc:mysql://service-mysql-for-apollo.sre:3306/ApolloConfigDB?characterEncoding=utf8"
ENV SPRING_DATASOURCE_USERNAME="FillInCorrectUser"
ENV SPRING_DATASOURCE_PASSWORD="FillInCorrectPassword"
#设置eureka集群
ENV EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE="http://service-apollo-configservice:8080/eureka/"
#创建/apollo-adminservice目录
RUN mkdir /apollo-adminservice
#将编译完成的内容复制到/apollo-configservice目录下
COPY --from=build /apollo-adminservice /apollo-adminservice
#添加修改配置文件的脚本
COPY scripts/entrypoint.sh /apollo-adminservice/scripts/entrypoint.sh
#添加运行脚本
COPY scripts/startup-kubernetes.sh /apollo-adminservice/scripts/startup-kubernetes.sh
#修改脚本的执行权限
RUN  chmod 777 /apollo-adminservice/scripts/entrypoint.sh
#修改脚本的执行权限
RUN  chmod 777 /apollo-adminservice/scripts/startup-kubernetes.sh
# 日志目录
VOLUME /opt/logs/apollo-adminservice
#暴露端口
EXPOSE 8090
ENTRYPOINT ["/apollo-adminservice/scripts/entrypoint.sh"]
CMD ["/apollo-adminservice/scripts/startup-kubernetes.sh"]