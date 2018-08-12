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
