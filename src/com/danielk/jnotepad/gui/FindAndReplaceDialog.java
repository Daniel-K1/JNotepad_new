package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.*;

public class FindAndReplaceDialog extends JFrame {

    private JPanel mainPane = new JPanel();
    private JTextField searchField = new JTextField(23);
    private Button ok = new Button("Find");
    private Button cancel = new Button("Cancel");
    private JCheckBox isCaseSensitive = new JCheckBox();
    private JRadioButton isBackwardDirection = new JRadioButton();
    private JRadioButton isForwardDirection = new JRadioButton();
    private JLabel caseSensitiveLabel = new JLabel("case sensitive");
    private JLabel isBackwardsLabel = new JLabel("search backwards");
    private JLabel isForwardLabel = new JLabel("search forward");
    private ButtonGroup bg = new ButtonGroup();

    private JLabel findLabel = new JLabel("Find: ");


    public FindAndReplaceDialog() {


        super("Find");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        caseSensitiveLabel.setLabelFor(isCaseSensitive);

        bg.add(isBackwardDirection);
        bg.add(isForwardDirection);
        bg.setSelected(isForwardDirection.getModel(),true);

        ok.setSize(50, 30);
        cancel.setSize(50, 30);

        GroupLayout layout = new GroupLayout(mainPane);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

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
                                .addComponent(ok)
                                .addComponent(cancel))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(findLabel)
                                .addComponent(searchField, 20, 20, 20)
                                .addComponent(ok, 30, 30, 30))
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

    }

}