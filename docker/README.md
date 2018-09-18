# 1 build镜像
```bash
cd ./apollo-adminservice
docker build ./ -t szss/apollo-adminservice

cd ./apollo-configservice
docker build ./ -t szss/apollo-configservice

cd ./apollo-portal
docker build ./ -t szss/apollo-portal
```
# 2 创建数据库
参考 [分布式部署指南](https://github.com/ctripcorp/apollo/wiki/分布式部署指南) 的 2.1 创建数据库 章节，创建ApolloPortalDB和
ApolloConfigDB数据库。

# 3 使用docker compose启动服务
修改docker-compose.yaml文件中10.10.1.120的IP替换为响应主机的IP。

启动服务：
```bash
docker-compose up -d
```