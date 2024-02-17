plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
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
}

kotlin {
    jvmToolchain(11)
}

tasks {
    shadowJar {
        relocate("dev.dejvokep.boostedyaml", "dev.rafi.vitelist.libs")
    }
}