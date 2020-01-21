package com.controller;

import com.Main;
import com.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class MainViewController extends MouseAdapter implements MouseListener, ActionListener {

    public static final String PLAY_AGAIN_COMMAND = "play again command";
    private Main root;
    private MainView parent;

    public MainViewController(MainView parent, Main root) {
        this.parent = parent;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
