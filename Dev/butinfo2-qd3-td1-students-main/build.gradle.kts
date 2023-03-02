import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("info.solidsoft.pitest") version "1.7.4"
    id("jacoco")

    application
}

group = "fr.nantes.univ.clock"
version = "1.0-SNAPSHOT"
pitest {
    setProperty("junit5PluginVersion", "0.15")
    setProperty("testPlugin", "junit5")
    setProperty("targetClasses", listOf("fr.nantes.univ.clock.*"))
    setProperty("outputFormats", listOf("HTML"))

    setProperty("failWhenNoMutations", false)
    setProperty("timestampedReports", false)
    setProperty("useClasspathFile", true)     //useful with bigger projects on Windows
}

jacoco {
    toolVersion = "0.8.8"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.2")
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