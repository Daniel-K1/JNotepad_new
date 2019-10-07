package com.danielk.jnotepad.gui;

import com.danielk.jnotepad.data.ManageTextStatistics;

import javax.swing.*;

class TextStatisticsDialog {

    TextStatisticsDialog(NotepadWindow notepadWindow) {

        JOptionPane.showMessageDialog(notepadWindow,
                "Lines: " + notepadWindow.getTextArea().getLineCount()
                        + "\nwords: " + ManageTextStatistics.getWordsCount(notepadWindow.getTextArea().getText())
                        + "\ncharactes (incl. whitespaces): " + notepadWindow.getTextArea().getText().length()
                        + "\ncharacters (excl. whitespaes): "
                        + ManageTextStatistics.getCharactersNoWhitespaces(notepadWindow.getTextArea().getText()),
                "Text statistics",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

