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