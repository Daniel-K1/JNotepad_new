package com.danielk.jnotepad.gui;

import javax.swing.*;

public class ToolTips {

    private static boolean isChecked =false;

    public static void showHideTooltips() {

        if(isChecked){
            ToolTipManager.sharedInstance().setEnabled(true);
            isChecked =false;
        }else{
            ToolTipManager.sharedInstance().setEnabled(false);
            isChecked =true;
        }
    }
}
