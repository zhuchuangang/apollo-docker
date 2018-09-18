# 1 映射mysql地址
修改service-mysql-for-apollo-prod.yaml文件中Endpoints设置的mysql的IP地址。
```yaml
kind: Endpoints
apiVersion: v1
metadata:
  namespace: apollo
  name: service-mysql-for-apollo-prod
subsets:
  - addresses:
      - ip: 10.10.1.120
    ports:
      - protocol: TCP
        port: 3306
```

# 2 修改数据库用户名和密码
service-apollo-adminservice-prod.yaml、service-apollo-configservice-prod.yaml、service-apollo-portal-server.yaml
配置中的数据库用户名和密码。
```yaml
  SPRING_DATASOURCE_USERNAME: root
  SPRING_DATASOURCE_PASSWORD: 123456
```

# 3 创建namespace
```bash
kubectl create namespace apollo
```

# 4 节点打标签
```bash
kubectl label nodes kube-node node=apollo
```
> 注意：kube-node为节点名称,根据实际情况进行设置

# 5 创建数据库
参考 [分布式部署指南](https://github.com/ctripcorp/apollo/wiki/分布式部署指南) 的 2.1 创建数据库 章节，创建ApolloPortalDB和
ApolloConfigDB数据库。


# 5 数据库配置
修改ApolloConfigDB库的ServerConfig表的eureka.service.url配置为
```
http://statefulset-apollo-configservice-prod-0.service-apollo-meta-server-prod.apollo:8080/eureka/,http://statefulset-apollo-configservice-prod-1.service-apollo-meta-server-prod.apollo:8080/eureka/,http://statefulset-apollo-configservice-prod-2.service-apollo-meta-server-prod.apollo:8080/eureka/
```
statefulset-apollo-configservice-prod-0.service-apollo-meta-server-prod.apollo为pod的DNS地址。

# 6 创建mysql外网映射服务
```bash
kubectl create -f ./service-mysql-for-apollo-prod.yaml
```

# 7 创建apollo configservice服务
```bash
kubectl create -f ./service-apollo-configservice-prod.yaml
```

# 8 创建apollo adminservice服务
```bash
kubectl create -f ./service-apollo-adminservice-prod.yaml
```

# 9 创建apollo portal服务
````bash
kubectl create -f ./service-apollo-portal-server.yaml
````
