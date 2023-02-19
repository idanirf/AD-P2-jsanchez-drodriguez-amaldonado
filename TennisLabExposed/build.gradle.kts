plugins {
    kotlin("jvm") version "1.8.0"
    application
    kotlin("plugin.serialization") version "1.7.20"
    id("org.jetbrains.dokka") version "1.7.20"
}

group = "org.alfredoDaniJorge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Exposed
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    // h2 dataBase
    implementation("com.h2database:h2:2.1.214")
    // Loggers
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    // Date
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    // JSON Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    // Dokka for avoid JDOC and KDOC
    implementation("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.20")
    // Guava codification
    implementation("com.google.guava:guava:31.1-jre")
    // JUnit y Mockito
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.test {
    useJUnitPlatform()
}
/*
}

kotlin {
    jvmToolchain(8)
}*/
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClass.set("MainKt")
}