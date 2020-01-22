package com.view.components;

import javax.swing.*;

public class GameCell extends JButton {

    private int cellY;
    private int cellX;

    public GameCell(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }
}

