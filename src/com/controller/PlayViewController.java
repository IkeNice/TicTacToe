package com.controller;

import com.Main;
import com.view.PlayView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class PlayViewController extends MouseAdapter implements MouseListener, ActionListener {

    public static final String PLAY_AGAIN_COMMAND = "play again command";
    public static final String GET_SETTING_COMMAND = "get settings command";
    private Main root;
    private PlayView parent;

    public PlayViewController(PlayView parent, Main root) {
        this.parent = parent;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
