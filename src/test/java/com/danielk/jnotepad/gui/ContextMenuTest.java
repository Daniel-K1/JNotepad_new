package com.danielk.jnotepad.gui;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContextMenuTest {

    @Test
    void getContextMenu() {

        JPopupMenu contextMenuTest=new JPopupMenu();
        ContextMenu contextMenu= new ContextMenu(new NotepadWindow());

        assertEquals(contextMenuTest.getClass(),contextMenu.getContextMenu().getClass());
    }
}