FROM adoptopenjdk/openjdk11:jre-11.0.7_10-ubuntu

RUN echo "Docker build starts. Using adoptopenjdk/openjdk11:jre-11.0.7_10-ubuntu as base image"

RUN echo "maintained by : Abhiroop Ghatak => abhiroop.g@hcl.com"

RUN apt install curl

ARG NEXUS_REPO_USER

ARG NEXUS_REPO_PASS

ARG CI_COMMIT_SHA

WORKDIR /usr/app

RUN curl  -u $NEXUS_REPO_USER:$NEXUS_REPO_PASS -L -o emart-frontend-0.0.1-SNAPSHOT.jar "http://nexus.apps.azocp.hclcnpocp.com/service/rest/v1/search/assets/download?sort=version&repository=maven-snapshots&maven.groupId=com.abhiroop.emart&maven.artifactId=emart-frontend&maven.baseVersion=$CI_COMMIT_SHA-SNAPSHOT&maven.extension=jar"

RUN echo "Artifacts downloaded from binary repository Nexus"
EXPOSE 8080
CMD java -jar emart-frontend-0.0.1-SNAPSHOT.jar

USER 1005
