package com.view.components;

import javax.swing.*;

public class GameCell extends JButton {

    public int cellY;
    public int cellX;

    public GameCell(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }
}

