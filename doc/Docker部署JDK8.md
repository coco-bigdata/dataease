环境：Vmware 15 Pro

系统：Centos 7.7.1908

Docker Version: 19.03.7

JDK Version: jdk1.8.0_241

由于JDK的运行需要依赖系统环境，这个环境可以是Centos，也可以是Ubuntu，所以我们需要先在Docker中部署一个运行的容器系统，这里我们以 Centos为例，然后基于此容器部署JDK

1. 拉取Centos7镜像
   docker pull centos:7
   拉取成功，查看下载的镜像

docker images

2. 创建Centos容器，并进行目录挂载
   docker run -it \
   --name centos7 \
   --privileged=true \
   -v /mydocker/centos:/usr/local/src \
   -d \
   --restart=always \
   centos:7
   命令解析:

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
查看启动的容器

docker ps

到这里 Centos 容器已经部署完成，并成功启动了。

3. 下载 JDK1.8 安装包
   Oracle官网下载地址：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

由于JDK下载需要登陆，所以这里不能通过wget命令下载，只能自己在别的平台下载，通过ftp传到宿主机的文件夹中

创建jdk安装包存放目录
mkdir -p /mydocker/centos/jdk8
路径和版本如下：


4. 使用 Dockerfile脚本创建JDK镜像
   在 jdk8 目录中创建 Dockerfile脚本

vi Dockerfile
内容如下：

# 依赖镜像名称和ID
FROM centos:7
# 指定镜像创建者信息
MAINTAINER George
# 切换工作目录
WORKDIR /mydocker/centos/jdk8
# 在容器中创建目录
RUN mkdir /usr/local/java
#ADD 是相对路径,把jdk添加到容器中,并自动进行解压
ADD jdk-8u241-linux-x64.tar.gz /usr/local/java/

# 配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_241
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
这里需要注意，JDK 安装包需要和 Dockerfile文件在同一目录

5. 执行命令构建镜像
   docker build -t='jdk1.8' .
   后面最后的 . 不能丢掉，代表当前路径


构建成功，这时再查看镜像


6. 运行镜像，创建 jdk容器
   docker run -it \
   --name jdk8 \
   -d \
   --restart=always \
   jdk1.8
   参数解析，参考Centos部署

这里让它后台运行，没有直接进入容器内

进入容器，查看 jdk 运行情况

docker exec -it jdk8 /bin/bash
