package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.util.Optional;

public class MenuBuilder {

    private final JMenu menu;
    private ButtonGroup buttonGroup;

    MenuBuilder(String menuLabel) {

        menu = new JMenu(menuLabel);
    }

    MenuBuilder withItem(JMenuItem item) {

        this.menu.add(item);
        return this;
    }
/*
methods withCheckboxItem and withRadiobuttonItem to be used in next update
 */


    MenuBuilder withCheckboxItem(JCheckBoxMenuItem item) {

        this.menu.add(item);
        return this;
    }

    public MenuBuilder withRadiobuttonItem(JRadioButtonMenuItem item) {

        this.menu.add(item);
        Optional.ofNullable(buttonGroup).ifPresent(bg -> bg.add(item));
        return this;
    }

    MenuBuilder withMnemonic(int mnemonic) {

        this.menu.setMnemonic(mnemonic);
        return this;
    }

    MenuBuilder withSeparator() {

        menu.addSeparator();
        return this;
    }

    MenuBuilder startGroup() {

        buttonGroup = new ButtonGroup();
        return this;

    }

    MenuBuilder endGroup() {

        buttonGroup = null;
        return this;
    }

    JMenu build() {

        return this.menu;
    }
}