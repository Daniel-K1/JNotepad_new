package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.*;

public class ReplaceDialog extends JDialog {

    private JTextArea localTextArea;
    private JTextField searchField = new JTextField(23);
    private JTextField replaceField = new JTextField(23);
    private JButton find = new JButton("Find");
    private JCheckBox isCaseSensitive = new JCheckBox();
    private JRadioButton isBackwardDirection = new JRadioButton();
    private JRadioButton isForwardDirection = new JRadioButton();

    private int startIndex = -1;
    private int endIndex = -1;

    private String lookFor;

    ReplaceDialog(JFrame parent, JTextArea textArea) {

        super(parent,"Replace", true);

        JLabel replaceLabel = new JLabel("Replace: ");
        JButton replace1 = new JButton("Replace");
        JButton replaceAll = new JButton("Replace all");
        JButton cancel = new JButton("Cancel");
        JLabel caseSensitiveLabel = new JLabel("case sensitive");
        JLabel isBackwardsLabel = new JLabel("search backwards");
        JLabel isForwardLabel = new JLabel("search forward");
        JLabel findLabel = new JLabel("Find: ");

        isCaseSensitive.setToolTipText("if unchecked - search for \"ABC\" results are ABC, abc, Abc etc.");
        searchField.setToolTipText("enter text to be searched for");
        find.setToolTipText("finds next occurrence of searched field (counting from cursor position)");
        cancel.setToolTipText("dispose current dialog window");
        isForwardDirection.setToolTipText("when checked - search is done from current cursor position to the end of file");
        isBackwardDirection.setToolTipText("when checked - search is done from current cursor position to the beginning of file");
        replace1.setToolTipText("replaces currently selected text in main window with text from \"replace\" field");
        replaceAll.setToolTipText("replaces all occurences of searched text with text from \"replace\" field, cursor position doesn't matter");
       replaceField.setToolTipText("enter text to be replaced with searched text");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.localTextArea = textArea;

        isCaseSensitive.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(isBackwardDirection);
        bg.add(isForwardDirection);
        bg.setSelected(isForwardDirection.getModel(), true);

        JPanel mainPane = new JPanel();
        GroupLayout layout = new GroupLayout(mainPane);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(replaceLabel))
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(searchField)
                                        .addComponent(replaceField))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(isBackwardDirection)
                                        .addComponent(isBackwardsLabel)
                                        .addComponent(isForwardDirection)
                                        .addComponent(isForwardLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(isCaseSensitive)
                                        .addComponent(caseSensitiveLabel)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(find,98,98,98)
                                .addComponent(replace1,98,98,98)
                                .addComponent(replaceAll,98,98,98)
                                .addComponent(cancel,98,98,98)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(searchField, 20, 20, 20)
                                .addComponent(find, 22, 22, 22))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(replaceLabel)
                                .addComponent(replaceField, 20, 20, 20)
                                .addComponent(replace1, 22, 22, 22))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isBackwardDirection)
                                .addComponent(isBackwardsLabel)
                                .addComponent(isForwardDirection)
                                .addComponent(isForwardLabel)
                                .addComponent(replaceAll, 22, 22, 22))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isCaseSensitive)
                                .addComponent(caseSensitiveLabel)
                                .addComponent(cancel, 22, 22, 22)
                        ));

        mainPane.setLayout(layout);

        find.addActionListener(ae -> {

            if (startIndex < 0) {
                startIndex = localTextArea.getSelectionStart();
            }

            String base = localTextArea.getText();
            lookFor = searchField.getText();
            endIndex = startIndex + searchField.getText().length();

            if (!isCaseSensitive.isSelected()) {
                lookFor = lookFor.toLowerCase();
                base = base.toLowerCase();
            }

            if (isBackwardDirection.isSelected()) {
                findTextBackwards(lookFor, base);
            }

            if (isForwardDirection.isSelected()) {
                findTextForward(lookFor, base);
            }
        });

        replace1.addActionListener(ae -> {
                    String replace = replaceField.getText();
                    localTextArea.replaceSelection(replace);
                    startIndex=localTextArea.getSelectionEnd();
                }
        );

        replaceAll.addActionListener(ae->{

            String lookFor = searchField.getText();
            String replace=replaceField.getText();
            String base = localTextArea.getText();

            if(!isCaseSensitive.isSelected()){
                lookFor=lookFor.toLowerCase();
                base=base.toLowerCase();
            }

            localTextArea.setCaretPosition(0);
            startIndex=0;
            endIndex=searchField.getText().length();

            while (endIndex<=base.length() && !replace.equals("") && !lookFor.equals("")){
                findTextForward(lookFor, base);
                localTextArea.replaceSelection(replace);
                startIndex=localTextArea.getSelectionEnd();
                endIndex=startIndex+lookFor.length();
            }

            localTextArea.setCaretPosition(0);
            startIndex=0;
            endIndex=0;

        });

        cancel.addActionListener(ae -> this.dispose());

        getContentPane().add(mainPane);
        setPreferredSize(new Dimension(470,191));
        setResizable(false);

        pack();
        setLocation(parent.getX()+100, parent.getY()+100);
        setVisible(true);
    }

    private void findTextForward(String lookFor, String base) {

        while (endIndex <= base.length()) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                if (find.getText().equals("Find")) {
                    find.setText("Find next");
                }
                startIndex++;
                endIndex++;
                return;
            } else {
                startIndex++;
                endIndex++;
            }
        }
    }

    private void findTextBackwards(String lookFor, String base) {

        if (endIndex > base.length()) {
            do {
                endIndex--;
                startIndex--;
            } while(endIndex!=base.length());
        }

        while (startIndex >= 0) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                if (find.getText().equals("Find")) {
                    find.setText("Find next");
                }
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