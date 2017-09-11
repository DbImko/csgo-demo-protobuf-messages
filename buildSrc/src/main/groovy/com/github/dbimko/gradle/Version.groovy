package com.github.dbimko.gradle

import com.github.dbimko.gradle.BuildException

public class Version {
    private Integer major;

    private Integer minor;

    private Integer patch;
    
    /**
     * BUILD_NUMBER or special value like SNAPSHOT
     */
    def build;

    public Version(final String version) {
        Objects.requireNonNull(version);
        String localVersion = version;

        final String[] parts = localVersion.split('\\.', 3);
        if (parts.length > 3) {
            throw new BuildException("Version should not contain more then 3 parts beside build-number");
        }

        if (parts.length > 0) {
            major = parseVersionPart(parts[0]);
        }
        if (parts.length > 1) {
            minor = parseVersionPart(parts[1]);
        }
        if (parts.length > 2) {
            patch = parseVersionPart(parts[2]);
        }
    }

    private static Integer parseVersionPart(String versionPart) {
        if (!versionPart.isInteger()) {
            throw new BuildException("Expect version contains a numbers divided by dots");
        }
        return versionPart as Integer;
    }

    @Override
    public String toString() {
        return "$major.$minor.$patch.$build";
    }

}
