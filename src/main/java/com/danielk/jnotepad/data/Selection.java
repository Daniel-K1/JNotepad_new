package com.danielk.jnotepad.data;

/**
 * Auxilliary class to handle text selection boundaries (selection start+ selection end) in one object
 *
 */

public class Selection {

    int startIndex;
    int endIndex;

    public Selection() {
    }

    public Selection(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }


    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

}
