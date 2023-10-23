plugins {
    kotlin("jvm") version "1.9.0"
    id("org.flywaydb.flyway") version "9.22.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

flyway {
    driver = "org.postgresql.Driver"
    url = "jdbc:postgresql://localhost:5432/demo"
    user = "postgres"
    password = "postgres"
    locations = arrayOf("filesystem:./src/main/resources/db/migration")
    placeholders = mapOf("schema_name" to "demo")
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:5.8.5.1"))
    implementation("org.http4k:http4k-cloudnative")
    implementation("org.http4k:http4k-contract")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")

    implementation("org.flywaydb:flyway-core:9.22.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}