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

mvn package
cd docker
cp ../backend/target/backend-1.0.0.jar dataease-fe/
sudo docker-compose build
sudo docker-compose up -d dataease

mysql -h127.0.0.1 -P3316 -uroot -p
create database dataease default character set utf8mb4 collate utf8mb4_unicode_ci;
create database dataease default character set utf8mb4 collate utf8mb4_general_ci;
create database dataease;
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