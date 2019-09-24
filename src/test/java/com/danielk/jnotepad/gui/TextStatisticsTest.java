package com.danielk.jnotepad.gui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class TextStatisticsTest {

    private String text;

    @BeforeEach
    void setUp() {
        text="AAA aaa a BBBBBbbbb   \n  q111";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCharactersNoWhitespaces() {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != '\t' && text.charAt(i) != '\n') {
                output.append(text.charAt(i));
            }
        }
        assertEquals(20, output.toString().length());
    }

    @Test
    void testCountCharactersWithWhitespaces(){

        assertEquals(29,text.length());
    }

    @Test
    void TestGetWords() {

        StringTokenizer tokens = new StringTokenizer(text);
        assertEquals(5,tokens.countTokens());
    }

    @Test
    void TestLineCounting(){

        JTextArea textArea = new JTextArea(text);
        assertEquals(2,textArea.getLineCount());
    }
}