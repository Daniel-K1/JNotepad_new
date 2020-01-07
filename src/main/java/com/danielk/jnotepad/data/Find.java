package com.danielk.jnotepad.data;

import javax.swing.*;

public class Find {

    static int startIndex = -1;
    static int endIndex = -1;

    public static Selection find(JTextArea textArea, String lookForText,
                                 boolean caseSensitiveSelected, boolean forwardDirectionSelected, boolean wrapSearchSelected) {

        JTextArea localTextArea=textArea;

        int startIndex = -1;
        int endIndex = -1;

        if(localTextArea.getSelectedText()==null){
           // startIndex=localTextArea.
        }

        if (startIndex < 0) {
            startIndex = localTextArea.getSelectionStart();
        }

        String base = localTextArea.getText();
        String lookFor = lookForText;
        endIndex = startIndex + lookFor.length();

        if (!caseSensitiveSelected) {
            lookFor = lookFor.toLowerCase();
            base = base.toLowerCase();
        }

        if (forwardDirectionSelected) {
            findTextForward(lookFor, base, localTextArea);
        }else{
            findTextBackwards(lookFor, base, localTextArea);
        }

        return new Selection(startIndex,endIndex);
    }


    private static void findTextForward(String lookFor, String base, JTextArea localTextArea) {

        while (endIndex <= base.length()) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                startIndex++;
                endIndex++;
                return;
            } else {
                startIndex++;
                endIndex++;
            }
        }
    }

    private static void findTextBackwards(String lookFor, String base, JTextArea localTextArea) {

        if (endIndex > base.length()) {
            do {
                endIndex--;
                startIndex--;
            } while(endIndex!=base.length());
        }

        while (startIndex >= 0) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                startIndex--;
                endIndex--;
                return;
            } else {
                startIndex--;
                endIndex--;
            }
        }
    }
}
