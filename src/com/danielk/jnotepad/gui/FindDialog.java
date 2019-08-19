package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.*;

class FindDialog extends JDialog {

    private JTextArea localTextArea;
    private JTextField searchField = new JTextField(23);
    private JButton find = new JButton("Find");
    private JCheckBox isCaseSensitive = new JCheckBox();
    private JRadioButton isBackwardDirection = new JRadioButton();
    private JRadioButton isForwardDirection = new JRadioButton();

    private int startIndex = -1;
    private int endIndex = -1;

    private String lookFor;

    FindDialog(JFrame parent, JTextArea textArea) {

        super(parent, "Find", true);

        JButton cancel = new JButton("Cancel");
        isCaseSensitive.setToolTipText("if unchecked - search for \"ABC\" results are ABC, abc, Abc etc.");
        searchField.setToolTipText("enter text to be searched for");
        find.setToolTipText("finds next occurrence of searched field (counting from cursor position)");
        cancel.setToolTipText("dispose current dialog window");
        isForwardDirection.setToolTipText("when checked - search is done from current cursor position to the end of file");
        isBackwardDirection.setToolTipText("when checked - search is done from current cursor position to the beginning of file");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.localTextArea = textArea;
        JLabel caseSensitiveLabel = new JLabel("case sensitive");
        caseSensitiveLabel.setLabelFor(isCaseSensitive);

        isCaseSensitive.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(isBackwardDirection);
        bg.add(isForwardDirection);
        bg.setSelected(isForwardDirection.getModel(), true);

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
                                .addComponent(find, 90,90,90)
                                .addComponent(cancel,90,90,90))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(searchField, 20, 20, 20)
                                .addComponent(find,28,28,28))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isBackwardDirection)
                                .addComponent(isBackwardsLabel)
                                .addComponent(isForwardDirection)
                                .addComponent(isForwardLabel)
                                .addComponent(cancel,28,28,28))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isCaseSensitive)
                                .addComponent(caseSensitiveLabel)));

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

        cancel.addActionListener(ae -> this.dispose());

        getContentPane().add(mainPane);
        setPreferredSize(new Dimension(468, 159));
        setResizable(false);
        pack();
        setLocation(parent.getX() + 100, parent.getY() + 100);
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