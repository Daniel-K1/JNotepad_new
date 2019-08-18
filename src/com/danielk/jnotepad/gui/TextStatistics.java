package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.util.StringTokenizer;

class TextStatistics {

    TextStatistics(NotepadWindow notepadWindow) {

        JOptionPane.showMessageDialog(notepadWindow,
                "Lines: " + notepadWindow.getTextArea().getLineCount()
                        + "\nwords: " + getWords(notepadWindow.getTextArea().getText())
                        + "\ncharactes (incl. whitespaces): " + notepadWindow.getTextArea().getText().length()
                        + "\ncharacters (excl. whitespaes): " + getCharactersNoWhitespaces(notepadWindow.getTextArea().getText()),
                "Text statistics",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private int getCharactersNoWhitespaces(String text) {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != '\t' && text.charAt(i) != '\n') {
                output.append(text.charAt(i));
            }
        }
        return output.toString().length();
    }

    private int getWords(String input) {

        StringTokenizer tokens = new StringTokenizer(input);
        return tokens.countTokens();
    }
}