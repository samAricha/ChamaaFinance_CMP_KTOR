plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

group = "com.teka.chamaa_finance"
version = "1.0.0"
application {
    mainClass.set("com.teka.chamaa_finance.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}



val ktor_version = "2.3.7"

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)



    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    //exposed+mysql/postgresql
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.jetbrains.exposed:exposed-core:0.46.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.46.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.46.0")
    implementation("org.postgresql:postgresql:42.3.1")



    //dotenv dependency
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    //Ktor client
    implementation ("io.ktor:ktor-client-core:3.0.2")
    implementation("io.ktor:ktor-client-cio:3.0.2")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
}