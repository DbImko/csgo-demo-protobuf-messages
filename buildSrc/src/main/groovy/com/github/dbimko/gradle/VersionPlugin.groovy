package com.github.dbimko.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging;

public class VersionPlugin implements Plugin<Project> {

    private static final Logger BUILD_LOGGER = Logging.getLogger(Project.class);

    @Override
    void apply(Project project) {
        if (!(project.version instanceof Version)) {
            project.version = new Version(project.version as String);
        }
        project.version.build = getBuildVersion(project);
    }

    private static String getBuildVersion(Project project) {
        String result = 'SNAPSHOT';
        if (project.hasProperty('BUILD_NUMBER')) {
            def buildNumber = project.getProperty('BUILD_NUMBER')
            if (buildNumber != null && !buildNumber.isEmpty()) {
                result = buildNumber as Integer;
            }
        }
        if (System.getenv().containsKey('TRAVIS_BUILD_NUMBER')) {
            def buildNumber = System.getenv('TRAVIS_BUILD_NUMBER')
            if (buildNumber != null && !buildNumber.isEmpty()) {
                result = buildNumber as Integer;
            }
        }
        return result;
    }
}
