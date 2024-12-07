FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/swagger-0.0.1.jar swagger.jar
ENTRYPOINT ["java", "-jar", "/deploy/swagger.jar"]