package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LocalClipboard {

    NotepadWindow localWidow;

    public LocalClipboard(NotepadWindow localWidow) {
        this.localWidow = localWidow;
    }

    public static void copy(String text) {
        Clipboard clipboard = getSystemClipboard();

        clipboard.setContents(new StringSelection(text), null);
    }

    public void cut(String text) {
        copy(text);
        delete();
    }

    public void delete() {
        localWidow.getTextArea().replaceSelection("");
    }

    public static void paste() {
        try {

            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        } catch (AWTException e) {
            System.out.println("Exception in Local Clipboard->paste method: " + e.getMessage());
        }
    }

    public static String get() throws Exception {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
            Object text = systemClipboard.getData(dataFlavor);
            return (String) text;
        }

        return null;
    }

    public static Clipboard getSystemClipboard() {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
}