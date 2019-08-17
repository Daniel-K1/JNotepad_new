package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.*;

public class ReplaceDialog extends JFrame{

        private JTextArea localTextArea;
        private JPanel mainPane = new JPanel();
        private JTextField searchField = new JTextField(23);
        private Button replace = new Button("Replace");
        private Button cancel = new Button("Cancel");
        private JCheckBox isCaseSensitive = new JCheckBox();
        private JRadioButton isBackwardDirection = new JRadioButton();
        private JRadioButton isForwardDirection = new JRadioButton();
        private JLabel caseSensitiveLabel = new JLabel("case sensitive");
        private JLabel isBackwardsLabel = new JLabel("search backwards");
        private JLabel isForwardLabel = new JLabel("search forward");
        private ButtonGroup bg = new ButtonGroup();

        private JLabel replaceLabel = new JLabel("Replace: ");


        public ReplaceDialog(JTextArea textArea) {

            super("Replace");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.localTextArea = textArea;

            isCaseSensitive.setSelected(true);
            bg.add(isBackwardDirection);
            bg.add(isForwardDirection);
            bg.setSelected(isForwardDirection.getModel(), true);

            replace.setSize(50, 30);
            cancel.setSize(50, 30);

            GroupLayout layout = new GroupLayout(mainPane);

            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addComponent(replaceLabel)
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
                                    .addComponent(replace)
                                    .addComponent(cancel))
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                    .addComponent(replaceLabel)
                                    .addComponent(searchField, 20, 20, 20)
                                    .addComponent(replace, 30, 30, 30))
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
            setSize(441, 191);
            setLocation(300, 250);
            setResizable(false);
    }
}
