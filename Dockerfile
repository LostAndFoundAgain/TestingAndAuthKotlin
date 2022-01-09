FROM openjdk:8
ENV PORT=3000
EXPOSE $PORT
#EXPOSE DOCKER_BUILDKIT=0
#EXPOSE COMPOSE_DOCKER_CLI_BUILD=0

ADD build/libs/testKotlin-0.0.1-SNAPSHOT-plain.jar testKotlin-0.0.1-SNAPSHOT-plain.jar
ENTRYPOINT ["java","-jar","/testKotlin-0.0.1-SNAPSHOT-plain.jar"]