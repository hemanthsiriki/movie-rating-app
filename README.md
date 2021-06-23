

### Installation Steps:
```
> Load maven dependencies by Right clicking on Project -> Maven -> Reload project
> Set your mongo connection uri in application.properties
> Run springboot application 
```

By default the application runs on localhost:8080

###Swagger
```aidl
http://localhost:8080/swagger-ui.html
```

###Docker

####Run:
```aidl
 docker build -t movie-app-docker . -f docker/Dockerfile
 docker run -d -p 8080:8080 -t movie-app-docker:latest --MONGO_ROOT_USERNAME="admin" --MONGO_ROOT_PASSWORD="password"
```
####Stop:
```aidl
 docker ps -l
 docker stop <CONTAINER ID>
```
####Push to repository:
```aidl
 docker commit -a "Hemanth Siriki" <Container ID> containers.cisco.com/hesiriki/movie-app:latest
 docker push containers.cisco.com/hesiriki/movie-app:latest
```
```aidl
 docker image tag movie-app-docker containers.cisco.com/hesiriki/movie-app:latest
 docker image rm <imageID> -f (force delete image)
 
 pull:
 docker pull containers.cisco.com/hesiriki/movie-app:latest
```