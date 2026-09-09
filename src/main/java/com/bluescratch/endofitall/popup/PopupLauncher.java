package com.bluescratch.endofitall.popup;

import com.bluescratch.endofitall.Teoia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class PopupLauncher {

    public static final List<Process> activePopups = new ArrayList<>();

    public static void show(boolean minimize, String title, String message) {
        try {


            Teoia.LOGGER.info("Launching popup process...");

            ProcessBuilder builder = new ProcessBuilder(
                    findJava().toString(),
                    "-cp",
                    WindowUtil.buildClasspath(),
                    PopupWindow.class.getName(),
                    title,
                    message
            );

            Teoia.LOGGER.info("Java path: {}", findJava());
            Teoia.LOGGER.info("Classpath: {}", WindowUtil.buildClasspath());

            builder.inheritIO();

            Process process = builder.start();

            activePopups.add(process);

            Teoia.LOGGER.info("Started process: {}", process.pid());

            if (!minimize || Teoia.CLIENT_CONFIG.popupUtil.minimize.get() == false) return;

            Teoia.LOGGER.info("Minimizing: Minecraft Neoforge* 1.21.1");
            WindowUtil.minimize();
        }
        catch (IOException e) {
            Teoia.LOGGER.error("Failed to launch popup.", e);
        }
    }

    private static Path findJava() {
        Path bin = Paths.get(System.getProperty("java.home"), "bin");

        for (String name : new String[]{
                "java.exe",
                "javaw.exe",
                "java"
        }) {
            Path path = bin.resolve(name);

            if (Files.isRegularFile(path))
                return path;
        }

        throw new RuntimeException("No Java executable found");
    }
}