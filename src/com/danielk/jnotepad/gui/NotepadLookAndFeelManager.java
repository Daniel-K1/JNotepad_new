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
                case WINDOWS:       //SWITCH TO WINDOWS WORKS FINE
                    notepadWindow.dispose();
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(notepadWindow.getRootPane());
                    notepadWindow.setUndecorated(false);
                    notepadWindow.setVisible(true);
                    break;

                case UNIX:      //SWITCH DOESN't WORK - TITLE BAR FROM DIFFERENT LAF
                    notepadWindow.dispose();
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    notepadWindow.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                    SwingUtilities.updateComponentTreeUI(notepadWindow.getRootPane());
                    JFrame.setDefaultLookAndFeelDecorated(true);

                    notepadWindow.setUndecorated(true);
                    notepadWindow.setVisible(true);
                    break;

                case JAVAMETAL: { //WORKS FINE

                    notepadWindow.dispose();
                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    notepadWindow.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                    SwingUtilities.updateComponentTreeUI(notepadWindow.getRootPane());
                    notepadWindow.setUndecorated(true);
                    notepadWindow.setVisible(true);
                }
                break;

                case JAVAOCEAN: {       //WORKS FINE

                    notepadWindow.dispose();
                    MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    notepadWindow.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                    SwingUtilities.updateComponentTreeUI(notepadWindow.getRootPane());
                    notepadWindow.setUndecorated(true);
                    notepadWindow.setVisible(true);
                }
                break;
            }

            //SwingUtilities.updateComponentTreeUI(notepadWindow);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}