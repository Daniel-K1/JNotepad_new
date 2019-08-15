package com.danielk.jnotepad.gui;

import com.danielk.jnotepad.data.LocalClipboard;

import javax.swing.*;

public class ContextMenu extends JPopupMenu {

    JPopupMenu contextMenu;
    private NotepadWindow localWindow;

    public static final int CUT_POSTION=0;
    public static final int COPY_POSTION=1;
    public static final int PASTE_POSTION=2;
    public static final int DELETE_POSTION=3;

    public ContextMenu(NotepadWindow notepadWindow) {

        this.localWindow = notepadWindow;
        contextMenu = new JPopupMenu();
        JMenuItem contextCut = new JMenuItem("Cut");
        contextCut.addActionListener(ae-> new LocalClipboard(localWindow).cut(this.localWindow.getTextArea().getSelectedText()));
        contextCut.setEnabled(false);
        JMenuItem contextCopy = new JMenuItem("Copy");
        contextCopy.addActionListener(ae->LocalClipboard.copy(localWindow.getTextArea().getSelectedText()));
        contextCopy.setEnabled(false);
        JMenuItem contextPaste = new JMenuItem("Paste");
        contextPaste.addActionListener(ae->LocalClipboard.paste());
        contextPaste.setEnabled(false);
        JMenuItem contextDelete = new JMenuItem("Delete");
        contextDelete.addActionListener(ae->new LocalClipboard(localWindow).delete());
        contextDelete.setEnabled(false);

        contextMenu.add(contextCut);
        contextMenu.add(contextCopy);
        contextMenu.add(contextPaste);
        contextMenu.add(contextDelete);

        contextMenu.setVisible(false);
    }

    public JPopupMenu getContextMenu() {
        return contextMenu;
    }
}
