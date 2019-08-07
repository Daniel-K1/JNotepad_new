package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;

import java.io.*;

public class NotepadFile {

    private File selectedFile;

    private final NotepadWindow notepadWindow;

    public NotepadFile(NotepadWindow notepadWindow) {
        this.notepadWindow = notepadWindow;
    }

    public void openWithEncoding(String charsetName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), charsetName))) {
            String line;
            StringBuilder MainText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                MainText.append(line);
                MainText.append(System.lineSeparator());
            }

            notepadWindow.textArea.setText(MainText.toString());
            notepadWindow.textArea.setCaretPosition(0);

        } catch (IOException e) {
            System.out.print("IO Error while opening the file");
        }
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
}