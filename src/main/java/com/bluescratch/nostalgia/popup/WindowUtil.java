package com.bluescratch.nostalgia.popup;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class WindowUtil {

    public static void minimize() {
        Minecraft minecraft = Minecraft.getInstance();

        Window window = minecraft.getWindow();

        org.lwjgl.glfw.GLFW.glfwIconifyWindow(window.getWindow());
    }

    public static Image getIcon() {
        try (InputStream stream = PopupWindow.class.getClassLoader()
                .getResourceAsStream("assets/nostlg/textures/popup/icon.png")) {

            if (stream == null) {
                throw new IllegalStateException("Failed to locate Image Icon: nostalgia.icon_png_minecraft.image.lang.");
            }

            return ImageIO.read(stream);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void killMinecraftInstance() {
        System.exit(0);
    }
}
