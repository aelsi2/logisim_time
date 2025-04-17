import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.1.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "aelsi2.logisim_time"
version = "1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libs"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Library-Class"] = "aelsi2.logisim_time.TimeLibrary"
    }
}

tasks.withType<ShadowJar> {
    exclude("logisim-*.jar")
}

kotlin {
    jvmToolchain(8)
}