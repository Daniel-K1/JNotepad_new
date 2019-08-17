package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.*;

class FindDialog extends JFrame {

    private JTextArea localTextArea;
    private JTextField searchField = new JTextField(23);
    private Button find = new Button("Find");
    private JCheckBox isCaseSensitive = new JCheckBox();
    private JRadioButton isBackwardDirection = new JRadioButton();
    private JRadioButton isForwardDirection = new JRadioButton();

    private int startIndex = -1;
    private int endIndex = -1;

    private String lookFor;

    FindDialog(JTextArea textArea) {

        super("Find");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.localTextArea = textArea;
        JLabel caseSensitiveLabel = new JLabel("case sensitive");
        caseSensitiveLabel.setLabelFor(isCaseSensitive);

        isCaseSensitive.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(isBackwardDirection);
        bg.add(isForwardDirection);
        bg.setSelected(isForwardDirection.getModel(), true);

        find.setSize(50, 30);
        Button cancel = new Button("Cancel");
        cancel.setSize(50, 30);

        JPanel mainPane = new JPanel();
        GroupLayout layout = new GroupLayout(mainPane);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel isBackwardsLabel = new JLabel("search backwards");
        JLabel isForwardLabel = new JLabel("search forward");
        JLabel findLabel = new JLabel("Find: ");
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(findLabel)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(searchField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(isBackwardDirection)
                                        .addComponent(isBackwardsLabel)
                                        .addComponent(isForwardDirection)
                                        .addComponent(isForwardLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(isCaseSensitive)
                                        .addComponent(caseSensitiveLabel)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(find)
                                .addComponent(cancel))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(searchField, 20, 20, 20)
                                .addComponent(find, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isBackwardDirection)
                                .addComponent(isBackwardsLabel)
                                .addComponent(isForwardDirection)
                                .addComponent(isForwardLabel)
                                .addComponent(cancel, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isCaseSensitive)
                                .addComponent(caseSensitiveLabel)));

        mainPane.setLayout(layout);

        getContentPane().add(mainPane);
        setVisible(true);
        setSize(441, 159);
        setLocation(300, 250);
        setResizable(false);

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

        cancel.addActionListener(ae -> this.dispose());
    }

    private void findTextForward(String lookFor, String base) {

        while (endIndex <= base.length()) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                if (find.getLabel().equals("Find")) {
                    find.setLabel("Find next");
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

        if (endIndex > lookFor.length()) {
            endIndex--;
            startIndex--;
        }

        while (startIndex >= 0) {
            if (base.substring(startIndex, endIndex).equals(lookFor)) {
                localTextArea.select(startIndex, endIndex);
                if (find.getLabel().equals("Find")) {
                    find.setLabel("Find next");
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