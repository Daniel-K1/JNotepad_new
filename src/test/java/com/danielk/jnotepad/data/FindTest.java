package com.danielk.jnotepad.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindTest {

    @Test
    @DisplayName("checking if search FORWARD case sensitive ON works correctly (mixed text)")
    void find_forwardAndCaseSensitivityOn() {
        String text = "AAA aaa AAA sss SSS";
        String lookFor = "AAA";
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        Map<Integer, Integer> reference = Map.ofEntries(
                entry(0, 2),
                entry(8, 10));

        Map<Integer, Integer> results = new HashMap<>();

        int currentPos = 0;
        int end = textArea.getText().length();

        while (currentPos <= end) {
            Find.find(textArea, lookFor, true, true);

            if (results.containsKey(textArea.getSelectionStart())) {
                currentPos++;

            } else {
                results.put(textArea.getSelectionStart(), textArea.getSelectionEnd() - 1);
                currentPos = textArea.getSelectionEnd() - 1;
            }
        }

        assertEquals(reference, results);
    }

    @Test
    @DisplayName("checking if search FORWARD case sensitive OFF works correctly (mixed text)")
    void find_forwardAndCaseSensitivityOff() {
        String text = "AAA aaa AAA sss SSS";
        String lookFor = "AAA";
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        Map<Integer, Integer> reference = Map.ofEntries(
                entry(0, 2),
                entry(4, 6),
                entry(8, 10));

        Map<Integer, Integer> results = new HashMap<>();

        int currentPos = 0;
        int end = textArea.getText().length();

        while (currentPos <= end) {
            Find.find(textArea, lookFor, Boolean.FALSE, Boolean.TRUE);

            if (!(results.containsKey(textArea.getSelectionStart()))) {
                results.put(textArea.getSelectionStart(), textArea.getSelectionEnd() - 1);
                currentPos = textArea.getSelectionEnd() - 1;
            } else {
                currentPos++;
            }
        }

        assertEquals(reference, results);
    }

    @Test
    @DisplayName("checking if search BACKWARDS case sensitive ON works correctly (mixed text)")
    void find_backwardsAndCaseSensitivityOn() {
        String text = "AAA aaa AAA sss SSS";
        String lookFor = "AAA";
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        Map<Integer, Integer> reference = Map.ofEntries(
                entry(0, 2),
                entry(8, 10));

        Map<Integer, Integer> results = new HashMap<>();

        int currentPos = 0;
        int end = textArea.getText().length();

        while (currentPos <= end) {
            Find.find(textArea, lookFor, true, false);

            if (results.containsKey(textArea.getSelectionStart())) {
                currentPos++;
            } else {
                results.put(textArea.getSelectionStart(), textArea.getSelectionEnd() - 1);
                currentPos = textArea.getSelectionEnd() - 1;
            }
        }

        assertEquals(reference, results);
    }

    @Test
    @DisplayName("checking if search BACKWARDS case sensitive OFF works correctly (mixed text)")
    void find_backwardsAndCaseSensitivityOff() {
        String text = "AAA aaa AAA sss SSS";
        String lookFor = "AAA";
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        Map<Integer, Integer> reference = Map.ofEntries(
                entry(0, 2),
                entry(4, 6),
                entry(8, 10));

        Map<Integer, Integer> results = new HashMap<>();

        int currentPos = 0;
        int end = textArea.getText().length();

        while (currentPos <= end) {
            Find.find(textArea, lookFor, false, false);

            if (!(results.containsKey(textArea.getSelectionStart()))) {
                results.put(textArea.getSelectionStart(), textArea.getSelectionEnd() - 1);
                currentPos = textArea.getSelectionEnd() - 1;
            } else {
                currentPos++;
            }
        }

        assertEquals(reference, results);
    }

    @Test
    @DisplayName("Searching safely stopping and the text end - searched phrase at the end")
    void isStoppingAtTheEnd() {

        String text = "AAA AAA AAA";
        String lookFor = "AAA";
        JTextArea textArea = new JTextArea();
        textArea.setText(text);
        Map<Integer, Integer> reference = Map.ofEntries(
                entry(0, 2),
                entry(4, 6),
                entry(8, 10));

        Map<Integer, Integer> results = new HashMap<>();

        int currentPos = 0;
        int end = textArea.getText().length();

        while (currentPos <= end) {
            Find.find(textArea, lookFor, Boolean.FALSE, Boolean.FALSE);

            if (!(results.containsKey(textArea.getSelectionStart()))) {
                results.put(textArea.getSelectionStart(), textArea.getSelectionEnd() - 1);
                currentPos = textArea.getSelectionEnd() - 1;
            } else {
                currentPos++;
            }
        }

        //todo dokonczyc

        assertEquals(reference, results);

    }
}