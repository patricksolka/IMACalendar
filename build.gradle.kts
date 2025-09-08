import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "de.thm.mni.www"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.5.4"
val junitJupiterVersion = "5.9.1"
val log4jVersion = "2.23.0"
val jacksonVersion = "2.16.1"
val mapDBVersion = "3.1.0"

val mainVerticleName = "de.thm.mni.ima.Start"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web")
  implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")

  // Simple embedded file-based database
  implementation("org.mapdb:mapdb:$mapDBVersion")
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
  implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jdk8
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
  // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
