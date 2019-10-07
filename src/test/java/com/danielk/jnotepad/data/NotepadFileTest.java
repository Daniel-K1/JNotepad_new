package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotepadFileTest {

    private File selectedFile;
    private static final Logger LOG = LoggerFactory.getLogger(NotepadFileTest.class);

    @BeforeEach
    void setUp() {
    }

    //todo string null
    //todo test sprawdzajacy czy strona kodowa zwracanego Stringa jest taka jak chcieliśmy

    @ParameterizedTest
    @DisplayName("Each and every Enum in 'charsetNames' can be passed as parameter without exception thrown")
    @MethodSource("argumentFactory")
    void openWithEncoding_parametrized(NotepadFile.CharsetNames charsetName) {

        NotepadWindow window = new NotepadWindow();
        window.setVisible(false);
        NotepadFile notepadFile = new NotepadFile(window);
        selectedFile = new File(String.join(File.separator,"src","test","resources","testFile.txt"));
        notepadFile.setSelectedFile(selectedFile);

        assertDoesNotThrow(() -> notepadFile.openWithEncoding(charsetName));
    }

    private static Stream<NotepadFile.CharsetNames> argumentFactory() {

        return Arrays.stream(NotepadFile.CharsetNames.values());
    }
}