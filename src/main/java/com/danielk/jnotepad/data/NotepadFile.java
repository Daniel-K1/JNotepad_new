package com.danielk.jnotepad.data;

import com.danielk.jnotepad.gui.NotepadWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class NotepadFile {

    public enum CharsetNames {

        CP_1250("Cp1250"),
        UTF_8("UTF8"),
        UTF_16("UTF16"),
        ASCII("ASCII"),
        ISO8859_2("ISO8859_2");

        private String charset;

        CharsetNames(String charset) {
            this.charset = charset;
        }

        public String getCharset() {
            return charset;
        }
    }

    private File selectedFile;
    private final NotepadWindow notepadWindow;
    private final static Logger LOG = LoggerFactory.getLogger(NotepadFile.class);

    public NotepadFile(NotepadWindow notepadWindow) {
        this.notepadWindow = notepadWindow;
    }

    public String openWithEncoding(CharsetNames charset) throws IOException {

        String charsetName=charset.getCharset();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), charsetName))) {
            String line;
            StringBuilder mainText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                mainText.append(line);
                mainText.append(System.lineSeparator());
            }

            return mainText.toString();
            //notepadWindow.textArea.setText(mainText.toString());
           // notepadWindow.textArea.setCaretPosition(0);

        } catch (FileNotFoundException e) {
            LOG.warn("File not found:" + e.getMessage(), e);
            throw e;

        } catch (IOException e) {
            LOG.error("General IO Exception while opening the file: " + e.getMessage(), e);
            throw e;
        }
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
}