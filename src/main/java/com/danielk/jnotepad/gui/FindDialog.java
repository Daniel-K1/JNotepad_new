package com.danielk.jnotepad.gui;

import com.danielk.jnotepad.data.Find;
import com.danielk.jnotepad.data.Selection;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class FindDialog extends JDialog {

    private JTextArea localTextArea;
    private JTextField searchField = new JTextField(23);
    private JButton find = new JButton("Find");
    private JCheckBox isCaseSensitive = new JCheckBox();
    private JCheckBox wrapSearch = new JCheckBox();
    private JRadioButton isBackwardDirection = new JRadioButton();
    private JRadioButton isForwardDirection = new JRadioButton();


    private String lookFor;

    FindDialog(NotepadWindow parent, JTextArea textArea) {

        super(parent, "Find", true);

        JButton cancel = new JButton("Cancel");
        isCaseSensitive.setToolTipText("if unchecked - search for \"ABC\" results are ABC, abc, Abc etc.");
        wrapSearch.setToolTipText("search starts over after reaches end of the text");
        searchField.setToolTipText("enter text to be searched for");
        find.setToolTipText("finds next occurrence of searched field (counting from cursor position)");
        cancel.setToolTipText("dispose current dialog window");
        isForwardDirection.setToolTipText("when checked - search is done from current cursor position to the end of file");
        isBackwardDirection.setToolTipText("when checked - search is done from current cursor position to the beginning of file");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.localTextArea = textArea;
        JLabel caseSensitiveLabel = new JLabel("case sensitive");
        JLabel wrapSearchLabel = new JLabel("wrap search");
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

        find.setEnabled(false);

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
                                        .addComponent(caseSensitiveLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(wrapSearch)
                                        .addComponent(wrapSearchLabel)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(find, 90, 90, 90)
                                .addComponent(cancel, 90, 90, 90))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(searchField, 20, 20, 20)
                                .addComponent(find, 28, 28, 28))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isBackwardDirection)
                                .addComponent(isBackwardsLabel)
                                .addComponent(isForwardDirection)
                                .addComponent(isForwardLabel)
                                .addComponent(cancel, 28, 28, 28))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(isCaseSensitive)
                                .addComponent(caseSensitiveLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(wrapSearch)
                                .addComponent(wrapSearchLabel)));

        mainPane.setLayout(layout);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                enableButton();
            }

            public void removeUpdate(DocumentEvent e) {
                enableButton();
            }

            public void insertUpdate(DocumentEvent e) {
                enableButton();
            }

            public void enableButton() {
                if (searchField.getText().equals("")) {
                    find.setEnabled(false);
                } else {
                    find.setEnabled(true);
                }
            }
        });

        find.addActionListener(ae -> {

            Selection selection = Find.find(localTextArea, searchField.getText(),
                    isCaseSensitive.isSelected(), isForwardDirection.isSelected(), wrapSearch.isSelected());

            parent.getTextArea().setSelectionStart(selection.getStartIndex());
            parent.getTextArea().setSelectionEnd(selection.getEndIndex());

            if (find.getText().equals("Find")) {
                find.setText("Find next");
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
}