package com.danielk.jnotepad.gui;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

class NotepadLookAndFeelManager {

    public enum LookAndFeelTypes {
        WINDOWS,
        JAVAMETAL,
        JAVAOCEAN
    }

    static void setLookAndFeel(LookAndFeelTypes lookAndFeelType, NotepadWindow notepadWindow) {

        try {
            notepadWindow.dispose();

            switch (lookAndFeelType) {
                case WINDOWS: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    notepadWindow.setUndecorated(false);
                }
                break;

                case JAVAMETAL: {
                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    notepadWindow.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                    notepadWindow.setUndecorated(true);
                }
                break;

                case JAVAOCEAN: {

                    MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    notepadWindow.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                    notepadWindow.setUndecorated(true);
                }
                break;
            }

            SwingUtilities.updateComponentTreeUI(notepadWindow);
            notepadWindow.setVisible(true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}