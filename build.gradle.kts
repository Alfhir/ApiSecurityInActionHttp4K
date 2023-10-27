plugins {
    kotlin("jvm") version "1.9.0"
    id("org.flywaydb.flyway") version "9.22.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val http4kVersion: String by project
val junitVersion: String by project

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
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    // implementation("org.http4k:http4k-cloudnative")
    implementation("org.http4k:http4k-contract")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.http4k:http4k-testing-hamkrest")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.flywaydb:flyway-core:9.22.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}