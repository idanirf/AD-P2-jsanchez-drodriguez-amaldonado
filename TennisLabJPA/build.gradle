plugins {
    id 'java'
    id 'org.jetbrains.kotlin.jvm' version '1.6.20'
    id 'org.jetbrains.kotlin.plugin.allopen' version '1.6.20'
    id 'org.jetbrains.kotlin.plugin.noarg' version '1.6.20'
    id'org.jetbrains.kotlin.plugin.serialization' version '1.6.20'

}

group 'com.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

ext {
    junitVersion = '5.8.2'
}

sourceCompatibility = "16"
targetCompatibility = "16"

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}

[compileKotlin, compileTestKotlin].forEach {
    it.kotlinOptions {
        jvmTarget = '1.8'
    }
}

allOpen {
    annotation('javax.persistence.Entity')
    annotation('javax.persistence.Embeddable')
    annotation('javax.persistence.MappedSuperclass')
}

noArg {
    annotation('javax.persistence.Entity')
    annotation('javax.persistence.Embeddable')
    annotation('javax.persistence.MappedSuperclass')
}

dependencies {
    //Base de datos H2
    implementation("com.h2database:h2:2.1.214")

    //Logging
    implementation('io.github.microutils:kotlin-logging-jvm:3.0.3')
    implementation('ch.qos.logback:logback-classic:1.4.4')

    //Serializar JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation('org.hibernate:hibernate-core:5.6.1.Final')

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")

    implementation("com.google.guava:guava:31.1-jre")

    testImplementation('io.mockk:mockk:1.13.2')

}

test {
    useJUnitPlatform()
}