# emart-app


**Migrating Spring boot web application to Quarkus.**  @ [link to myBlog](https://abhiroopspeaks.medium.com/migrating-spring-boot-web-application-to-quarkus-1530ad664faa)



$oc new-app --name frontend-emart  registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/abhiroopghatak/emart-app.git --context-dir=frontend-emart


$oc new-app --name emart-backend-service  registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/abhiroopghatak/emart-app.git --context-dir=emart-backend -port=8081

$oc expose svc frontend-emart

after deployment change svc port of backend svc to 8081
