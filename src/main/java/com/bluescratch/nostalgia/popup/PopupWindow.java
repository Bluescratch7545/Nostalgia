package com.bluescratch.nostalgia.popup;

import javax.swing.*;
import java.awt.*;

public class PopupWindow {

    public static void main(String[] args) {

        String title = args.length > 0 ? args[0] : "nostalgia.backup_title_name.lang";

        String message = args.length > 1 ? args[1] : "nostalgia.backup_title_text.lang";


        JFrame window = new JFrame(title);

        window.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JLabel label = new JLabel(message);

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        window.add(label, BorderLayout.CENTER);

        window.setIconImage(WindowUtil.getIcon());

        window.setPreferredSize(new Dimension(338, 90));
        window.pack();

        window.setLocationRelativeTo(null);

        window.setResizable(false);

        window.setAlwaysOnTop(true);

        window.setVisible(true);

    }
}