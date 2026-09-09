package com.bluescratch.endofitall.popup;

import com.bluescratch.endofitall.Teoia;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class WindowUtil {

    public static void minimize() {
        Minecraft minecraft = Minecraft.getInstance();

        Window window = minecraft.getWindow();

        org.lwjgl.glfw.GLFW.glfwIconifyWindow(window.getWindow());
    }

    public static Image getIcon() {
        try (InputStream stream = PopupWindow.class.getClassLoader()
                .getResourceAsStream("assets/teoia/textures/popup/icon_128x128.png")) {

            if (stream == null) {
                throw new IllegalStateException("Failed to locate Image Icon: nostalgia.icon_png_minecraft.image.lang.");
            }

            return ImageIO.read(stream);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void killMinecraftInstance(int retrn) {
        System.exit(retrn);
    }

    public static Path findModJar() {
        return ModList.get()
                .getModFileById(Teoia.MOD_ID)
                .getFile()
                .getFilePath();
    }

    public static String buildClasspath() {
        if (FMLEnvironment.production) {
            return findModJar().toString();
        }
        return System.getProperty("java.class.path");
    }
}
