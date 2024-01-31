import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.hibernate.orm") version "6.3.1.Final"
    id("org.graalvm.buildtools.native") version "0.9.28"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("plugin.jpa") version "1.9.20"
    kotlin("plugin.allopen") version "1.9.20"
}

group = "cc.shacocloud"
version = "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    // 改为阿里云的镜像地址
    maven {
        isAllowInsecureProtocol = true
        setUrl("https://maven.aliyun.com/repository/central")
    }
    maven {
        isAllowInsecureProtocol = true
        setUrl("https://maven.aliyun.com/repository/jcenter")
    }
    maven {
        isAllowInsecureProtocol = true
        setUrl("https://maven.aliyun.com/repository/google")
    }
    maven {
        isAllowInsecureProtocol = true
        setUrl("https://maven.aliyun.com/repository/public")
    }
    maven {
        isAllowInsecureProtocol = true
        setUrl("https://jitpack.io")
    }
    mavenCentral()
    google()
}

// 强制所有实体为 open 不为 final，避免不能被子类化，导致 Hibernate 的代理机制关闭
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.mysql:mysql-connector-j")
}

hibernate {
    enhancement {
        enableAssociationManagement.set(true)
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}