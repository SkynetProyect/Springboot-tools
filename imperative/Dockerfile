FROM ghcr.io/graalvm/native-image-community:25-muslib AS builder

RUN microdnf install -y findutils && microdnf clean all

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew nativeCompile --no-daemon

# ============================================================
# Stage 2 (opcional): Compresión con UPX
# ============================================================
FROM alpine:3.20 AS packer

RUN apk add --no-cache upx

COPY --from=builder /app/build/native/nativeCompile/imperative /imperative

RUN upx --lzma /imperative


# ============================================================
# Stage 3: Runtime Alpine
# ============================================================
FROM alpine:3.20

WORKDIR /app

# Solo certificados para HTTPS saliente (el binario es estático, no necesita libstdc++)
RUN apk add --no-cache ca-certificates \
    && addgroup -S app && adduser -S app -G app

COPY --from=packer /imperative /app/imperative

USER app

EXPOSE 8080

ENTRYPOINT ["/app/imperative"]