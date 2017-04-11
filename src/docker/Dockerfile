FROM centos:7
RUN yum update -y && yum install -y \
    epel-release \
    java-1.8.0-openjdk-headless        # Java and epel release
RUN useradd -d /cns-backend cns-backend
WORKDIR /cns-backend
ADD spring-boot-cns-backend.jar /cns-backend/
RUN chown -R cns-backend:cns-backend /cns-backend

EXPOSE 8080
USER cns-backend
CMD exec java $JAVA_OPTS -jar /cns-backend/spring-boot-cns-backend.jar
