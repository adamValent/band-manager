############ BUILD STAGE ############
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY  . /app
WORKDIR /app
RUN mvn clean install -DskipTests -pl '!:module-core,!:module-pdf,!:module-email' && \
	cp /app/module-user/target/module-user-*.jar /app/module-user.jar

############ RUNNABLE STAGE ############
FROM openjdk:17
VOLUME /tmp
COPY --from=build /app/module-user.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]