
1. config server 기동이 가장 먼저 되어야한다.
> cd configserver
> ./gradlew BootRun

다만, cloud bus 사용하게 된다면 config server 전에 docker 이용해서 rabbitMQ 실행

2. eureka server 기동
> cd eureka
> ./gradlew bootJar
> java -jar example.jar

3. api gateway 기동
> cd apigateway
> ./gradlew bootJar
> java -jar example.jar

4. 각 서비스객체가 기동 (user, product, order)
> cd user, product, order
> ./gradlew bootJar
> java -jar example.jar

ps: 특이사항
order 기동 시 Kafka(비동기) - zookeeper - docker-compose 

endpoint:
http://localhost:port/user-service/user/signIn
http://localhost:port/product-service/product/create
http://localhost:port/order-service/order/create