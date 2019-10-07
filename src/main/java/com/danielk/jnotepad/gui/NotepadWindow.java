package com.danielk.jnotepad.gui;

import com.danielk.jnotepad.data.LocalClipboard;
import com.danielk.jnotepad.data.NotepadFile;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NotepadWindow extends JFrame {

    public JTextArea textArea;
    private JPanel radioPanel;
    private NotepadFile localFile;
    private boolean wrapFlag = false;
    private boolean textUpdated = false;
    private ContextMenu contextMenu;
    private NotepadMenu notepadMenu;
    private LocalClipboard clipboard;

    public NotepadWindow() {

        super("Notepad - no name");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        contextMenu = new ContextMenu(this);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (e.getButton() == MouseEvent.BUTTON3) {
                    contextMenu.getContextMenu().show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        textArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (e.getDot() != e.getMark()) {
                    contextMenu.getContextMenu().getComponent(ContextMenu.CUT_POSTION).setEnabled(true);
                    contextMenu.getContextMenu().getComponent(ContextMenu.COPY_POSTION).setEnabled(true);
                    contextMenu.getContextMenu().getComponent(ContextMenu.DELETE_POSTION).setEnabled(true);
                    notepadMenu.getEditMenuItem(NotepadMenu.CUT_MENUITEM).setEnabled(true);
                    notepadMenu.getEditMenuItem(NotepadMenu.COPY_MENUITEM).setEnabled(true);
                    notepadMenu.getEditMenuItem(NotepadMenu.DELETE_MENUITEM).setEnabled(true);
                } else {
                    contextMenu.getContextMenu().getComponent(ContextMenu.CUT_POSTION).setEnabled(false);
                    contextMenu.getContextMenu().getComponent(ContextMenu.COPY_POSTION).setEnabled(false);
                    contextMenu.getContextMenu().getComponent(ContextMenu.DELETE_POSTION).setEnabled(false);
                    notepadMenu.getEditMenuItem(NotepadMenu.CUT_MENUITEM).setEnabled(false);
                    notepadMenu.getEditMenuItem(NotepadMenu.COPY_MENUITEM).setEnabled(false);
                    notepadMenu.getEditMenuItem(NotepadMenu.DELETE_MENUITEM).setEnabled(false);
                }
            }
        });

        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                updateTextUpdatedStatus(true);
                notepadMenu.getEditMenuItem(NotepadMenu.FIND_MENUITEM).setEnabled(true);
                notepadMenu.getEditMenuItem(NotepadMenu.REPLACE_MENUITEM).setEnabled(true);

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        LocalClipboard.getSystemClipboard().addFlavorListener(new FlavorListener() {
            @Override
            public void flavorsChanged(FlavorEvent e) {

                if (LocalClipboard.getSystemClipboard().isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                    contextMenu.getContextMenu().getComponent(ContextMenu.PASTE_POSTION).setEnabled(true);
                    notepadMenu.getEditMenuItem(NotepadMenu.PASTE_MENUITEM).setEnabled(true);
                } else {
                    contextMenu.getContextMenu().getComponent(ContextMenu.PASTE_POSTION).setEnabled(false);
                    notepadMenu.getEditMenuItem(NotepadMenu.PASTE_MENUITEM).setEnabled(false);
                }
            }
        });

        radioPanel = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        JRadioButton radioWin1250Coding = new JRadioButton("Windows-1250", true);
        radioWin1250Coding.addActionListener(ae -> openWithPrompt(NotepadFile.CharsetNames.CP_1250, localFile));
        JRadioButton radioUtf8Coding = new JRadioButton("UTF-8", false);
        radioUtf8Coding.addActionListener(ae -> openWithPrompt(NotepadFile.CharsetNames.UTF_8, localFile));
        JRadioButton radioUtf16Coding = new JRadioButton("UTF-16", false);
        radioUtf16Coding.addActionListener(ae -> openWithPrompt(NotepadFile.CharsetNames.UTF_16, localFile));
        JRadioButton radioAsciiCoding = new JRadioButton("ASCII", true);
        radioAsciiCoding.addActionListener(ae -> openWithPrompt(NotepadFile.CharsetNames.ASCII, localFile));
        JRadioButton radioIsoCoding = new JRadioButton("ISO8859-2", false);
        radioIsoCoding.addActionListener(ae -> openWithPrompt(NotepadFile.CharsetNames.ISO8859_2, localFile));

        bg.add(radioWin1250Coding);
        bg.add(radioUtf8Coding);
        bg.add(radioUtf16Coding);
        bg.add(radioAsciiCoding);
        bg.add(radioIsoCoding);

        radioPanel.add(radioWin1250Coding);
        radioPanel.add(radioUtf8Coding);
        radioPanel.add(radioUtf16Coding);
        radioPanel.add(radioAsciiCoding);
        radioPanel.add(radioIsoCoding);
        radioPanel.setVisible(false);

        NotepadLookAndFeelManager.setLookAndFeel(NotepadLookAndFeelManager.LookAndFeelTypes.JAVAOCEAN, this);
        getContentPane().add(scroll);
        getContentPane().add(radioPanel, "South");
        setVisible(true);
        setSize(650, 550);
        setLocation(300, 250);
    }

    public void openWithPrompt(NotepadFile.CharsetNames selectedCharset, NotepadFile localFile) {

        try {
            String mainText=localFile.openWithEncoding(selectedCharset);
            textArea.setText(mainText);
            textArea.setCaretPosition(0);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unknown Error while opening the file");
        }

    }

    void updateTextUpdatedStatus(boolean status) {

        this.textUpdated = status;
    }

    @Override
    public void setJMenuBar(JMenuBar menubar) {
        super.setJMenuBar(menubar);
        menubar.revalidate();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    void newFile() {

        if (textUpdated) {
            if (notMistake()) {
                clearTetArea();
            }
        } else {
            clearTetArea();
        }
    }

    private void clearTetArea() {
        textArea.setText("");
        radioPanel.setVisible(false);
        setTitle("JNotepad - no name");
    }

    boolean notMistake() {
        int result = JOptionPane.showConfirmDialog(this, "Current text not saved - are you sure?", "Warning",
                JOptionPane.OK_CANCEL_OPTION);
        return result != JOptionPane.CANCEL_OPTION && result != JOptionPane.CLOSED_OPTION;
    }

    void wrapText() {

        wrapFlag = !wrapFlag;
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(wrapFlag);
    }

    void setRadioPanelVisible() {

        radioPanel.setVisible(true);
    }

    void setLocalFile(NotepadFile localFile) {
        this.localFile = localFile;
    }

    public void exitNotepad() {
        if (!textUpdated || (notMistake())) {
            System.exit(0);
        }
    }

    boolean isTextUpdated() {
        return textUpdated;
    }

    public void addNotepadMenu(NotepadMenu notepadMenu) {

        this.notepadMenu = notepadMenu;
    }

    public void addClipboard(LocalClipboard clipboard) {

        this.clipboard = clipboard;
    }

    public void setTextUpdated(boolean b) {
        textUpdated = b;
    }
}