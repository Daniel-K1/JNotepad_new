package com.danielk.jnotepad.gui;

import com.danielk.jnotepad.data.LocalClipboard;
import com.danielk.jnotepad.data.NotepadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NotepadPrompts {

    private NotepadFile localFile;
    private File dir;
    private String fileName;
    private LocalClipboard clipboard;

    private final static Logger LOG = LoggerFactory.getLogger(NotepadPrompts.class);

    public NotepadPrompts(NotepadFile notepadFile, LocalClipboard clipboard) {
        this.localFile = notepadFile;
        this.clipboard = clipboard;
    }

    void openFile(NotepadMenu menu, NotepadWindow notepadWindow) {

        JFileChooser chooser = new JFileChooser(dir);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt, *.me)", "txt", "me");
        chooser.addChoosableFileFilter(filter);
        int chooserReturnValue = chooser.showOpenDialog(notepadWindow);
        if (chooserReturnValue == JFileChooser.APPROVE_OPTION) {

            if (!notepadWindow.isTextUpdated() || notepadWindow.notMistake()) {
                localFile.setSelectedFile(chooser.getSelectedFile());
                localFile.openWithEncoding("cp1250");
                notepadWindow.setRadioPanelVisible();
                notepadWindow.setTitle("JNotepad - " + chooser.getSelectedFile().getName());
                notepadWindow.setLocalFile(localFile);
                fileName = chooser.getSelectedFile().getName();
                dir = new File(chooser.getCurrentDirectory().getAbsolutePath());
                menu.getFileMenuItem(NotepadMenu.SAVE_MENUITEM).setEnabled(true);
                menu.getEditMenuItem(NotepadMenu.FIND_MENUITEM).setEnabled(true);
                menu.getEditMenuItem(NotepadMenu.REPLACE_MENUITEM).setEnabled(true);
            }
        }
    }

    void saveFile(NotepadMenu menu, NotepadWindow notepadWindow, boolean firstSave) {

        boolean doNotSave = false;

        if (firstSave) {
            JFileChooser chooser = new JFileChooser(dir);

            chooser.setDialogTitle("Save as...");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt, *.me)", "txt", "me");
            chooser.addChoosableFileFilter(filter);

            if (chooser.showSaveDialog(notepadWindow) == JFileChooser.APPROVE_OPTION) {
                boolean fileNameExists = false;
                File[] listFiles = chooser.getCurrentDirectory().listFiles();
                assert listFiles != null;
                for (File file : listFiles) {
                    if (file.getName().equals(chooser.getSelectedFile().getName())) {
                        fileNameExists = true;
                        break;
                    }
                }

                if (fileNameExists && (!(chooser.getSelectedFile().getName().equals(fileName)))) {

                    if (JOptionPane.showConfirmDialog(null, "Filename: [" + chooser.getSelectedFile().getName()
                                    + "] already exists. Do you want to overwrite it?",
                            "Filename warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        fileName = chooser.getSelectedFile().getName();
                        dir = new File(chooser.getCurrentDirectory().getAbsolutePath());
                        menu.getFileMenuItem(NotepadMenu.SAVE_MENUITEM).setEnabled(true);
                    } else {
                        doNotSave = true;
                    }

                } else {
                    fileName = chooser.getSelectedFile().getName();
                    dir = new File(chooser.getCurrentDirectory().getAbsolutePath());
                    menu.getFileMenuItem(NotepadMenu.SAVE_MENUITEM).setEnabled(true);
                }
            } else {
                doNotSave = true;
            }
        }

        if (dir != null && fileName != null && !doNotSave) {

            File actualFile = new File(dir, fileName);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(actualFile))) {

                JTextArea textField = notepadWindow.getTextArea();

                for (int i = 0; i < textField.getText().length(); i++) {
                    if (textField.getText().charAt(i) != '\n') {
                        bw.write(textField.getText().charAt(i));
                    } else {
                        bw.newLine();
                    }
                }
                notepadWindow.updateTextUpdatedStatus(false);
                notepadWindow.setTitle("JNotepad - " + fileName);

            } catch (IOException e) {
                LOG.error("IO Error while saving the file"+e.getMessage());
            }
        }
    }

    public void printFile(NotepadWindow notepadWindow) {

        try{
            notepadWindow.getTextArea().print();
        } catch (PrinterException e) {
            LOG.error("Error while printing: "+e.getMessage());
        }

    }

    public void setFont(NotepadWindow notepadWindow) {

        JFontChooser fontChooser = new JFontChooser();
        int result = fontChooser.showDialog(notepadWindow.getTextArea());
        if (result == JFontChooser.OK_OPTION) {
            Font font = fontChooser.getSelectedFont();
            notepadWindow.getTextArea().setFont(font);
        }

    }
}