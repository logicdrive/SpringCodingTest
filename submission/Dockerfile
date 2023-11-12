FROM openjdk:15-jdk-alpine


WORKDIR /root

COPY target/*SNAPSHOT.jar app.jar
COPY scripts/ scripts/

RUN apk update
RUN apk add python3
RUN apk add py3-pip
RUN python3 -m pip install --upgrade pip setuptools
RUN python3 -m pip install --upgrade httpie

RUN apk add gcc python3-dev
RUN apk add build-base linux-headers
RUN python3 -m pip install --upgrade psutil


EXPOSE 48081

ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar","--spring.profiles.active=docker"]