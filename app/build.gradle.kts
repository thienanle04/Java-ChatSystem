plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id("application") // Enables application plugin
    id("java") // Java plugin for building Java projects
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven { 
        url = uri("https://maven.pkg.github.com/mrniko/netty-socketio")
        credentials {
            username = "22127005"
            password = "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"
        }
    }
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)

    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("org.slf4j:slf4j-api:2.0.9")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.9")
    implementation("org.json:json:20240303")
    implementation("org.jfree:jfreechart:1.5.3")

    // FlatLaf
    implementation("com.formdev:flatlaf:3.4.1")
    implementation("com.formdev:flatlaf-extras:3.4.1")
    implementation("com.formdev:flatlaf-fonts-roboto:2.137")

    // MigLayout
    implementation("com.miglayout:miglayout-core:5.3")
    implementation("com.miglayout:miglayout-swing:5.3")

    implementation("com.corundumstudio.socketio:netty-socketio:2.0.12")
    implementation("io.socket:socket.io-client:2.0.1")

    implementation("org.json:org.json:chargebee-1.0")

    implementation("com.toedter:jcalendar:1.4")
    implementation("com.sun.mail:javax.mail:1.6.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0") // Replace with the latest version
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.0") // Optional: For better parameter name handling
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    // Define the main class for the application.
    mainClass = "user.Main"
}

sourceSets {
    main {
        java.srcDirs("src/main/java")
        resources.srcDirs("src/main/resources")
    }
}