# Movie Project

## Project thema
Wij hebben gekozen voor films omdat wij vinden dat dit een leuk onderwerp is. Beide hebben we ook een grote interesse in films.

## Microservices
Wij hebben gebruik gemaakt van de volgende services

### Movie-service:
Een movie heeft een titel, een beschrijving, een release date, een lijst met actors en een ratingId voor de rating van de film dat in een andere database opgeslagen is. Deze service is verbonden met een MongoDB database.
```java
public class Movie {
    private String movieId;
    private String title;
    private String description;
    private String releaseDate;
    private List<Long> actors;
    private int ratingId;
}
```

### Rating-service:
Een rating heeft enkel een rating. Deze service is verbonden met een MySQL database.
```java
public class Rating {
    private Long ratingId;
    private int rating;
}
```

### Actor-service:
Een actor heeft een naam, een geboortedag, een land van herkomst en een boolean ofdat deze actor nog actief is. Deze service is verbonden met een MySQL database.
```java
public class Actor {
    private Long actorId;
    private String name;
    private Date birthDate;
    private String country;
    private boolean active;
}
```

### Cinema-service:
Een cinema heeft een naam, een adress en een lijst met films. Deze service is verbonden met een MongoDB database.
```java
public class Cinema {
    private String cinemaId;
    private String name;
    private String address;
    private List<String> movies;
}
```

## API-Gateway
Onze API-gateway is gebaseerd op Spring Cloud Gateway. Deze gateway zorgt ervoor dat clientverzoeken naar de juiste microservices worden geleid. We hebben alle API-oproepen beveiligd met OAuth 2.0. Via de API-gateway kan de client een token verkrijgen en dit gebruiken om beveiligde API-oproepen te doen. Alleen de opvraging van actors is toegestaan zonder het gebruik van een token
```java
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
            .authorizeExchange(exchange -> exchange.pathMatchers(HttpMethod.GET, "/actors")
                .permitAll()
                .anyExchange()
                .authenticated())
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(withDefaults()));
        return serverHttpSecurity.build();
    }
}
```
We hebben per microservice 1 GET call aangemaakt. Dan hebben we bij onze actors nog de POST, PUT en DELETE toegevoegd.
```yaml
server:
  port: 8084

logging:
  level:
    root: INFO
    org.springframework.cloud.gateway: TRACE
    org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator: INFO
    reactor.netty: TRACE

spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENTID}
            client-secret: ${GOOGLE_CLIENTSECRET}
            scope: openid,profile,email
      resourceserver:
        jwt:
          issuer-uri: https://accounts.google.com
          jwk-set-uri: https://www.googleapis.com/oauth2/v3/certs
  cloud:
    gateway:
      routes:
        - id: actor-service
          uri: http://${ACTOR_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/actors
            - Method=GET
          filters:
            - SetPath=/api/actors
        - id: actor-service-post
          uri: http://${ACTOR_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/actors
            - Method=POST
          filters:
            - SetPath=/api/actors
        - id: actor-service-id
          uri: http://${ACTOR_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/actors/**
            - Method=GET
          filters:
            - RewritePath=/actors/(?<id>.*), /api/actors/${id}
        - id: actor-service-delete
          uri: http://${ACTOR_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/actors/**
            - Method=DELETE
          filters:
            - RewritePath=/actors/(?<id>.*), /api/actors/${id}
        - id: actor-service-put
          uri: http://${ACTOR_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/actors/**
            - Method=PUT
          filters:
            - RewritePath=/actors/(?<id>.*), /api/actors/${id}
        - id: cinema-service
          uri: http://${CINEMA_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/cinemas
            - Method=GET
          filters:
            - SetPath=/api/cinemas
        - id: movie-service
          uri: http://${MOVIE_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/movies
            - Method=GET
          filters:
            - SetPath=/api/movies
        - id: movie-service-title
          uri: http://${MOVIE_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/movies/**
            - Method=GET
          filters:
            - RewritePath=/movies/(?<title>.*), /api/movies/${title}
        - id: movie-service-post
          uri: http://${MOVIE_SERVICE_BASEURL:localhost:8080}
          predicates:
            - Path=/movies
            - Method=POST
          filters:
            - SetPath=/api/movies
        - id: rating-service
          uri: http://${RATING_SERVICE_BASEURL:localhost:8081}
          predicates:
            - Path=/ratings
            - Method=GET
          filters:
            - SetPath=/api/ratings
```
### Hier volgen de 4 GET calls
<img src="/images/ActorsGet.png" alt="get actors"/>
<img src="/images/CinemasGet.png" alt="get cinemas"/>
<img src="/images/MoviesGet.png" alt="get movies"/>
<img src="/images/RatingsGet.png" alt="get ratings"/>

#### De POST op actors
<img src="/images/ActorsPost.png" alt="post actors"/>

#### De PUT op actors
<img src="/images/ActorsPut.png" alt="put actors"/>

#### De DELETE op actors
<img src="/images/ActorsDelete.png" alt="delete actors"/>

## Deployment Diagram
<img src="/images/movieProject.png" alt="deployment diagram"/>

## Kubernetes
Wij hebben gekozen om een uitbreiding te doen met kubernetes, Prometheus en Grafana, hiervoor gebruiken we minikube en kubectl. De manifest files zijn opgedeeld per microservice en zijn database. Wij hebben ook een script geschreven om de cluster op te starten, in dit script worden alle manifest files geladen. Ook de uitbreiding met Prometheus en Grafana staan hier. Nu volgt de manifest file van movie-service.
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongo-movies
  namespace: default
spec:
  selector:
    matchLabels:
      app: mongo-movies
  template:
    metadata:
      labels:
        app: mongo-movies
    spec:
      containers:
        - name: mongo-movies
          image: mongo:latest
          ports:
            - containerPort: 27017

---
apiVersion: v1
kind: Service
metadata:
  name: mongo-movies
  namespace: default
spec:
  selector:
    app: mongo-movies
  ports:
    - protocol: TCP
      port: 27017
      targetPort: 27017

---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: movie-service
  namespace: default
spec:
  selector:
    matchLabels:
      app: movie-service
  template:
    metadata:
      labels:
        app: movie-service
    spec:
      containers:
        - name: movie-service
          image: j0ppek/movie-service:latest
          env:
            - name: MONGODB_PORT
              value: "27017"
            - name: MONGODB_HOST
              value: "mongo-movies"

---
apiVersion: v1
kind: Service
metadata:
  name: movie-service
  namespace: default
spec:
  selector:
    app: movie-service
  ports:
    - protocol: TCP
      port: 8082
      targetPort: 8080
```

### Minikube dashboard
Dit is het dashboard van Minikube, hier ziet u alle pods, services, deployments, ... die in de default namespace van de cluster worden uitgevoerd.
De pods, services, deployments van Prometheus en Grafana worden uitgevoerd in het monitoring namespace.
<img src="/images/minikubeDashboard-defaultNamespace.png" alt="minikube dashboard"/>

### Prometheus example
Dit is het Prometheus dashboard.
<img src="/images/prometheusDashboard.png" alt="prometheus dashboard"/>

### Grafana example
Dit is het Grafana dashboard.
<img src="/images/grafanaDashboard.png" alt="grafana dashboard"/>
