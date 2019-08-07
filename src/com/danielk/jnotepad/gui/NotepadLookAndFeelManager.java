package com.danielk.jnotepad.gui;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

class NotepadLookAndFeelManager {

    public enum LookAndFeelTypes {
        WINDOWS,
        UNIX,
        JAVAMETAL,
        JAVAOCEAN
    }

    static void setLookAndFeel(LookAndFeelTypes lookAndFeelType, NotepadWindow notepadWindow) {

        try {
            switch (lookAndFeelType) {
                case WINDOWS:
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;

                case UNIX:
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    break;

                case JAVAMETAL: {
                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                }
                break;

                case JAVAOCEAN: {
                    MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                }
                break;
            }

            SwingUtilities.updateComponentTreeUI(notepadWindow);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}