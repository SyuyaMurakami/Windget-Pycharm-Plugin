plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.18.1"
}

group = "SyuyaMurakami"
version = "1.3"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        pycharm("2025.3")
        bundledPlugin("PythonCore")
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding="UTF-8"
    }

    java {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(25)
            )
        }
    }

    patchPluginXml {
        sinceBuild.set("262")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
