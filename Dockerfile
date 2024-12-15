FROM public.ecr.aws/docker/library/gradle:jdk17 AS builder

WORKDIR /app

COPY ./gradle ./gradle
COPY ./gradlew ./gradlew
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle

RUN ./gradlew build -x test --no-daemon

FROM public.ecr.aws/amazoncorretto/amazoncorretto:17-al2023-headful AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# for Apline
# RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
# USER appuser

# for AL2023
RUN dnf update -y && dnf install -y shadow-utils
RUN groupadd --system appgroup && useradd --system --gid appgroup appuser
USER appuser

# Env set
EXPOSE 80

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]