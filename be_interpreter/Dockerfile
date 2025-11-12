# Stage 1: build Java app (Maven) and compile C++ interpreter
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /build

# Copy Java backend (maven project) and knudon source
COPY ./be /build/be
COPY ./knudon-2.0 /build/knudon-2.0

# Build Java project (skip tests for faster builds)
WORKDIR /build/be
RUN mvn -B -DskipTests package

# Install build tools and compile the interpreter (in the same builder stage)
WORKDIR /build/knudon-2.0
RUN apt-get update && apt-get install -y build-essential && \
    g++ -std=c++17 -O2 -o knudon main.cpp scanner.cpp parser.cpp interpreter.cpp statements.cpp || true

# Stage 2: runtime image with JRE
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# copy jar (explicit based on your pom)
COPY --from=builder /build/be/target/knudon-0.0.1-SNAPSHOT.jar /app/knudon.jar

# copy interpreter binary into interpreter folder
RUN mkdir -p /app/interpreter
COPY --from=builder /build/knudon-2.0/knudon /app/interpreter/knudon
RUN chmod +x /app/interpreter/knudon

# ensure the app can write to interpreter dir (your service writes code.k)
RUN chmod -R 0777 /app/interpreter

EXPOSE 8080
ENV JAVA_OPTS="-Xms128m -Xmx512m"

CMD ["sh", "-c", "exec java $JAVA_OPTS -jar /app/knudon.jar"]