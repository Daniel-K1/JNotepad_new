package com.danielk.jnotepad.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

class MenuItemBuilder {

    private final JMenuItem menuItem;

    private MenuItemBuilder(JMenuItem menuItem) {
        this.menuItem = menuItem;
    }

    static MenuItemBuilder menuItem(String label) {

        return new MenuItemBuilder(new JMenuItem(label));
    }

    static MenuItemBuilder checkbox(String label, boolean isChecked) {

        return new MenuItemBuilder(new JCheckBoxMenuItem(label, isChecked));
    }

    static MenuItemBuilder radioButton(String label, boolean isChecked) {

        return new MenuItemBuilder(new JRadioButtonMenuItem(label, isChecked));
    }

    MenuItemBuilder withMnemonic(int mnemonic) {
        this.menuItem.setMnemonic(mnemonic);
        return this;
    }

    MenuItemBuilder isEnabled(boolean enabled) {
        this.menuItem.setEnabled(enabled);
        return this;
    }

    MenuItemBuilder withAccelerator(KeyStroke accelerator) {
        this.menuItem.setAccelerator(accelerator);
        return this;
    }

    MenuItemBuilder withActionListener(ActionListener ae) {
        this.menuItem.addActionListener(ae);
        return this;
    }

    MenuItemBuilder withItemListener(ItemListener ie) {

        this.menuItem.addItemListener(ie);
        return this;
    }

    MenuItemBuilder withImageIcon(String iconName) {
        this.menuItem.setIcon(new ImageIcon(iconName));
        return this;
    }

    JMenuItem build() {
        return this.menuItem;
    }
}