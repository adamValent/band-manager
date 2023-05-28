############ BUILD STAGE ############
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY  . /app
WORKDIR /app
RUN mvn clean install -DskipTests -pl '!:module-email,!:module-pdf,!:module-user' && \
	cp /app/module-core/target/module-core-*.jar /app/module-core.jar

############ RUNNABLE STAGE ############
FROM openjdk:17
VOLUME /tmp
COPY --from=build /app/module-core.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]