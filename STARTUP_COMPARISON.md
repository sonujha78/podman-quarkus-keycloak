# Quarkus: JVM vs Native Startup Comparison

| Mode   | Startup Time | Binary Size |
|--------|-------------|-------------|
| JVM    | 0.418s      | quarkus-run.jar (uber jar dir) |
| Native | 0.022s      | 42.31 MiB executable |

Result: Native mode starts ~19x faster than JVM mode.

This is Quarkus's core selling point over traditional frameworks like Spring Boot, sub-second (here, sub-30-millisecond) startup makes it ideal for serverless, Kubernetes autoscaling, and cold-start-sensitive workloads.

Native build was done using GraalVM CE 25.3.4 (sdk install java 25.3.4+1.r25-graalce).
