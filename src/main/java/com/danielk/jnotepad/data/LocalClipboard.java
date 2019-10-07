package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;

public class LocalClipboard {

    private final static Logger LOG = LoggerFactory.getLogger(LocalClipboard.class);

    NotepadWindow localWindow;

    public LocalClipboard(NotepadWindow localWindow) {
        this.localWindow = localWindow;
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
        localWindow.getTextArea().replaceSelection("");
    }

    public static void paste() {
        try {

            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        } catch (AWTException e) {
            LOG.error("Exception in Local Clipboard->paste method: " + e);
        }
    }

//    public static String get() throws Exception {
//        Clipboard systemClipboard = getSystemClipboard();
//        DataFlavor dataFlavor = DataFlavor.stringFlavor;
//
//        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
//            Object text = systemClipboard.getData(dataFlavor);
//            return (String) text;
//        }
//
//        return null;
//    }

    public static Clipboard getSystemClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }
}