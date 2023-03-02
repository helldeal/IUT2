import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// https://betterprogramming.pub/how-to-improve-the-quality-of-tests-using-mutation-testing-2346019829f1

plugins {
    kotlin("jvm") version "1.7.20"
    id("info.solidsoft.pitest") version "1.7.4"
    id("jacoco")
    application
}

group = "palindrome"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

pitest {
    setProperty("junit5PluginVersion", "0.15")
    setProperty("testPlugin", "junit5")
    setProperty("targetClasses", listOf("iut.but2.r402.notation.*"))
    setProperty("outputFormats", listOf("HTML"))

    setProperty("mutators", listOf("STRONGER"))

    setProperty("failWhenNoMutations", false)
    setProperty("timestampedReports", false)
    setProperty("useClasspathFile", true)     //useful with bigger projects on Windows
}

jacoco {
    toolVersion = "0.8.8"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

application {
    mainClass.set("MainKt")
}
