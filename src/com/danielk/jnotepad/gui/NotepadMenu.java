package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.event.*;

public class NotepadMenu {

    private final NotepadPrompts notepadPrompts;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenuItems;

    public static final int SAVE_MENUITEM=2;


    public NotepadMenu(NotepadWindow notepadWindow, NotepadPrompts notepadPrompts) {

        this.notepadPrompts=notepadPrompts;

        JMenu fileMenu = new MenuBuilder("File")
                .withMnemonic(KeyEvent.VK_P)
                .withItem(MenuItemBuilder.menuItem("New")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_N)
                        .withActionListener(ae -> notepadWindow.newFile())
                        .build())
                .withItem(MenuItemBuilder.menuItem("Open")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_O)
                        .withActionListener(ae -> this.notepadPrompts.openFile(this,notepadWindow))
                        .build())
                .withItem(MenuItemBuilder.menuItem("Save")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_A)
                        .withActionListener(ae -> this.notepadPrompts.saveFile(this, notepadWindow, false))
                        .isEnabled(false)
                        .build())
                .withItem(MenuItemBuilder.menuItem("Save as...")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_S)
                        .withActionListener(ae -> this.notepadPrompts.saveFile(this, notepadWindow, true))
                        .build())
                .withSeparator()
                .withItem(MenuItemBuilder.menuItem("Print...")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_D)
                        .build())
                .withSeparator()
                .withItem(MenuItemBuilder.menuItem("Exit")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_Z)
                        .withActionListener(ae ->notepadWindow.exitNotepad())
                        .build())
                .build();

        JMenu editMenu = new MenuBuilder("Edit")
                .withMnemonic(KeyEvent.VK_E)
                .withItem(MenuItemBuilder.menuItem("Copy")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_C)
                        .build())
                .withItem(MenuItemBuilder.menuItem("Paste")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_V)
                        .build())
                .withItem(MenuItemBuilder.menuItem("Cut")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_X)
                        .build())
                .withSeparator()
                .withItem(MenuItemBuilder.menuItem("Replace...")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_R)
                        .build())
                .withItem(MenuItemBuilder.menuItem("Find...")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_F)
                        .build())
                .withSeparator()
                .withItem(MenuItemBuilder.menuItem("Select all")
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK))
                        .withMnemonic(KeyEvent.VK_A)
                        .build())
                .build();

        JMenu formatMenu = new MenuBuilder("Format")
                .withMnemonic(KeyEvent.VK_F)
                .withItem(MenuItemBuilder.checkbox("Word wrap", false)
                        .withActionListener(ae -> notepadWindow.wrapText())
                        .build())
                .build();

        JMenu settingsMenu = new MenuBuilder("Settings")
                .withMnemonic(KeyEvent.VK_U)
                .withItem(MenuItemBuilder.checkbox("Hints", true)
                        .build())
                .withSeparator()
                .startGroup()
                .withItem(MenuItemBuilder.radioButton("Windows skin", true)
                        .withItemListener(ie -> {
                            if (ie.getStateChange() == ItemEvent.SELECTED)
                                NotepadLookAndFeelManager.setLookAndFeel(NotepadLookAndFeelManager.LookAndFeelTypes.WINDOWS, notepadWindow);
                        })
                        .build())
                .withItem(MenuItemBuilder.radioButton("Unix Skin", false)
                        .withItemListener(ie -> {
                            if (ie.getStateChange() == ItemEvent.SELECTED)
                                NotepadLookAndFeelManager.setLookAndFeel(NotepadLookAndFeelManager.LookAndFeelTypes.UNIX, notepadWindow);
                        })
                        .build())
                .withItem(MenuItemBuilder.radioButton("Java skin - Metal Theme", false)
                        .withItemListener(ie -> {
                            if (ie.getStateChange() == ItemEvent.SELECTED)
                                NotepadLookAndFeelManager.setLookAndFeel(NotepadLookAndFeelManager.LookAndFeelTypes.JAVAMETAL, notepadWindow);
                        })
                        .build())
                .withItem(MenuItemBuilder.radioButton("Java skin - Ocean Theme", false)
                        .withItemListener(ie -> {
                            if (ie.getStateChange() == ItemEvent.SELECTED)
                                NotepadLookAndFeelManager.setLookAndFeel(NotepadLookAndFeelManager.LookAndFeelTypes.JAVAOCEAN, notepadWindow);
                        })
                        .build())
                .endGroup()
                .build();

        JMenu helpMenu = new MenuBuilder("Help")
                .withMnemonic(KeyEvent.VK_C)
                .withItem(MenuItemBuilder.menuItem("Help topics")
                        .withMnemonic(KeyEvent.VK_F1)
                        .withAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK))
                        .withImageIcon("question_mark.gif")
                        .build())
                .withItem(MenuItemBuilder.menuItem("About...")
                        .withActionListener(ae ->
                                JOptionPane.showMessageDialog(notepadWindow, "Notepad Java Swing v.0.3", "About program...",
                                        JOptionPane.INFORMATION_MESSAGE))
                        .build())
                .build();

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        fileMenu.getItem(2).setEnabled(false);
        fileMenuItems=fileMenu;
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }

    JMenuItem getFileMenuItem(int pos){

        return fileMenuItems.getItem(pos);
    }
}