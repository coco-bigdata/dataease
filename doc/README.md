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
```

```shell
wget --no-check-certificate https://github.com/dataease/dataease/releases/latest/download/dataease-release-v1.0.1.tar.gz
```

```shell
sudo docker-compose -f docker-compose-kettle-doris.yml up
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