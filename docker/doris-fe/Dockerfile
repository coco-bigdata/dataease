FROM openkbs/jdk-mvn-py3

RUN mkdir -p /home/doris

ENV JAVA_HOME /usr/lib/jvm/java

COPY ./fe/ /home/doris/fe

COPY ./be/ /home/doris/be

COPY ./apache_hdfs_broker/ /home/doris/fs_broker

EXPOSE 8030 9020 9030 9010 9070 9060 8060 8040 9050 8000

VOLUME ["/home/doris/fe/conf", "/home/doris/fe/log", "/home/doris/fe/doris-meta", "/home/doris/be/conf", "/home/doris/be/log", "/home/doris/be/storage", "/home/doris/fs_brokers/conf"]

COPY entrypoint.sh /

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
