############ BUILD STAGE ############
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY  . /app
WORKDIR /app
RUN mvn clean install -DskipTests -pl '!:module-core,!:module-email,!:module-user' && \
	cp /app/module-pdf/target/module-pdf-*.jar /app/module-pdf.jar

############ RUNNABLE STAGE ############
FROM openjdk:17
VOLUME /tmp
COPY --from=build /app/module-pdf.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]