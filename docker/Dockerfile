FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /workspace

COPY ./ ./

RUN ./mvnw -B -q -DskipTests clean package

FROM eclipse-temurin:21-jre-jammy AS runner
ARG GID=2000
ARG UID=2000

ENV SPRING_PROFILES_ACTIVE=prod

RUN addgroup --gid ${GID} spring && \
    adduser --uid ${UID} --gid ${GID} --shell /usr/sbin/nologin spring

WORKDIR /app

COPY ./docker/entrypoint.sh /entrypoint.sh

COPY --chown=spring --from=builder /workspace/target/*.jar /app/app.jar

EXPOSE 8080
USER spring

CMD ["java","-jar","/app/app.jar"]
