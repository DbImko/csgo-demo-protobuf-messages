package com.github.dbimko.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

public class JavaPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        project.apply({ it.plugin(org.gradle.api.plugins.JavaPlugin.class) })

        project.jar {
            manifest {
                attributes(
                        "Archiver-Version": "Gradle ${project.gradle.gradleVersion}",
                        "Created-By": "Gradle",
                        "Built-By": System.getProperty('user.name'),
                        "Built-Jdk": System.getProperty('java.version'),
                        "X-Build-Tag": System.getenv("TRAVIS_TAG") ?: "DEVELOPER_BUILD",
                        "X-Build-Number": project.version.build ?: "SNAPSHOT",
                        "X-Build-Id": System.getenv("TRAVIS_BUILD_ID") ?: "DEVELOPER_BUILD",
                        "X-Commit": System.getenv("TRAVIS_COMMIT") ?: "DEVELOPER_BUILD",
                        "X-Branch": System.getenv("TRAVIS_BRANCH") ?: "DEVELOPER_BUILD",
                        "X-Component-Id": project.name,
                        "X-Component-Version": version,
                )
            }
        }
    }

}
