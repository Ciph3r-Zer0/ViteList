plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-velocity") version "2.2.2"
}

group = "dev.rafi"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.1.1")
    kapt("com.velocitypowered:velocity-api:3.1.1")
    implementation("dev.dejvokep:boosted-yaml:1.3")
    implementation("org.jetbrains.exposed:exposed-core:0.48.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.48.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.48.0")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
}

kotlin {
    jvmToolchain(11)
}

tasks {
    shadowJar {
        relocate("dev.dejvokep.boostedyaml", "dev.rafi.vitelist.libs.boostedyaml")
        relocate("com.mysql.jdbc", "dev.rafi.vitelist.libs.jdbc")
        relocate("org.sqlite", "dev.rafi.vitelist.libs.sqlite")
    }
    runVelocity {
        velocityVersion("3.2.0-SNAPSHOT")
    }
}