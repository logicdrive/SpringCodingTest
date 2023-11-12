FROM openjdk:15-jdk-alpine


WORKDIR /root
COPY target/*SNAPSHOT.jar app.jar
COPY scripts/ scripts/

RUN apk update
RUN apk add python3


EXPOSE 48081

ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar","--spring.profiles.active=docker"]