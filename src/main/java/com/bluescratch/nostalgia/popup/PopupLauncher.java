package com.bluescratch.nostalgia.popup;

import com.bluescratch.nostalgia.Nostalgia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PopupLauncher {

    public static void show(boolean minimize, String title, String message) {
        try {

            Nostalgia.LOGGER.info("Launching popup process...");

            ProcessBuilder builder = new ProcessBuilder(
                    findJava().toString(),
                    "-cp",
                    System.getProperty("java.class.path"),
                    PopupWindow.class.getName(),
                    title,
                    message
            );

            Nostalgia.LOGGER.info("Java path: {}", findJava());
            Nostalgia.LOGGER.info("Classpath: {}", System.getProperty("java.class.path"));

            builder.inheritIO();

            Process process = builder.start();

            Nostalgia.LOGGER.info("Started process: {}", process.pid());

            if (!minimize) return;

            Nostalgia.LOGGER.info("Minimizing: Minecraft Neoforge* 1.21.1");
            WindowUtil.minimize();
        }
        catch (IOException e) {
            Nostalgia.LOGGER.error("Failed to launch popup.", e);
        }
    }

    private static Path findJava() {
        Path bin = Paths.get(System.getProperty("java.home"), "bin");

        for (String name : new String[]{
                "javaw.exe",
                "java.exe",
                "java"
        }) {
            Path path = bin.resolve(name);

            if (Files.isRegularFile(path))
                return path;
        }

        throw new RuntimeException("No Java executable found");
    }
}