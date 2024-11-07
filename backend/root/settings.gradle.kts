rootProject.name = "root"
include("market")

pluginManagement {
    repositories {
        gradlePluginPortal() // Gradle Plugin Portal 추가
        mavenCentral()       // Maven Central (기본값)
    }
}
