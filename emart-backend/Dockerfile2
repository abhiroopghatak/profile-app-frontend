
FROM alpine/git

WORKDIR /app
RUN git clone https://github.com/abhiroopghatak/emart-backend.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app/emart-backend /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=1 /app/target/emart-backend-service-0.0.1-SNAPSHOT.jar /app
CMD java -jar emart-backend-service-0.0.1-SNAPSHOT.jar

#USER root

#COPY my-ldap-ca-bundle.crt /usr/local/share/ca-certificates/my-ldap-ca-bundle.crt
#RUN update-ca-certificates


#COPY my-ldap-ca-bundle.crt $JAVA_HOME/jre/lib/security/
#RUN \
#    cd $JAVA_HOME/jre/lib/security \
#    && $JAVA_HOME/bin/keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias ldapcert -file my-ldap-ca-bundle.crt

#Adding dynatrace server certificate
#ADD dynacert.crt /usr/local/share/ca-certificates/dynacert.cert
#RUN chmod 644 /usr/local/share/ca-certificates/dynacert.cert
#RUN update-ca-certificates

USER 1005
