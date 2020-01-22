package com.controller;

import com.Main;
import com.data.ApplicationData;
import com.view.SettingsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsViewController implements ActionListener {

    public static final String PLAYER_VS_PLAYER = "PLAYER_VS_PLAYER";
    public static final String PLAYER_VS_COMPUTER = "PLAYER_VS_COMPUTER";
    private SettingsView parent;
    private Main root;

    // передаем ссылки на родительский и основной классы
    public SettingsViewController(SettingsView parent, Main root){
        this.parent = parent;
        this.root = root;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // определяем значение ActionCommand в событиях
        switch (e.getActionCommand()){
            case PLAYER_VS_PLAYER:
                // устанавливаем режим игры "Player vs Player"
                ApplicationData.playMode = ApplicationData.PLAYER_VS_PLAYER ;
                break;

            case PLAYER_VS_COMPUTER:
                // устанавливаем режим игры "Player vs Computer"
                ApplicationData.playMode = ApplicationData.PLAYER_VS_COMPUTER;
                break;
        } // end switch

        // инициализируем панель игры
        root.play();
    } // end method actionPerformed
}
