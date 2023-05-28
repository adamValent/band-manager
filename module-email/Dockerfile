############ BUILD STAGE ############
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY  . /app
WORKDIR /app
RUN mvn clean install -DskipTests -pl '!:module-core,!:module-pdf,!:module-user' && \
	cp /app/module-email/target/module-email-*.jar /app/module-email.jar

############ RUNNABLE STAGE ############
FROM openjdk:17
VOLUME /tmp
COPY --from=build /app/module-email.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]