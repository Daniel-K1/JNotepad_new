package com.danielk.jnotepad.data;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LocalClipboardTest {

    private String text;
    private Clipboard systemClipboard;
    private Toolkit defaultToolkit;

    private static final Logger LOG = LoggerFactory.getLogger(LocalClipboardTest.class);


    @BeforeEach
    void setUp() {
        text = "ABCabc123!@#";
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void copy() {

        DataFlavor dataFlavor = DataFlavor.stringFlavor;
        
        systemClipboard.setContents(new StringSelection(text), null);

        try {
            String fromClipboard = (String) systemClipboard.getData(dataFlavor);
            assertEquals(text, fromClipboard);
        } catch (UnsupportedFlavorException | IOException e) {
            LOG.error("copy method test exception" + e);
        }

    }


    @Test
    void cut() {
    }

    @Test
    void delete() {
    }

    @Test
    void paste() {
    }

    @Test
    void get() {
    }

    @Test
    void getSystemClipboard() {
    }
}