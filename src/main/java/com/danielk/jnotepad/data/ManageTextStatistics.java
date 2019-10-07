package com.danielk.jnotepad.data;

import java.util.StringTokenizer;

public class ManageTextStatistics {

    public static int getCharactersNoWhitespaces(String text) {

        if(text==null){
            text="";
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != '\t' && text.charAt(i) != '\n') {
                output.append(text.charAt(i));
            }
        }

        return output.toString().length();
    }

    public static int getWordsCount(String input) {

        if(input==null){
            input="";
        }

        StringTokenizer tokens = new StringTokenizer(input);
        return tokens.countTokens();
    }
}
