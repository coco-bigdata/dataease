```xml
<execution>
    <id>npm install</id>
    <goals>
        <goal>npm</goal>
    </goals>
    <!-- Optional configuration which provides for running any npm command -->
    <configuration>
        <arguments>install --force</arguments>
    </configuration>
</execution>

<execution>
    <id>npm run build</id>
    <goals>
        <goal>npm</goal>
    </goals>
    <configuration>
        <arguments>run build</arguments>
    </configuration>
</execution>
```

```shell
wget --no-check-certificate https://github.com/dataease/dataease/releases/latest/download/dataease-release-v1.0.1.tar.gz
```

```shell
sudo docker-compose -f docker-compose-kettle-doris.yml up
sudo docker-compose -f docker-compose-kettle-doris.yml down
sudo docker-compose -f docker-compose-kettle-doris.yml up -d
sudo docker-compose -f docker-compose-kettle-doris.yml logs -f

sudo docker-compose -f docker-compose-kettle-doris.yml up -d kettle

http://82.157.51.152:8030/
http://82.157.51.152:8040/
http://82.157.51.152:8010/
http://82.157.51.152:8011/
cluster
cluster

sudo docker pull yiluxiangbei/centos7-jdk8
sudo docker run -it -d yiluxiangbei/centos7-jdk8 /bin/bash

sudo docker-compose -f docker-compose-kettle-doris2.yml build
sudo docker-compose -f docker-compose-kettle-doris2.yml up
sudo docker-compose -f docker-compose-kettle-doris2.yml down
sudo docker-compose -f docker-compose-kettle-doris2.yml up -d

tail -f doris2/fe/log/fe.*
tail -f doris2/be/log/be.*

http://82.157.51.152:8030/
http://82.157.51.152:8040/



kettle      | *******************************************************************************
kettle      | *** Karaf Instance Number: 1 at /opt/kettle/./system/karaf/caches/carte/dat ***
kettle      | ***   a-1                                                                   ***
kettle      | *** FastBin Provider Port:52901                                             ***
kettle      | *** Karaf Port:8802                                                         ***
kettle      | *** OSGI Service Port:9051                                                  ***
kettle      | *******************************************************************************

sudo docker-compose up dataease-mysql
sudo docker-compose down
sudo docker-compose up
sudo docker-compose up -d

npm run build
mvn package
cd docker
cp ../backend/target/backend-1.0.0.jar dataease-fe/
sudo docker-compose build
sudo docker-compose up -d dataease

mysql -h127.0.0.1 -P3316 -uroot -p
root
create database dataease default character set utf8mb4 collate utf8mb4_unicode_ci;
create database dataease default character set utf8mb4 collate utf8mb4_general_ci;
create database dataease;

mysql -h127.0.0.1 -uroot -p -P9030
ALTER SYSTEM ADD BACKEND "172.19.0.199:9050";
SHOW PROC '/backends';

SELECT CURRENT_USER();

SHOW PROC "/brokers";

ALTER SYSTEM ADD BROKER broker1 "173.29.40.42:8000";
ALTER SYSTEM drop BROKER broker1 "173.29.40.42:8000";
CREATE DATABASE dataease;

# doris 安装与部署
# https://doris.apache.org/master/zh-CN/installing/install-deploy.html#%E9%9B%86%E7%BE%A4%E9%83%A8%E7%BD%B2
# doris candidate backends is empty
# doris 修改密码
# https://doris.apache.org/master/zh-CN/getting-started/basic-usage.html#_1-%E5%88%9B%E5%BB%BA%E7%94%A8%E6%88%B7
SET PASSWORD FOR 'root' = PASSWORD('root');

CREATE USER 'test' IDENTIFIED BY 'test_passwd';
CREATE DATABASE example_db;
GRANT ALL ON example_db TO test;
```

```shell
wget https://mirrors.bfsu.edu.cn/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz
PATH=/home/git/apache-maven-3.8.1/bin:$PATH
```

```shell
sudo docker network ls
NETWORK ID     NAME             DRIVER    SCOPE
c7dcdcfb9d61   bridge           bridge    local
4149977c528b   docker_default   bridge    local
fc43d13bff71   host             host      local
4af94ec5168e   none             null      local

sudo docker network create --subnet=172.19.0.0/16 dataease-network

sudo docker network ls
NETWORK ID     NAME               DRIVER    SCOPE
c7dcdcfb9d61   bridge             bridge    local
837c72d14215   dataease-network   bridge    local
4149977c528b   docker_default     bridge    local
fc43d13bff71   host               host      local
4af94ec5168e   none               null      local 
```

```shell
sudo docker network --help

Usage:  docker network COMMAND

Manage networks

Commands:
  connect     Connect a container to a network
  create      Create a network
  disconnect  Disconnect a container from a network
  inspect     Display detailed information on one or more networks
  ls          List networks
  prune       Remove all unused networks
  rm          Remove one or more networks

Run 'docker network COMMAND --help' for more information on a command.
```

```shell
docker doris

apache_hdfs_broker
auditloader.zip
be
doris-spark-1.0.0-SNAPSHOT.jar
fe
udf

docker build -f Dockerfile -t doris:0.12.21-release .
```

```
# 交互式方式启动
-i

# 启动后进入容器（后面加了 -d所以不会进入）
-t

# 给容器命名
--name centos7

# 提升权限，拥有真正root权限，否则容器内root只是外部普通用户权限
--privileged=true

# 挂载目录
# # 将宿主机的 /mydocker/centos 目录映射到容器的 /usr/local/src 目录
-v /mydocker/centos:/usr/local/src

# 后台启动
-d

# 当Docker 重启时，容器会自动启动。
--restart=always
```