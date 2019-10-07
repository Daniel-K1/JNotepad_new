package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LocalClipboardTest {

    private LocalClipboard clipboard = new LocalClipboard(new NotepadWindow());
    private Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
    private Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

    @Test
    @DisplayName("Correctly copying String to system Clipboard and retrieving from it - mixed text")
    void copy() {

        String text = "ABCabc123!@#";
        LocalClipboard.copy(text);

        //todo test z klasa TestClipboard extends Clipbord - przemyslec

        if (stringInSystemClipboard()) {
            assertCompareClipboardsForString(text);
        } else {
            fail("Data in system clipboad is not a String");
        }
    }

    //todo cut

    @Test
    @DisplayName("Correctly copying String to system Clipboard and retreving from it - String is 'null'")
    void copy_stringNull() {

        String text = null;
        clipboard.copy(text);

        if (stringInSystemClipboard()) {
            assertCompareClipboardsForString(text);
        } else {
            fail("Data in system clipboad is not a String");
        }
    }

    @Test
    @DisplayName("Correctly copying String to system Clipboard and retreving from it - empty String")
    void copy_stringEmpty() {

        String text = "";
        clipboard.copy(text);

        if (stringInSystemClipboard()) {
            assertCompareClipboardsForString(text);
        } else {
            fail("Data in system clipboard is not a String");
        }
    }

    private void assertCompareClipboardsForString(String text) {

        try {
            String systemClipboardContents = (String) systemClipboard.getData(DataFlavor.stringFlavor);
            assertEquals(text, systemClipboardContents);
        } catch (UnsupportedFlavorException | IOException e) {
            fail("Exception while reading from system clipboard: " + e.getMessage());
        }
    }

    private boolean stringInSystemClipboard() {

        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            return true;
        }
        return false;
    }

    @Test
    void cut() {
        fail("not done");

    }

    @Test
    @DisplayName("Tested if correctly deleted selection")
    void delete() {

        NotepadWindow window = new NotepadWindow();
        window.textArea.setText("test test");
        LocalClipboard clipboard = new LocalClipboard(window);
        clipboard.delete();
        assertEquals("", window.getTextArea().getSelectedText());

        //window.getTextArea().replaceSelection("")
    }

    @Test
    @DisplayName("Tested if correctly pasted text from system clipboard to text area")
    void paste() {

        String text = "ABCabc123!@#";
        systemClipboard.setContents(new StringSelection(text),null);
        NotepadWindow window = new NotepadWindow();
        LocalClipboard clipboard = new LocalClipboard(window);

        clipboard.paste();

        assertEquals(text,window.getTextArea().getText());
    }

    @Test
    void getSystemClipboard() {

        assertEquals(clipboard.getSystemClipboard(), systemClipboard);
    }
}