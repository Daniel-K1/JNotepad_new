package com.danielk.jnotepad.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

class NotepadFileTest {

    private File selectedFile;
    private static final Logger LOG = LoggerFactory.getLogger(NotepadFileTest.class);

    @ParameterizedTest
    @MethodSource("argumentFactory")
    void openWithEncoding_parametrized(String charsetName) {

        boolean ok = true;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), charsetName));
        } catch (IOException e) {
            ok=false;
            LOG.error("IO Error while opening the file in test method: " + e.getMessage());
        }
    }

    private static Stream<Arguments> argumentFactory() {
        return Stream.of(
                Arguments.of("Cp1250"),
                Arguments.of("UTF8"),
                Arguments.of("UTF16"),
                Arguments.of("ASCII"),
                Arguments.of("ISO8859_2")
        );
    }

}