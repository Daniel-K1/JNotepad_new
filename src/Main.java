import com.danielk.jnotepad.data.NotepadFile;
import com.danielk.jnotepad.gui.NotepadMenu;
import com.danielk.jnotepad.gui.NotepadPrompts;
import com.danielk.jnotepad.gui.NotepadWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        NotepadWindow notepadWindow = new NotepadWindow();
        NotepadFile notepadFile = new NotepadFile(notepadWindow);
        NotepadPrompts notepadPrompts = new NotepadPrompts(notepadFile);
        NotepadMenu notepadMenu = new NotepadMenu(notepadWindow, notepadPrompts);
        notepadWindow.setJMenuBar(notepadMenu.getMenuBar());

        notepadWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notepadWindow.exitNotepad();
                notepadWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }
}