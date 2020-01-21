package com.controller;

import com.Main;
import com.view.SettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsViewController implements ActionListener {

    public static final String PLAY_VS_PLAYER = "PLAY_VS_PLAYE";
    private SettingsView parent;
    private Main root;

    public SettingsViewController(SettingsView parent, Main root){
        this.parent = parent;
        this.root = root;
    }
    @Override
    public void actionPerformed(ActionEvent arg0){
    }

}
