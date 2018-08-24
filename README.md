# 1 build镜像
```bash
docker build ./ -t szss/apollo
```

# 2 配置mysql数据库
在mysql目录下执行下面的命令，运行mysql容器：
```bash
docker-compose -f docker-compose-mysql.yaml up -d
```

执行apollo的mysql脚本 

https://github.com/ctripcorp/apollo/blob/master/scripts/sql/apolloconfigdb.sql

https://github.com/ctripcorp/apollo/blob/master/scripts/sql/apolloportaldb.sql

分别创建ApolloPortalDB库和ApolloConfigDB库。

# 3 修改docker-compose.yaml文件
修改docker-compose.yaml文件中的$host_ip为主机IP

# 3 启动apollo服务
在项目的根目录下执行：
```bash
docker-compose up -d
```

# 4 访问系统
访问potal页面：http://127.0.0.1:8070

默认用户超级管理员：apollo/admin


# 5 测试配置
```
test = 100
spring.datasource.type = com.alibaba.druid.pool.DruidDataSource
spring.datasource.url = jdbc:mysql://127.0.0.1:3306/test
spring.datasource.username = root
spring.datasource.password = 123456
spring.datasource.driver-class-name = com.mysql.jdbc.Driver
spring.datasource.druid.validationQuery = SELECT 1
spring.datasource.druid.initialSize = 10
spring.datasource.druid.minIdle = 10
spring.datasource.druid.maxActive = 200
spring.datasource.druid.minEvictableIdleTimeMillis = 180000
spring.datasource.druid.testOnBorrow = false
spring.datasource.druid.testWhileIdle = true
spring.datasource.druid.removeAbandoned = true
spring.datasource.druid.removeAbandonedTimeout = 1800
spring.datasource.druid.logAbandoned = true
spring.datasource.druid.poolPreparedStatements = true
spring.datasource.druid.maxOpenPreparedStatements = 100
```

# 6 数据库、redis、mongo配置刷新参考
https://github.com/cartersz/connection