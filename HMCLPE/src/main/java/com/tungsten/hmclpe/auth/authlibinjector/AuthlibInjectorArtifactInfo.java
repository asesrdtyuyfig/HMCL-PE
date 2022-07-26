package com.tungsten.hmclpe.auth.authlibinjector;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class AuthlibInjectorArtifactInfo {

    public static AuthlibInjectorArtifactInfo from(Path location) throws IOException {
        try (JarFile jarFile = new JarFile(location.toFile())) {
            Attributes attributes = jarFile.getManifest().getMainAttributes();

            String title = Optional.ofNullable(attributes.getValue("Implementation-Title"))
                    .orElseThrow(() -> new IOException("Missing Implementation-Title"));
            if (!"authlib-injector".equals(title)) {
                throw new IOException("Bad Implementation-Title");
            }

            String version = Optional.ofNullable(attributes.getValue("Implementation-Version"))
                    .orElseThrow(() -> new IOException("Missing Implementation-Version"));

            int buildNumber;
            try {
                buildNumber = Optional.ofNullable(attributes.getValue("Build-Number"))
                        .map(Integer::parseInt)
                        .orElseThrow(() -> new IOException("Missing Build-Number"));
            } catch (NumberFormatException e) {
                throw new IOException("Bad Build-Number", e);
            }
            return new AuthlibInjectorArtifactInfo(buildNumber, version, location.toAbsolutePath());
        }
    }

    private int buildNumber;
    private String version;
    private Path location;

    public AuthlibInjectorArtifactInfo(int buildNumber, String version, Path location) {
        this.buildNumber = buildNumber;
        this.version = version;
        this.location = location;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public String getVersion() {
        return version;
    }

    public Path getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "authlib-injector [buildNumber=" + buildNumber + ", version=" + version + "]";
    }
}