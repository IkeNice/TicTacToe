package com.controller;

import com.Main;
import com.data.ApplicationData;
import com.view.SettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsViewController implements ActionListener {

    public static final String PLAY_VS_PLAYER = "PLAY_VS_PLAYE";
    public static final String PLAY_VS_COMPUTER = "PLAY_VSCOMPUTER";
    private SettingsView parent;
    private Main root;

    public SettingsViewController(SettingsView parent, Main root){
        this.parent = parent;
        this.root = root;
    }
    @Override
    public void actionPerformed(ActionEvent e){

        switch (e.getActionCommand()){
            case PLAY_VS_PLAYER:
                ApplicationData.playMode = ApplicationData.PLAYER_VS_PLAYER ;
                break;

            case PLAY_VS_COMPUTER:

                ApplicationData.playMode = ApplicationData.PLAYER_VS_COMPUTER;
                break;
        } // end switch
        ApplicationData.setLineSize(parent.getSliderValue());
        root.play();
    }
}
