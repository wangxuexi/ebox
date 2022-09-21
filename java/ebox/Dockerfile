FROM hub.c.163.com/library/java:8-alpine
ADD target/*.jar app.jar

LABEL license='SPDX-License-Identifier: Apache-2.0' \
      copyright='Copyright (c) 2018'
ENV TZ=Asia/Shanghai
RUN echo "http://mirrors.aliyun.com/alpine/v3.4/main/" > /etc/apk/repositories \
    && apk --no-cache add tzdata zeromq \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo '$TZ' > /etc/timezone

EXPOSE 18091

ENTRYPOINT ["java","-jar","/app.jar"]

COPY officefile officefile/