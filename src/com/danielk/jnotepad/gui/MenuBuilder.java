package com.danielk.jnotepad.gui;

import javax.swing.*;

class MenuBuilder {

    private final JMenu menu;

    MenuBuilder(String menuLabel) {

        menu = new JMenu(menuLabel);
    }

    MenuBuilder withItem(JMenuItem item) {

        this.menu.add(item);
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

    JMenu build() {

        return this.menu;
    }
}